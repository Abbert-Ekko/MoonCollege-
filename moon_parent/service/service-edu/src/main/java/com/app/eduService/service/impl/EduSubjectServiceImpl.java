package com.app.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.app.eduService.entity.EduSubject;
import com.app.eduService.entity.excel.SubjectData;
import com.app.eduService.entity.subject.One;
import com.app.eduService.entity.subject.Two;
import com.app.eduService.listener.subjectExcelListener;
import com.app.eduService.mapper.EduSubjectMapper;
import com.app.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new subjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<One> getAllOneTwoSubject() {
        //查询出所有一级分类 parent_id = 0
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> oneSduSubjects = baseMapper.selectList(oneWrapper);

        //查询出所有二级分类 parent_id != 0
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> twoSduSubjects = baseMapper.selectList(twoWrapper);

        List<One> finalSubjectList = new ArrayList<>();

        //封装一级分类
        for (int i = 0; i < oneSduSubjects.size(); i++) {
            EduSubject eduSubject = oneSduSubjects.get(i);

            //将eduSubject值放入One
            One one = new One();
            /*one.setId(eduSubject.getId());
            one.setTitle(eduSubject.getTitle());*/
            BeanUtils.copyProperties(eduSubject,one);

            //封装二级分类
            List<Two> twoList = new ArrayList<>();
            for (int j = 0; j < twoSduSubjects.size(); j++) {
                EduSubject eduSubject1 = twoSduSubjects.get(j);
                if (eduSubject1.getParentId().equals(eduSubject.getId())){
                    Two two = new Two();
                    BeanUtils.copyProperties(eduSubject1,two);
                    twoList.add(two);
                }
            }
            one.setChildren(twoList);

            //将One添加到finalSubjectList
            finalSubjectList.add(one);
        }



        return finalSubjectList;
    }


}
