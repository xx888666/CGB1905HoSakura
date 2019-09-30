//package org.sakura.common.interceptor;
//
//import com.jt.pojo.User;
//import com.jt.util.ObjectMapperUtil;
//import com.jt.util.UserThreadLocalUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import redis.clients.jedis.JedisCluster;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
////拦截器, 当用户点购物车时, 检验用户是否登录
//@Component
//public class UserInterceptor implements HandlerInterceptor{
//	private static final String TICKET = "JT_TICKET";
//
//	@Autowired
//	private JedisCluster jedisCluster;
//
//	/**
//	 * 实现用户的权限认证
//	 * 		用户不登录, 不允许访问涉密操作
//	 * 		重定向到用户登录界面
//	 *
//	 * 		如果用户登录, 则请求予以放行
//	 *
//	 * 方法说明:
//	 * 	1.boolean
//	 * 		true: 放行
//	 * 		false: 拦截 配合重定向使用
//	 * 实现步骤:
//	 * 	先判断用户是否登录
//	 * 	1. 获取用户cookie信息, 获取密钥
//	 * 	2. 从redis中获取数据
//	 */
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		//获取整个客户端中的cookie得到的是一个数组
//		Cookie[] cookies = request.getCookies();
//		String ticket = null;
//		//判断cookies是否为空
//		if (cookies.length > 0) {
//			//遍历cookies
//			for (Cookie cookie : cookies) {
//				//遍历时如果有和我们ticket名称相同的cookie, 说明找到了我们需要的cookie
//				if (TICKET.equals(cookie.getName())) {
//
//					ticket = cookie.getValue();
//					break;
//				}
//			}
//		}
//
//		if (!StringUtils.isEmpty(ticket)) {//如果ticket有值
//			//校验redis中是否有数据
//			String userJson = jedisCluster.get(ticket);
//			if (!StringUtils.isEmpty(userJson)) {
//				//实现用户信息的动态获取
//				User user = ObjectMapperUtil.toObject123(userJson, User.class);
//				//将用户信息上传到域, 在controller层用request.getAttribute获取
//				//request.setAttribute("JT_USER", user);
//				//利用工具api将user对象传入
//				UserThreadLocalUtil.set(user);
//				return true;
//			}
//		}
//
//		//如果用户没有登录, 需要重定向到登录界面  重定向和页面跳转的区别???
//		response.sendRedirect("/user/login.html");  //重定向到登录界面
//		return false;
//	}
//
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//
//		//防止内存溢出, 在方法运行完成, after拦截器运行
//		UserThreadLocalUtil.remove();//删除本地线程中的数据
//	}
//
//}
