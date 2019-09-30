package org.sakura.sys.dao;


import org.apache.ibatis.annotations.Mapper;
import org.sakura.common.pojo.Hotel;

@Mapper
public interface HotelMapper{

    Hotel findHotelById(String hotelId);


}
