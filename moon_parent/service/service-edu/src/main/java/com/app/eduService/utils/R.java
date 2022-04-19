package com.app.eduService.utils;

import com.app.eduService.entity.EduTeacher;
import lombok.Data;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;
    private Map<String, Object> info = new HashMap<String, Object>();
    private EduTeacher teacher;

    private R() {
    }

    ;

    public static R success() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");

        return r;
    }

    public static R fail() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");

        return r;
    }

    public R isSuccess(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(Object data) {
        this.setData(data);
        return this;
    }

    public void setData(Object data, EduTeacher teacher) {
        this.data = data;
        this.teacher = teacher;
    }

//    public R data(Object data, EduTeacher teacher){
//        this.setData(data, teacher);
//        return this;
//    }

    public R info(HashMap<String, Object> info) {
        this.setData(info);
        return this;
    }


}
