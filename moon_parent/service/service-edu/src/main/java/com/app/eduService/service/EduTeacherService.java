package com.app.eduService.service;

import com.app.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-09
 */
@Component
public interface EduTeacherService extends IService<EduTeacher> {
    public IPage<EduTeacher> getPage(int currentPage, int pageSize, EduTeacher eduTeacher);
}
