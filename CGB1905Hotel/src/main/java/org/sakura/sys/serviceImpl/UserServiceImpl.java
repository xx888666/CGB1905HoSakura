package org.sakura.sys.serviceImpl;

import java.util.UUID;

import org.sakura.common.pojo.User;
import org.sakura.sys.dao.UserMapper;
import org.sakura.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.jt.mapper.UserMapper;
//import com.jt.pojo.User;
//import com.jt.service.UserService;
//import com.jt.util.ObjectMapperUtil;
//
//import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * param:用户需要校验的数据
	 * type:校验的类型  1 username   2 phone   3 email
	 */
	@Override
	public boolean checkUsername(String username) {

		//根据用户名去数据库中查找有没有重复的用户名
		User user = userMapper.checkUsername(username);
		if (user == null) {//如果查到没有数据, 说明用户注册用的用户名可以使用
			return true;
		}
		return false;
	}

}
