package org.sakura.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	平台管理员
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User2 {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String username;	//账号
	private String password;	//密码
	private Integer power;		//权限级别默认0:普通管理员
}
