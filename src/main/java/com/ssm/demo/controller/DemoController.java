package com.ssm.demo.controller;

import com.ssm.demo.entity.User;
import com.ssm.demo.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
@RequestMapping("/demo/")
public class DemoController {
	
	private final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Resource(name = "demoService")
	private IDemoService demoService;
	
	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public String demo(String name){
		logger.info("enter demo");

		try {
			demoService.addUser(name);

		}catch (Exception e){
			logger.info("exception:", e);
		}


		logger.info("service is done");
		return "test";
	}

	@RequestMapping("testhtml.htm")
	public String testHtml(){
		return "testhtml";
	}
}
