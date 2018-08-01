package com.ssm.demo.listener;

import com.ssm.demo.util.HttpMethod;
import com.ssm.demo.wrapper.RequestParametersWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 自定义requestListener
 */
public class DemoRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();

        String httpMethod = httpServletRequest.getMethod();

        logger.info("httpMethod is:" + httpMethod);

        if (HttpMethod.POST.getValue().equals(httpMethod)){

            try {
                httpServletRequest.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.info("set request characterEncoding fail",e);
                e.printStackTrace();
            }
        }


        /*logger.info(httpServletRequest.getScheme() + "://"+ httpServletRequest.getServerName() + ":"
                + httpServletRequest.getServerPort() + httpServletRequest.getContextPath());*/

        logger.info(httpServletRequest.getRequestURL() + "?" + httpServletRequest.getQueryString());

        logger.info("sessionId:" + httpServletRequest.getRequestedSessionId());

        //获取cookies
        Cookie[] cookies = httpServletRequest.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                logger.info("cookies:" + cookie.getName() + ":" + cookie.getValue());
            }
        }

        //使parameterMap可修改
        httpServletRequest = new RequestParametersWrapper(httpServletRequest);

        //获取parameter
        Map<String,String[]> parameters = httpServletRequest.getParameterMap();

        Set<String> keys =  parameters.keySet();

        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()){
            String key = keyIterator.next().toString();
            logger.info("parameter:" + key);
            String[] values = parameters.get(key);

            if(values != null){
                String[] newValues = new String[values.length];

                for(int i = 0; i < values.length; i++){

                    if(HttpMethod.GET.getValue().equals(httpMethod)){

                        try {
                            values[i] = new String(values[i].getBytes("ISO-8859-1"),"utf-8");
                            newValues[i] = values[i];
                        } catch (UnsupportedEncodingException e) {
                            logger.info("new String from ISO-8859-1 to UTF-8 fail",e);
                            e.printStackTrace();
                        }

                        logger.info("value:" + newValues[i]);
                    }

                }

//                parameters.put(key,newValues);

            }

        }
    }
}
