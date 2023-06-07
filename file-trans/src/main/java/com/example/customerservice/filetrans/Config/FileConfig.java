package com.example.customerservice.filetrans.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileConfig {

    /**
     * 文件上传配置
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(
            @Value("${spring.servlet.multipart.max-file-size}")
            String maxFileSize,
            @Value("${spring.servlet.multipart.max-request-size}")
            String maxRequestSize
    ){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.of(3000, DataUnit.MEGABYTES));
        //总上传最大
        factory.setMaxRequestSize(DataSize.of(10000, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
