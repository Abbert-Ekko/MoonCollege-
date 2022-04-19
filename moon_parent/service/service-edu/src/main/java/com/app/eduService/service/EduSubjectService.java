package com.app.eduService.service;

import com.app.eduService.entity.EduSubject;
import com.app.eduService.entity.subject.One;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);


    List<One> getAllOneTwoSubject();
}
