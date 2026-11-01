package com.wyh.controller;

import com.wyh.pojo.Result;
import com.wyh.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //本地存储方式
//    @PostMapping("/upload")
//    public Result save(String name, Integer age, MultipartFile file) throws Exception{
//        log.info("姓名{}，年龄{}，内容{}",name,age,file);
//        //获取文件名
//        String originalFilename = file.getOriginalFilename();
//        //生成新的文件名
//        String newFilename = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        //存储文件到本地电脑
//        file.transferTo(new File("D:/web_ai_result/"+newFilename));
//        return Result.success();

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception{
        log.info("文件上传：{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS,url:{}",url);
        return Result.success(url);
    }
}
