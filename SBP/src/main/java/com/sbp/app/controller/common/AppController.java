package com.sbp.app.controller.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbp.app.config.DefaultConfig;
/**
 * Servlet四大作用域：
 * - application(ServletContext)
 * 		生命周期：当Web服务被加载至容器时，创建一个整个web应用都可见的application对象ServletContext,ServletContext是代表整个web应用对象,容器销毁ServletContext也随之销毁
 * 		作用范围：整个Web
 * 		作用：       存储公共数据，存储至应用上下文
 * 
 * - sessoion(HttpSession)
 * 		生命周期：在第一次调用request.getSession()时被创建，每次调用首先服务器会检查是否已经存在session，如果存在直接返回，如果不存在则创建新的session并返回
 * 		作用范围：一次会话，用户访问网站至用户关闭浏览器
 * 		作用：       保存登录的用户信息、购物车信息等
 * 
 * - request(HttpServletContext)
 * 		生命周期：调用service方法之前被服务器创建并传入service方法，一次请求结束，request结束
 * 		作用范围：整个请求链，请求转发
 * 		作用：       整个请求中需要传递的参数，比如Servlet处理好数据交给jsp显示，参数就可以放至request中
 * 
 * - pageContext(PageContext)
 * 		生命周期：对jsp请求时开始，响应结束时销毁，此对象是在jsp中的
 * 		作用范围：整个jsp页面
 * 		作用：       用于页面的展示
 * 
 * - JSP中的九大隐式对象
 * 		application
 * 		session			可以获取会话中存储的信息
 * 		request			可以获取请求中携带的参数
 * 		pageContext		获取request等 对象并存入当前JSP中，也就是page作用域
 * 		response		响应对象
 * 		out				响应字符输出流
 * 		page			当前jsp
 * 		config			JSP的配置信息
 * 		exception		代表的是异常信息(当页面isErrorPage="true"时有效)
 * 
 * @author weiqz
 *
 */
@RestController // @Controller + @ResponseBody 的结合体：此注解修饰类 该类下所有方法全部返回jSON格式
public class AppController {
	// 读取配置文件的配置信息
	@Autowired
	private Environment env;// 读取配置文件属性的类
	@Value("${server.port}")// 注解方式注入配置属性
	private String port;
	
	@Autowired
	private DefaultConfig config;
	
	
	@GetMapping("/") // @RequestMapping(method=RequestMethod.GET)的简写版
	public String index() {
		return "Hello SBP!";
	}
	
	@GetMapping("/getServerPort")
	public List<Map<String,Object>> getServerPort() {
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		Map<String,Object> m = new HashMap<String,Object>();
		
		m.put("server.port", env.getProperty("server.port"));
		//m.put("default.email.serverStartTime", env.getProperty("default.email.serverStartTime"));
		m.put("default.email.serverStartTime", config.getServerStartTime());
		//m.put("default.email.enabled", env.getProperty("default.email.enabled"));
		m.put("default.email.enabled", config.getEnabled());
		//m.put("default.email.defaultSubject", env.getProperty("default.email.defaultSubject"));
		m.put("default.email.defaultSubject", config.getDefaultSubject());
		
		l.add(m);
		return l;// 读取不到返回空字符串
	}
	
}
