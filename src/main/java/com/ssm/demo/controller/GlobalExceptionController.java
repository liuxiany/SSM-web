package com.ssm.demo.controller;

import com.ssm.demo.util.GlobalException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * 注解@EnableWebMvc的作用相当于 <mvc:annotation-driven>
 */
@ControllerAdvice
//@EnableWebMvc
public class GlobalExceptionController {

    @ExceptionHandler(value = GlobalException.class)
    public ModelAndView ExceptionHandler(GlobalException gex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("erroCode",gex.getErrCode());
        modelAndView.addObject("erroMessage",gex.getErrMsg());
        return modelAndView;
    }
}
