package com.ssm.demo.controller;

import com.ssm.demo.entity.User;
import com.ssm.demo.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/demo/")
public class DemoController {
	
	private final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Resource(name = "demoService")
	private IDemoService demoService;

	/**
	 * 测试事务回滚
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public String demo(String name) throws Exception{
		logger.info("enter demo");

		demoService.addUser(name);

		logger.info("service is done");
		return "test";
	}

	/**
	 * 测试html解析和jsp解析并存
	 * @return
	 */
	@RequestMapping(value = "testhtml.htm", method = RequestMethod.GET)
	public String testHtml(){
		return "testhtml";
	}

	/**
	 * 测试使用fastjson替换jackson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAllUsers.htm", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() throws Exception{
		List<User> users = demoService.getAllUsers();
		return users;
	}

	/**
	 * 测试全局异常处理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "testAdvice.htm", method = RequestMethod.GET)
	public String testControllerAdvice() throws Exception {
		throw new Exception();
	}

	/**
	 *测试全局异常处理能否处理从service抛出的异常
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserById.htm", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(String userId) throws Exception{
		User user = demoService.getUserById(userId);
		return user;
	}

	/**
	 * 测试form使用get方式提交中文乱码的解决
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUsersByName.htm", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsersByName(String name) throws Exception{
		List<User> users = demoService.getUserByName(name);
		return users;
	}

    /**
     * 路径样式的匹配:
     * 1.URI模板变量的数目和通配符数量的总和最少的那个路径模板更准确
     * 2.如果两个模板的URI模板数量和通配符数量总和一致，则路径更长的那个模板更准确
     * 3.如果两个模板的数量和长度均一致，则那个具有更少通配符的模板是更加准确的
     * 可以概括为，哪个url更详细，更细节，更具体就匹配哪一个
     * @return
     */
    @RequestMapping(value = "/*/*.htm")
    @ResponseBody
    public User showName(){
        logger.info("hehe");
        User user = new User("星号");
        return user;
    }

	/**
	 * 测试URI模板
     * URI模板可以从类级别和方法级别的	@RequestMapping	注解获取数据
     * 	注解@PathVariable可以被应用于所有简单类型的参数上，比如int、long、Date等类型。
     * 	Spring 会自动地帮你把参数转化成合适的类型，如果转换失败，就抛出一 个	TypeMismatchException。
	 * @param userId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "{userId}/{userName}.htm", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUserInfo(@PathVariable String userId, @PathVariable("userName") String name
									   ,@PathVariable Map<String,Object> map) throws Exception{
		User user = new User(name);
		user.setId(userId);

		map.put("user",user);

		return map;
	}

    /**
     * 测试@PathVariable支持正则表达式
     * 语法是	{varName:regex}	， 其中第一部分定义了变量名，第二部分就是你所要应用的正则表达式
     * @param map
     * @return
     */
    @RequestMapping(value = "showRegularExpression/{firstName:[a-z]{3}}/{lastName:[A-Z]{3}}.htm", method = RequestMethod.GET)
    @ResponseBody
	public Map<String,String> showRegularExpression(@PathVariable Map<String,String>  map){
	    return map;
    }

}
