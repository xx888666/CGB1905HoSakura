package org.sakura.sys.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

//import org.apache.http.protocol.HTTP;
import org.sakura.common.pojo.User;
import org.sakura.common.vo.SysResult;
import org.sakura.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/register")
	public SysResult register(User user) {

		return null;
	}

}
