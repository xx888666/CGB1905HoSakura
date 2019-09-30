package org.sakura.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.sakura.common.pojo.Room;

import java.util.List;

@Mapper
public interface RoomMapper {

    List<Room> findRoomByHotelId(String hotelId);
}
