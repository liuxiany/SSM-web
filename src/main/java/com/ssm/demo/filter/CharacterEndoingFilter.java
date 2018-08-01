package com.ssm.demo.filter;

import com.ssm.demo.wrapper.RequestParametersWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 自定义filter。彻底解决使用get方式提价中文乱码的问题
 *
 * post提交产生乱码的原因：
 * post方式提交的数据是在请求体中，request对象接收到数据之后，放入request的缓冲区中。缓冲区就有编码（默认ISO-8859-1:不支持中文）.
 * 解决方案：将request的缓冲区的编码修改了即可。request.setCharacterEncoding("UTF-8");
 *
 * get提交产生乱码的原因:get方式提交的数据在请求行的url后面，地址栏上其实就已经进行一次URL的编码了.
 * 解决方案:将存入到request缓冲区中的值以ISO-8859-1的方式获取到，以UTF-8的方式进行解码。
 * 例如:String value = new String(name.getBytes("ISO-8859-1"),"UTF-8");
 *
 * 编码设置参考：https://blog.csdn.net/kalision/article/details/46441081
 * 修改tomcat下server.xml的编码设置：https://blog.csdn.net/muzi1314_/article/details/73824719
 *
 * web.xml中配置的characterEncodingFiler对get方法提交的中文编码设置不起作用
 */
public class CharacterEndoingFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(CharacterEndoingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CharacterEndoingFilter init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("CharacterEndoingFilter dofilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String httpMethod = httpServletRequest.getMethod();

        RequestParametersWrapper requestParametersWrapper = new RequestParametersWrapper(httpServletRequest);

        logger.info("httpMethod:" + httpMethod);

        if("POST".equals(httpMethod)){
            requestParametersWrapper.setCharacterEncoding("UTF-8");
            servletResponse.setCharacterEncoding("UTF-8");

        }else if("GET".equals(httpMethod)) {

            Map<String, String[]> parametersMap = requestParametersWrapper.getParameterMap();

            Set<String> keys = parametersMap.keySet();

            Iterator<String> iterator = keys.iterator();

            while (iterator.hasNext()) {

                String key = String.valueOf(iterator.next());

                logger.info("key:" + key);

                String[] values = parametersMap.get(key);

                if (values != null && values.length > 0) {

                    String[] newValues = new String[values.length];

                    for (int i = 0; i < values.length; i++) {
                        try {
                            values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
                            newValues[i] = values[i];
                        } catch (UnsupportedEncodingException e) {
                            logger.info("new String from ISO-8859-1 to UTF-8 fail", e);
                            e.printStackTrace();
                        }

                        logger.info("value:" + newValues[i]);
                    }

                    parametersMap.put(key,newValues);

                }
            }

            requestParametersWrapper.setParameterMap(parametersMap);

        }

        filterChain.doFilter(requestParametersWrapper,servletResponse);

    }

    @Override
    public void destroy() {
        logger.info("CharacterEndoingFilter destory");
    }
}
