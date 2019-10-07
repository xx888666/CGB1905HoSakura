package org.sakura.sys.controller;


import org.sakura.common.pojo.Hotel;
import org.sakura.common.vo.SysResult;
import org.sakura.sys.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hotelList")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/findHotelById")
    @ResponseBody
    public SysResult findHotelById(String hotelId) {

        Hotel hotel = hotelService.findHotelById(hotelId);

        return new SysResult(hotel);

    }





}
