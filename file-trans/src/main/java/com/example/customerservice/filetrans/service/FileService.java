package com.example.customerservice.filetrans.service;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.core.util.JacksonUtil;
import com.example.customerservice.filetrans.service.dto.SimpleBigFileDto;
import com.example.customerservice.filetrans.service.dto.SimpleFileDto;
import com.example.customerservice.filetrans.service.dto.SimpleMessageDto;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class FileService {

    @Value("${file.upload.url}")
    private String uploadFilePath;

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public FileService(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    // 上传文件到服务器
    public void uploadFile(MultipartFile file, String senderId, String receiverId, Long conversationId) throws IOException, NoSuchAlgorithmException {
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(fileBytes);
        String fileMD5 = new BigInteger(1, digest).toString(16);
        File dest = new File(uploadFilePath + '/' + fileMD5);
        file.transferTo(dest);

        SimpleMessageDto messageDto = SimpleMessageDto.builder().senderId(senderId).rcvId(receiverId).type((byte) 1).content(JacksonUtil.toJson(SimpleFileDto.builder().fileName(fileName).md5(fileMD5)))
                .conversationId(conversationId)
                .time(LocalDateTime.now()).build();

        Message<String> stringMsg = MessageBuilder.withPayload(JacksonUtil.toJson(messageDto)).build();
        rocketMQTemplate.send("sendfile", stringMsg);
    }

    //从服务器下载文件
    public void downloadFile(HttpServletResponse response, String fileName, String md5){
        File file = new File(uploadFilePath +'/'+ fileName);

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
        }
    }

    //分片上传到服务器
    public void uploadBigFile(MultipartFile file, String senderId, String receiverId, Long conversationId, Long splitSmallFileSize){
//        Long splitSmallFileSize = Long.parseLong(splitSize);
        //被分割文件总大小
        long splitFileSize=file.getSize();
        System.out.println("fileSize:" + splitFileSize);
        System.out.println("fileSize:" + splitFileSize);
        //每个小文件分割的起始位置
        long splitSmallFileBeginPos = 0;
        //实际分割的小文件大小
        long splitSmallFileActualSize = splitSmallFileSize > splitFileSize ? splitFileSize :splitSmallFileSize;
        //被分割的小文件个数
        int size=(int)Math.ceil(splitFileSize*1.0/splitSmallFileSize);
        System.out.println("splitSize:" + size);
        //被分割的小文件路径list
        List<String> splitSmallFileList=new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            splitSmallFileList.add(uploadFilePath+"/" + file.getOriginalFilename() + "/" +  i +"_"+file.getOriginalFilename());
            System.out.println(splitSmallFileList.get(i).toString());
        }
        File dir = new File(uploadFilePath+"/"+file.getOriginalFilename());
        dir.mkdirs();

        File splitFile = null;
        //临时存储
        try {
            InputStream ins = ins = file.getInputStream();
            splitFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, splitFile);
            ins.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //切割文件
        for (int i = 0; i < size; i++) {
            //切割起始位置
            splitSmallFileBeginPos=i*splitSmallFileSize;
            //切割到最后一个小文件
            if (i==size-1) {
                //切割的实际大小
                splitSmallFileActualSize=splitFileSize;
            }else {//否则
                //切割的实际大小
                splitSmallFileActualSize=splitSmallFileSize;
                //源文件减小
                splitFileSize-=splitSmallFileActualSize;
            }

            //具体的切割
            try {
                //源文件
                RandomAccessFile splitRandomAccessFile=new RandomAccessFile(splitFile, "r");
                //被分割的小文件
                RandomAccessFile splitSmallRandomAccessFile=new RandomAccessFile(splitSmallFileList.get(i), "rw");

                //从源文件的哪个位置读取
                splitRandomAccessFile.seek(splitSmallFileBeginPos);

                //分段读取
                //10字节的缓存
                byte[] cache=new byte[1024*10];
                int len=-1;
                while((len=splitRandomAccessFile.read(cache))!=-1) {
                    //小文件实际分割大小>len
                    if (splitSmallFileActualSize>len) {
                        splitSmallRandomAccessFile.write(cache,0,len);
                        splitSmallFileActualSize-=len;
                    }else {//小文件实际分割大小<len，写完数据后跳出循环
                        splitSmallRandomAccessFile.write(cache,0,(int)splitSmallFileActualSize);
                        break;
                    }
                }

                splitRandomAccessFile.close();
                splitSmallRandomAccessFile.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException("文件未找到",e);
            } catch (IOException e) {
                throw new RuntimeException("文件传输异常",e);
            }
        }
        //删除临时文件
        if (splitFile.isFile()) {
            splitFile.delete();
        }

        SimpleMessageDto messageDto = SimpleMessageDto.builder().senderId(senderId).rcvId(receiverId).type((byte) 1)
                .content(JacksonUtil.toJson(SimpleBigFileDto.builder().fileName(file.getOriginalFilename()).splitSmallFileSize(splitSmallFileSize).size(size).build()))
                .conversationId(conversationId)
                .time(LocalDateTime.now()).build();

        Message<String> stringMsg = MessageBuilder.withPayload(JacksonUtil.toJson(messageDto)).build();
        rocketMQTemplate.send("sendfile", stringMsg);
    }

    private void inputStreamToFile(InputStream ins, File file) throws Exception {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            throw new Exception("读取文件错误", e);
        }
    }

    //从服务器下载文件
    public ReturnObject downloadBigFile(HttpServletResponse response, String fileName, Long splitSmallFileSize, int size) {
        List<String> splitSmallFileList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            splitSmallFileList.add(i,uploadFilePath + "/" + fileName + "/" + i +"_"+fileName);
            System.out.println(splitSmallFileList.get(i));
        }
        long length = splitSmallFileSize * size;

        try {
            //输出流，写文件,true表示追加写而不覆盖
            OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(fileName,true));
            //输入流，读文件
            Vector<InputStream> vector=new Vector<InputStream>();
            for (int i = 0; i < splitSmallFileList.size(); i++) {
                vector.add(new BufferedInputStream(new FileInputStream(splitSmallFileList.get(i))));
            }
            //SequenceInputStream，实现批量输入流的按序列读
            SequenceInputStream sequenceInputStream=new SequenceInputStream(vector.elements());
            //10字节的缓存
            byte[] cache=new byte[1024*10];
            int len=-1;
            while ((len=sequenceInputStream.read(cache))!=-1) {
                //分段写
                outputStream.write(cache,0,len);
                outputStream.flush();
            }
            outputStream.close();
            sequenceInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件未找到",e);
        } catch (IOException e) {
            throw new RuntimeException("文件传输异常",e);
        }

        File file = new File(fileName);
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
        }
        //删除临时文件
        if (file.isFile()) {
            file.delete();
        }
        return new ReturnObject(ReturnNo.OK);
    }
}
