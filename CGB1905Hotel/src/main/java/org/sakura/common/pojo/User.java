package org.sakura.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	用户信息表
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Hotel hotel;//入库时忽略, 当用户入住之后, 将他住的酒店信息封装到他自己的User对象中

	private Integer id;			//用户id  用uuid去生成   主键
	private String username;	//用户名
	private String password;	//密码
	private String name;		//真实姓名
	private String hotelId;	//入住酒店-赋值时先赋null,用户入住时再将酒店信息填入
	private Integer idNumber;	//身份证号
	private String phone;		//电话
}
