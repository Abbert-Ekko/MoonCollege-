package com.app.eduService.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
    private Date gmtModified;
    private Date gmtCreate;
    private String viewCount;
    private String status;

}
