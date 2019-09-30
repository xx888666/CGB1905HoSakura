package org.sakura.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	酒店管理员表
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Employ {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String username;	//用户名
	private String password;	//密码
	private Integer status;		//权限级别默认为0
	private Integer hotelId;	//所属酒店
}
