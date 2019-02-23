package com.azra.demo.service;

import com.azra.demo.message.AMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingServiceImpl extends BaseService {

    @Autowired
    AMessageSender producer;

    public void addMessage(String topic,
                             String message ){
        if(topic == null || topic.isEmpty()) {
            logger.info("adding message.. for queue");
            producer.addMessageToQueue(message);
        } else {
            logger.info("adding message.. for topic:" + topic);
            producer.addMessageToTopic(message);
        }
    }

    public List<String> fetchAlMessages(){
      return null;
    }
}
