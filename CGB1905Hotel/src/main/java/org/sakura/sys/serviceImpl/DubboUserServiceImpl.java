//package org.sakura.sys.serviceImpl;
//
//import java.util.Date;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.DigestUtils;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.jt.mapper.UserMapper;
//import com.jt.pojo.User;
//import com.jt.service.DubboUserService;
//import com.jt.util.ObjectMapperUtil;
//
//import redis.clients.jedis.JedisCluster;
//
//@Service
//public class DubboUserServiceImpl implements DubboUserService {
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Autowired
//	private JedisCluster jedisCluster;
//
//	/**
//	 * 暂时使用用户的电话代替邮箱
//	 * md5加密:
//	 */
//	@Transactional    //控制事务
//	@Override
//	public void saveUser(User user) {
//		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//
//
//		user.setPassword(md5Pass)
//			.setEmail(user.getPhone())
//			.setCreated(new Date())
//			.setUpdated(user.getCreated());
//
//
//		userMapper.insert(user);
//
//	}
//
//
//
//	//根据用户名密码去数据库中查询数据
//		@Override
//		public String findUserByUP(User user) {
//			String ticket = null;
//			//为了与数据库一致, 需要将密码加密, 因为数据库中的密码时加密过得
//			String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//			user.setPassword(md5Pass);
//			QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
//
//			User userDB = userMapper.selectOne(queryWrapper);
//			if (userDB != null) {
//				//定义密钥
//				String uuid = UUID.randomUUID().toString();
//				//把uuid进行md5加密, 得到密钥			DigestUtils.md5DigestAsHex(字节);加密的API
//				ticket =  DigestUtils.md5DigestAsHex(uuid.getBytes());
//				//将数据库数据转化为json, 进行脱敏处理
//				userDB.setPassword("");//将密码字段设为空串, 进行脱敏处理
//				String userJSON = ObjectMapperUtil.toJSON(userDB);
//				//将用户信息缓存到redis
//				jedisCluster.setex(ticket, 7*27*3600, userJSON);
//			}
//
//			// TODO Auto-generated method stub
//			return ticket;
//		}
//}
