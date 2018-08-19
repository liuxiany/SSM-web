package com.ssm.demo.controller;

import com.ssm.demo.entity.User;
import com.ssm.demo.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 参数上注解@ModelAttribute表明该方法参数的值将由model中取得。
     * 如果model 中找不到，那么该参数会先被实例化，然后被添加到model中。名称默认为类型的首字母小写。
     * 在model中存在以后，请求中所有名称匹配的参数都会填充到该参数中。这在Spring	MVC中被称为数据绑定
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public String demo(@ModelAttribute User user, BindingResult bindingResult) throws Exception{
		logger.info("enter demo");

		if(bindingResult.hasErrors()){
		    return "exception";
        }

		demoService.addUser(user.getName());
        logger.info("date:{}",user.getDate());
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
    public User showName() throws Exception{
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
	public Map<String,String> showRegularExpression(@PathVariable Map<String,String>  map) throws Exception{
	    return map;
    }

	/**
	 * 测试媒体类型
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "addUserByJson.htm",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public User addUserByJson(@RequestBody User user) throws Exception{
        return user;
    }

	/**
	 * 测试HttpEntity和@RequestHeader
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(value = "something.htm",method = RequestMethod.GET)
    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity,@RequestHeader HttpHeaders httpHeaders) throws Exception{
		String requestHeaders = requestEntity.getHeaders().getFirst("Cookie");
		byte[] requestBody = requestEntity.getBody();

        String host = httpHeaders.getFirst("Host");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyRequestHeader","MyValue");

		return new ResponseEntity<String>("Hello World:" + host + ",cookie:" + requestHeaders,responseHeaders, HttpStatus.CREATED);
	}

	/**
	 * 测试@ModelAttribute注解
	 * 当@ModelAttrubute注解注解在controller的方法上时，此方法会向model中添加属性，会在所有的@RequestMapping注解的方法之前执行。
	 * 默认的属性名为类型的首字母小写。如String-->string
	 * 一个控制器可以拥有数量不限的	@ModelAttribute	方法。同个控制器内的所有这些方法，都会在@RequestMapping方法之前被调用
	 * 	注解@ModelAttribute方法也可以定义在	@ControllerAdvice注解的类中，并且这些@ModelAttribute可以同时对许多控制器生效。
	 * 测试页面为test.jsp
	 * @return
	 */
	@ModelAttribute
	public String addModelAttribute(Model model) throws Exception{
		model.addAttribute("hello","helloworld");
		return "hahaha";
	}

	/**
	 * 测试@ModelAttribute注解注解在@RequestMapping方法上
	 * 方法的返回值将会被解释为model的一个属性，而非一个视图名。此时视图名将以视图命名约定来方式来决议，
	 * 与返回值为void的方法所采用的处理方法类似：路径为@RequestMapping的路径，文件名为方法名首字母小写。
     * model的名称为类型的首字母小写：User-->user
	 * @return
	 */
	@RequestMapping(value = "modelAttribute.htm",method = RequestMethod.GET)
	@ModelAttribute
	public User modelAttribute() throws Exception{
		User user = new User();
		user.setId("20180819");
		user.setName("modelAttributeUser");
		return user;
	}

    /**
     * 测试org.springframework.web.filter.HttpPutFormContentFilter
     * 使能通过httpServletRequest.getParameter()得到put和patch方式提交的参数
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "testPutFilter.htm",method = RequestMethod.PUT)
    @ResponseBody
	public String testPutFilter(HttpServletRequest httpServletRequest){
	    String userName = httpServletRequest.getParameter("username");
	    return userName;
    }

    /**
     * 测试@CookieValue
     * @param cookie
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "getCookie.htm",method = RequestMethod.GET)
	@ResponseBody
	public String getCookie(@CookieValue("JSESSIONID") String cookie) throws Exception{
		return cookie;
	}

}
