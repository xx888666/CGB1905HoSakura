package org.sakura.common.pojo;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * 	酒店信息
 * @author PHP
 *
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

	private List<Room> room;//封装酒店都有哪些类型的房间, 一对多  一个酒店对应多种类型的房间


	private Long hotelId;       //酒店的id(用UUID去生成)  主键
	private String hotelName;		//酒店名称
	private String details;		//酒店详情介绍
	private String address;		//酒店地址
	private Date created;		//酒店加入平台时间
	private Integer hotelTel;		//酒店电话
	private Integer status;		//酒店状态(营业还是暂停)
	private String image;		//酒店展示图片

}
