package com.app.eduService.controller;


import com.app.eduService.entity.EduTeacher;
import com.app.eduService.service.EduTeacherService;
import com.app.eduService.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("/eduService/edu-teacher")
@CrossOrigin(origins = "*")
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/list")
    public R getAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.success().data(list);
    }

    @PostMapping("/add")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        return save ? R.success() : R.fail();
    }


    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id) {
        Boolean flag = eduTeacherService.removeById(id);
        return flag ? R.success() : R.fail();
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        EduTeacher byId = eduTeacherService.getById(id);
        return R.success().data(byId);
    }

    @PutMapping("/edit")
    public R editTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean update = eduTeacherService.updateById(eduTeacher);
        return update ? R.success() : R.fail();
    }


    @PostMapping("/{current}/{size}")
    public R pageListTeacher(@PathVariable Integer current, @PathVariable Integer size, @RequestBody(required = false) EduTeacher eduTeacher) {
        IPage<EduTeacher> page = eduTeacherService.getPage(current, size, eduTeacher);
//        return R.success().data(eduTeacherService.page(page,null));
        return R.success().data(page);
    }


}

