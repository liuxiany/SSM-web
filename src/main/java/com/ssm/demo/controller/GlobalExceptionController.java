package com.ssm.demo.controller;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

/**
 * 全局异常处理
 * 注解@EnableWebMvc的作用相当于 <mvc:annotation-driven>
 * 注解	@ControllerAdvice的类可以拥有@ExceptionHandler	、@InitBinder或@ModelAttribute注解的方法，
 * 并且这些方法会被应用至控制器类层次的所有@RequestMapping	方法上。
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

    @ModelAttribute(name = "controllerAdvice")
    public String helloWorld(){
        return "controllerAdvice:helloworld";
    }

    @InitBinder
    public	void initBinder(WebDataBinder binder)	{
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

}
