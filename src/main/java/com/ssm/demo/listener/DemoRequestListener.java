package com.ssm.demo.listener;

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
 *
 * post提交产生乱码的原因：
 * post方式提交的数据是在请求体中，request对象接收到数据之后，放入request的缓冲区中。缓冲区就有编码（默认ISO-8859-1:不支持中文）.
 * 解决方案：将request的缓冲区的编码修改了即可。request.setCharacterEncoding("UTF-8");
 *
 * get提交产生乱码的原因:get方式提交的数据在请求行的url后面，地址栏上其实就已经进行一次URL的编码了.
 * 解决方案:将存入到request缓冲区中的值以ISO-8859-1的方式获取到，以UTF-8的方式进行解码。
 * 例如:String value = new String(name.getBytes("ISO-8859-1"),"UTF-8");
 */
public class DemoRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("request 销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        try {
            httpServletRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
