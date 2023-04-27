package com.example.customerservice.filetrans.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.ReturnObject;
import com.example.customerservice.filetrans.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/file")
@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ReturnObject uploadFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam("sender") String senderId,
                                   @RequestParam("receiver") String receiverId,
                                   @RequestParam("conversationId") Long conversationId) throws IOException, NoSuchAlgorithmException {
        this.fileService.uploadFile(file, senderId, receiverId, conversationId);
        return new ReturnObject(ReturnNo.OK);
    }

    @GetMapping("/download")
    public ReturnObject downloadFile(@RequestParam("filename") String fileName,
                                     @RequestParam("md5") String md5,
                                     HttpServletResponse response) throws IOException {
        this.fileService.downloadFile(response, fileName, md5);
        return new ReturnObject(ReturnNo.OK);
    }
}
