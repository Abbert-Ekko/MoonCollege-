package com.app.eduService.controller;


import com.app.eduService.entity.subject.One;
import com.app.eduService.service.EduSubjectService;
import com.app.eduService.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.success();
    }

    @GetMapping("getAllSubject")
    public R getAllOneTwoSubject(){
        List<One> list = subjectService.getAllOneTwoSubject();
        return R.success().data(list);
    }

}

