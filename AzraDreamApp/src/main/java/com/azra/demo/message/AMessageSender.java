package com.azra.demo.message;

import com.azra.demo.config.JmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * A queue means a message goes to one and only one possible subscriber. A topic goes to each and every subscriber. \
 * Topics are for the publisher-subscriber model, while queues are for point-to-point. ...
 * The same published message is received by all consuming subscribers.
 */
@Component
public class AMessageSender {

    private Logger logger = LoggerFactory.getLogger(AMessageSender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    JmsProperties props;


    @SendTo("${spring.jms.topic.inbound.name}")
    public String addMessageToTopic(final String myMessage){
        String res = "";
        try {
            logger.info("Message Received by Producer sending to topic : " + myMessage);
            jmsTemplate.convertAndSend( myMessage);
        } catch(Exception e) {
            logger.error("Exception "+e);
        }
        return res;
    }

    @SendTo("${spring.jms.queue.inbound.name}}")
    public String addMessageToQueue(final String myMessage){
        String res = "";
        try {
            logger.info("Message Received by Producer sending to queue : " + myMessage);
            jmsTemplate.convertAndSend( myMessage);
        } catch(Exception e) {
            logger.error("Exception "+e);
        }
        return res;
    }
}
