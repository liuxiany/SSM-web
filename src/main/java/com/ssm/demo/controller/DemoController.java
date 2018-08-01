package com.ssm.demo.controller;

import com.ssm.demo.entity.User;
import com.ssm.demo.service.IDemoService;
import com.ssm.demo.util.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/demo/")
public class DemoController {
	
	private final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Resource(name = "demoService")
	private IDemoService demoService;
	
	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public String demo(String name) throws Exception{
		logger.info("enter demo");

		demoService.addUser(name);

		logger.info("service is done");
		return "test";
	}

	@RequestMapping(value = "testhtml.htm", method = RequestMethod.GET)
	public String testHtml(){
		return "testhtml";
	}

	@RequestMapping(value = "getAllUsers.htm", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers(){
		List<User> users = demoService.getAllUsers();
		return users;
	}

	@RequestMapping(value = "testAdvice.htm", method = RequestMethod.GET)
	public String testControllerAdvice() throws GlobalException {
		throw new GlobalException("0001","testControllerAdvice");
	}

	@RequestMapping(value = "getUserById.htm", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(String userId) throws GlobalException{
		User user = demoService.getUserById(userId);
		return user;
	}

	@RequestMapping(value = "getUsersByName.htm", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsersByName(String name){
		List<User> users = demoService.getUserByName(name);
		return users;
	}
}
