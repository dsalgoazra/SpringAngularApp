package com.azra.demo.controller;

import com.azra.demo.domain.*;
import com.azra.demo.service.MessagingServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController extends  BaseController{

    @Autowired
    MessagingServiceImpl service;

    @RequestMapping(value ="/messaging/add" , method= {RequestMethod.POST, RequestMethod.GET },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public void addMessage(@RequestBody Message message){
        logger.info("adding message..");
        service.addMessage(message.getTopic(),message.getMessage());
    }

    public List<String> getMessages(){
       return null;
    }
}
