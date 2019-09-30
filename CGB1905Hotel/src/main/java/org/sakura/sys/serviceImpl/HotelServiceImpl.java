package org.sakura.sys.serviceImpl;

import org.sakura.common.pojo.Hotel;
import org.sakura.common.pojo.Room;
import org.sakura.sys.dao.RoomMapper;
import org.sakura.sys.dao.HotelMapper;
import org.sakura.sys.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomMapper homeMapper;

    @Override
    public Hotel findHotelById(String hotelId) {
        //根据酒店id查找酒店的信息
        Hotel hotel = hotelMapper.findHotelById(hotelId);
        //根据酒店id查找酒店对应的房间信息
        List<Room> rooms = homeMapper.findRoomByHotelId(hotelId);

        //将房间信息封装在对应的酒店中
        hotel.setRoom(rooms);
        return hotel;
    }
}
