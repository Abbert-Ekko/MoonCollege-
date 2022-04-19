package com.app.controller;

import com.app.service.OssService;
import com.app.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduOss/file")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping()
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadAvatarFile(file);
        return R.success().data(url);
    }

}
