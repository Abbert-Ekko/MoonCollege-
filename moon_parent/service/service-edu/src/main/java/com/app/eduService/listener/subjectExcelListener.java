package com.app.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.app.eduService.entity.EduSubject;
import com.app.eduService.entity.excel.SubjectData;
import com.app.eduService.exception.MoonException;
import com.app.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class subjectExcelListener extends AnalysisEventListener<SubjectData> {

    //subjectExcelListener不能交给spring管理,不能注入其他对象
    //不能实现数据库操作

    private EduSubjectService subjectService;

    public subjectExcelListener() {
    }

    public subjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel值,一行一行读
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new MoonException(20001,"文件数据为空");
        }
        //每行有两个值,第一个是一级分类,第二个是二级分类,加入到数据库
        //添加并判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOntSubjectName());
        if (existOneSubject == null){
            //没有相同的一级分类,进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOntSubjectName());//一级分类名称

            subjectService.save(existOneSubject);
        }
        //获取一级分类的id值
        String pid = existOneSubject.getId();
        //添加并判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }

    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }
    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


    }
}
