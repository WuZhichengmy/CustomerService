package com.example.customerservice.filetrans.service;

import com.example.core.util.JacksonUtil;
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

        SimpleMessageDto messageDto = SimpleMessageDto.builder().senderId(senderId).rcvId(receiverId).type((byte) 1).content(JacksonUtil.toJson(SimpleFileDto.builder().fileName(fileName).md5(fileMD5).build()))
                .conversationId(conversationId)
                .time(LocalDateTime.now()).build();

        Message<String> stringMsg = MessageBuilder.withPayload(JacksonUtil.toJson(messageDto)).build();
        rocketMQTemplate.send("sendfile", stringMsg);
    }

    //从服务器下载文件
    public void downloadFile(HttpServletResponse response, String fileName, String md5) throws IOException {
        File file = new File(uploadFilePath +'/'+ fileName);

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
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
}
