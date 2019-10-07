package org.sakura.sys.serviceImpl;

import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.sakura.common.exception.ServiceException;
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


	@Override
	public void register(User user) {
		//将用户密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);

		int i = userMapper.register(user);
		//如果注册出现错误, 就向前端发送异常, 系统维护中
		if (i < 1) {
			throw new SecurityException("系统维护中");
		}
	}

	@Override
	public String findUserByUP(User user) {
		String ticket = null;
		//为了与数据库数据一致,需要将密码加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);

		User userDB = userMapper.findUserByUp(user);

		//根据用户名和密码校验数据
		if(userDB !=null) {
			//将数据库数据转化为json保存到redis中
			String uuid = UUID.randomUUID().toString();
			ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
			//进行脱敏处理   100xxx0311  将用户的密码设为空串
			//userDB.setPassword("123456你信吗??");
			userDB.setPassword("");

			//暂时先不用redis实现单点登录, 暂时只用cookie
//			String userJSON =
//					ObjectMapperUtil.toJSON(userDB);
//			jedisCluster.setex(ticket,7*24*3600, userJSON);
			return user.getUsername();
		} else {
			//如果userDB为空, 说明没有找到, 抛出一个异常用户名或密码错误
			throw new ServiceException("用户名或密码错误");
		}
		//先不return ticket, 先返回前端一个用户的用户名
		//return ticket;
	}

}
