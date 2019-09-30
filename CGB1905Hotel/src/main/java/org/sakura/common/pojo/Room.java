package org.sakura.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	房间信息
 * 	展示每个酒店都有什么类型的房间
 *
 *
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	private String id;//自增id
	private String hotelId;	//所属酒店id, 应该设成外键, 是Hotel表的主键
	private String name;		//房间类型名称
	private String price;		//房间价格
	private String sum;		//房间总数
	private String nowRoom;		//房间现在剩余的数量

}
