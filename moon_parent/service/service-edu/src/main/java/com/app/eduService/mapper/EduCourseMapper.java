package com.app.eduService.mapper;

import com.app.eduService.entity.EduCourse;
import com.app.eduService.entity.vo.CourseInfoVo;
import com.app.eduService.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-17
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    public ArrayList<CoursePublishVo> getCourseInfoList();
}
