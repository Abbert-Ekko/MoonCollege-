package com.app.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.app.service.OssService;
import com.app.utils.ConstantPropertiesUtil;
import com.app.utils.R;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatarFile(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String originalFilename = file.getOriginalFilename();

        //添加随机UUID,防止文件名重复
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        //按照日期分类
        String datePath = new DateTime().toString("yyyy/MM/dd");

        //https://hsh-edu.oss-cn-chengdu.aliyuncs.com/picture/2022/04/13/4cb99dde0b604d068d07ad101a4e5322邀请函.jpg
        String objectName = "picture/"+datePath+"/"+uuid+originalFilename;

        /*// 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= "D:\\localpath\\examplefile.txt";*/

        try {


            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            InputStream inputStream = file.getInputStream();

            ossClient.putObject(bucketName,objectName,inputStream);

            ossClient.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String url="https://"+bucketName+"."+endpoint+"/"+objectName;

        return url;
    }
}
