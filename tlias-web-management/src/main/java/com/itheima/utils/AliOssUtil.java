package com.itheima.utils;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.itheima.properties.AliOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Component
@EnableConfigurationProperties(AliOssProperties.class)
public class AliOssUtil {
    @Autowired
    private AliOssProperties aliOssProperties;

 /*  //springboot读取配置文件的方法一：通过声明成员变量，在成员变量上使用@Value注解
   @Value("${aliyun.oss.endpoint}")
    private String endpoint;

   @Value("${aliyun.oss.bucketName}")
   private String bucketName;


   @Value("${aliyun.oss.region}")
   private String region;*/




    public String upload(byte[] content, String originalFilename) throws Exception {
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 填写Object完整路径，例如202406/1.png。Object完整路径中不能包含Bucket名称。
        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //根据原始文件名originalFilename, 生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliOssProperties.getEndpoint(), credentialsProvider);

        //文件上传
        try {
            ossClient.putObject(aliOssProperties.getBucketName(), objectName, new ByteArrayInputStream(content));
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return aliOssProperties.getEndpoint().split("//")[0] + "//" + aliOssProperties.getBucketName() + "." + aliOssProperties.getEndpoint().split("//")[1] + "/" + objectName;
    }

}
