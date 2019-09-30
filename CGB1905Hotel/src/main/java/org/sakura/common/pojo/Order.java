package org.sakura.common.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	订单信息
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer hotelId;		//酒店id
	private Integer clientId;		//客户信息
	private Integer typetId;		//房间类型
	private Integer homeId;			//房间编号
	private Date created;			//订单创建时间
	private Double cash; 			//押金
	
}
