package com.ssm.demo.controller;

import org.springframework.core.MethodParameter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.text.SimpleDateFormat;

/**
 * 全局异常处理
 * 注解@EnableWebMvc的作用相当于 <mvc:annotation-driven>
 * 注解	@ControllerAdvice的类可以拥有@ExceptionHandler	、@InitBinder或@ModelAttribute注解的方法，
 * 并且这些方法会被应用至控制器类层次的所有@RequestMapping	方法上。
 *
 * 实现ResponseBodyAdvice接口的目的：如果方法使用@ResponseBody注解，那interceptor的postHandle方法在被调用前
 * ，HttpMessageConverter会把信息写回响应中。这样拦截器就无法再改变响应了。
 */
@ControllerAdvice
//@EnableWebMvc
public class GlobalExceptionController implements ResponseBodyAdvice {

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

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return null;
    }
}
