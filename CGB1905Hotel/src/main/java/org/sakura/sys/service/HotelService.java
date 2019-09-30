package org.sakura.sys.service;


import org.sakura.common.pojo.Hotel;

public interface HotelService {

    Hotel findHotelById(String hotelId);
}
