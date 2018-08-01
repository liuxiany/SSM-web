package com.ssm.demo.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写HttpServletRequestWrapper，彻底解决用get提交中文，出现乱码的问题
 * 使其能在filter中，能对parameter做修改，否则会报No modifications are allowed to a locked ParameterMap
 * 参考https://blog.csdn.net/baidu_27062827/article/details/77097011
 */
public class RequestParametersWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameters;

    public RequestParametersWrapper(HttpServletRequest request) {
        super(request);
        this.parameters = new HashMap<String,String[]>(request.getParameterMap());
    }

    @Override
    public Map<String,String[]> getParameterMap(){
        return parameters;
    }

    public void setParameterMap(Map<String,String[]> parameterMap){
        this.parameters = parameterMap;
    }

}
