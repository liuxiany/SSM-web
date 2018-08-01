package com.ssm.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 自定义httpSessionListener
 */
public class DemoSessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(DemoSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        HttpSession httpSession = httpSessionEvent.getSession();

        logger.info("sessionId:" + httpSession.getId());

        long lastAccessedTime = httpSession.getLastAccessedTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        logger.info("lastAccessedTime:" + simpleDateFormat.format(new Date(lastAccessedTime)));

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("sessionDestroyed");
    }
}
