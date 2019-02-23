package com.azra.demo.controller;

import com.azra.demo.domain.OrderDTO;
import com.azra.demo.domain.ServerResponse;
import com.azra.demo.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController extends  BaseController{

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(value = "/order/add" , consumes = {MediaType.APPLICATION_JSON_VALUE},
            method= {RequestMethod.POST, RequestMethod.GET })
    @CrossOrigin(origins = "http://localhost:4200")
    public ServerResponse<String> addOrder(@RequestBody OrderDTO order){
        if(order == null) return handleFailedResponse();
        orderService.save(order);
        return handleSuccessResponse();
    }

    @RequestMapping(value = "/order/findAll" , produces = {MediaType.APPLICATION_JSON_VALUE}, method= {RequestMethod.GET })
    @CrossOrigin(origins = "http://localhost:4200")
    public List<OrderDTO> findAllOrder(){
        List<OrderDTO> orderDtoList = orderService.findAll();
        return orderDtoList;
    }
}
