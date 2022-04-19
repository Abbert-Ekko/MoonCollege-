package com.app.eduService.exception;

import com.app.eduService.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GolbalExceptionHandler {

    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.fail().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.fail().message("执行了ArithmeticException异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(MoonException.class)
    @ResponseBody
    public R error(MoonException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.fail().code(e.getCode()).message(e.getMessage());
    }

}
