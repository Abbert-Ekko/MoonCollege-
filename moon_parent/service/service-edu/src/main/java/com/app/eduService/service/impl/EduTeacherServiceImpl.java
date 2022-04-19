package com.app.eduService.service.impl;

import com.app.eduService.entity.EduTeacher;
import com.app.eduService.mapper.EduTeacherMapper;
import com.app.eduService.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-09
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired(required = false)
    private EduTeacherMapper mapper;

    public EduTeacher _teacher;

    @Override
    public IPage<EduTeacher> getPage(int currentPage, int pageSize, EduTeacher teacher) {

        IPage<EduTeacher> page = new Page<EduTeacher>(currentPage, pageSize);

        LambdaQueryWrapper<EduTeacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();

        teacherLambdaQueryWrapper.like(Strings.isNotEmpty(teacher.getName()), EduTeacher::getName, teacher.getName());
//        teacherLambdaQueryWrapper.like(Strings.isNotEmpty(teacher.getIntro()), EduTeacher::getIntro, teacher.getIntro());
//        teacherLambdaQueryWrapper.like(Strings.isNotEmpty(teacher.getCareer()), EduTeacher::getCareer, teacher.getCareer());
        teacherLambdaQueryWrapper.eq(teacher.getLevel() != null, EduTeacher::getLevel, teacher.getLevel());
//        teacherLambdaQueryWrapper.like(Strings.isNotEmpty(teacher.getAvatar()), EduTeacher::getAvatar, teacher.getAvatar());
        teacherLambdaQueryWrapper.ge(teacher.getGmtCreate() != null, EduTeacher::getGmtCreate, teacher.getGmtCreate());
        teacherLambdaQueryWrapper.le(teacher.getGmtModified() != null, EduTeacher::getGmtModified, teacher.getGmtModified());

        mapper.selectPage(page, teacherLambdaQueryWrapper);

        return page;
    }

}
