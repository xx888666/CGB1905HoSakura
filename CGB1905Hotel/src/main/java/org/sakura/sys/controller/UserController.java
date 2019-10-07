package org.sakura.sys.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

//import org.apache.http.protocol.HTTP;
import org.sakura.common.exception.ServiceException;
import org.sakura.common.pojo.User;
import org.sakura.common.vo.SysResult;
import org.sakura.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

//import com.alibaba.dubbo.config.annotation.Reference;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.jt.pojo.User;
//import com.jt.service.DubboUserService;
//import com.jt.service.UserService;
//import com.jt.vo.SysResult;
//
//import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user/")
public class UserController {
	
	

	@Autowired
	private UserService userService;
	
	//@Autowired
	//private JedisCluster jedisCluster;
	
	/**
	 * 跨域请求, 返回值JSONPObject
	 * 由于JSONP跨域请求, 所以使用JSONPObject封装数据
	 * url:http://sso.jt.com/user/check/{param}/{type}
	 * 参数说明:
	 * 		1.param 用户校验的参数
	 * 		2.type 校验的字段  1 username  2 phone  3 email
	 * 返回值: SysResult
	 *//*
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback) {

		boolean flag = userService.checkUser(param, type);
		return new JSONPObject(callback, SysResult.success(flag));
	}

	*//**
	 * 利用ticket
	 * @param ticket
	 * @param callback
	 * @return
	 *//*
	@RequestMapping("/query/{ticket}")
	public JSONPObject findUserByTicket(@PathVariable String ticket, String callback) {
		String userJSON = jedisCluster.get(ticket);
		return new JSONPObject(callback, SysResult.success(userJSON));
	}*/


	//不考虑跨域请求, 用传统的方式实现
	//当用户在前端输入用户名时, 去数据库校验是否有重名
	@RequestMapping("/check/{username}")
	public SysResult checkUsername(@PathVariable String username) {

		boolean flag = userService.checkUsername(username);
		if (flag == false) {
			return SysResult.checkFalse();
		}
		return SysResult.checkSuccess();
	}



	//不考虑跨域请求
	//注册功能
	//@PostMapping("/register")
	@RequestMapping("/register")
	public SysResult register(User user, String username) {
		System.out.println(username);
		System.out.println(user);

		userService.register(user);

		SysResult sysResult = new SysResult(1, "注册成功", null);

		return sysResult;
	}


	/**
	 * 关于cookie的使用
	 * cookie.setPath("/"); 默认
	 * 	全部的请求都可见该cookie信息
	 * 	www.jt.com
	 * cookie.setPath("/aa");
	 * 	 只有在/aa路径下的url才能访问该cookie
	 * 	 www.jt.com/aa/a.html
	 *
	 * 关于Cookie的使用时间
	 * 	cookie.setMaxAge(>0); cookie的存活时间
	 * 	cookie.setMaxAge(0);  删除cookie
	 *  cookie.setMaxAge(-1); 关闭会话之后删除cookie
	 * @param user
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult login(User user,HttpServletResponse response) {
		//1.校验数据是否正确.获取密钥
		//String ticket = userService.findUserByUP(user);
		//将登录用户的用户名存入cookie
		String loginUsername = userService.findUserByUP(user);
		if(StringUtils.isEmpty(loginUsername)) {

			throw new ServiceException("用户名或密码错误");
		}
		//2.如果程序执行到这里.表示密钥有值.写入cookie
		Cookie cookie = new Cookie("HOTEL_TICKET", loginUsername);
		cookie.setMaxAge(7 * 24 * 3600);
		cookie.setPath("/");
		//设定cookie的共享!!!!! sso
		cookie.setDomain("");

		//将cookie写入客户端
		response.addCookie(cookie);
		return SysResult.success();
	}


}

