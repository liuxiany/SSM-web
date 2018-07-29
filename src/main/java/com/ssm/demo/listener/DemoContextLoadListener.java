package com.ssm.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自定义contextListenr，只为了解此listener，与业务无关
 */
public class DemoContextLoadListener implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(DemoContextLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.print(System.currentTimeMillis());
        logger.info("容器初始化。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.print(System.currentTimeMillis());
        logger.info("容器销毁。。。");
    }
}
