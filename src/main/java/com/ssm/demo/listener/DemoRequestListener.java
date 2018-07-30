package com.ssm.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();

        /*logger.info(httpServletRequest.getScheme() + "://"+ httpServletRequest.getServerName() + ":"
                + httpServletRequest.getServerPort() + httpServletRequest.getContextPath());*/

        logger.info(httpServletRequest.getRequestURL() + "?" + httpServletRequest.getQueryString());

        logger.info("sessionId:" + httpServletRequest.getRequestedSessionId());

        //获取cookies
        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies){
            logger.info("cookies:" + cookie.getName() + ":" + cookie.getValue());
        }

        //获取parameter
        Map<String,String[]> parameters = httpServletRequest.getParameterMap();

        Set<String> keys =  parameters.keySet();

        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()){
            String key = keyIterator.next().toString();
            logger.info("parameter：" + key);
            String[] values = parameters.get(key);

            for(String value : values){
                logger.info("value:" + value);
            }
        }
    }
}
