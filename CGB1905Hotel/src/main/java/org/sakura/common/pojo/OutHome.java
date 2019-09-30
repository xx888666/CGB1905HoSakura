package org.sakura.common.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	退房表
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class OutHome {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer clientId;	//客户id
	private Integer homeId;		//酒店id
	private Date time;			//退房时间
}
