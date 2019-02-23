package com.azra.demo.service;

import com.azra.demo.domain.OrderDTO;
import com.azra.demo.repo.entity.Order;
import com.azra.demo.repo.repo.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    public OrderDTO save(OrderDTO orderDTO){

       Order o =  orderRepository.save(mapOrderDtoToEntity(orderDTO));
        orderDTO.setModifiedTime(convertNowToUTC());
        orderDTO.setCreatedTime(convertNowToUTC());
       return mapOrderEntityToDto(o);
    }

    private Long convertNowToUTC(){
        long timeInMillis = System.currentTimeMillis();
//        TimeZone gmt = TimeZone.getTimeZone("GMT");
//        int offset = gmt.getOffset(timeInMillis);
//        offset = offset/100;
        return timeInMillis;
    }

    public List<OrderDTO> findAll(){
        //Iterable<Order> o =  orderRepository.findAll();
        List<OrderDTO> oList = new ArrayList<>();
        OrderDTO o = new OrderDTO();
        o.setOrderId(1l);
        o.setDescription("first");
        o.setOrderStatus("Placed");
        o.setCreatedTime(847892323327l);
        o.setModifiedTime(87649629386l);
        oList.add(o);
        o = new OrderDTO();
        o.setOrderId(2l);
        o.setDescription("second");
        o.setOrderStatus("Shipped");
        o.setCreatedTime(8478923293492327l);
        o.setModifiedTime(876487933329386l);
        oList.add(o);
        //mapOrderDtoList(o);
        return oList;
    }

    private List<OrderDTO> mapOrderDtoList(Iterable<Order> o) {
        List<OrderDTO> oList = new ArrayList<>();
        Iterator<Order> it = o.iterator();
        while(it.hasNext()) {
            oList.add(mapOrderEntityToDto(it.next()));
        }
        return oList;
    }

    public void updateStatus(OrderDTO orderDTO){
        //orderRepository.
    }

    public Order mapOrderDtoToEntity(OrderDTO order) {
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(orderDto, Order.class);
        Order dto = new Order();
        if(order.getOrderId() != null) {
            dto.setOrderId(order.getOrderId());
        }
        dto.setDescription(order.getDescription());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setCreatedTime(order.getCreatedTime());
        dto.setModifiedTime(order.getModifiedTime());
        return dto;
    }

    public OrderDTO mapOrderEntityToDto(Order order) {
//        ModelMapper modelMapper = new ModelMapper();
//
//        return modelMapper.map(order, OrderDTO.class);
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setDescription(order.getDescription());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setCreatedTime(order.getCreatedTime());
        dto.setModifiedTime(order.getModifiedTime());
        return dto;

    }
}
