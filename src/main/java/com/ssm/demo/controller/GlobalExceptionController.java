package com.ssm.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * 注解@EnableWebMvc的作用相当于 <mvc:annotation-driven>
 */
@ControllerAdvice
//@EnableWebMvc
public class GlobalExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView GlobalExceptionHandler(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("erroCode","error");
        modelAndView.addObject("erroMessage","测试全局异常处理");
        return modelAndView;
    }

}
