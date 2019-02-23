package com.azra.demo.message;

import com.azra.demo.config.JmsProperties;
import com.azra.demo.service.OrderServiceImpl;
import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class AMessageListener {

    private Logger logger = LoggerFactory.getLogger(AMessageListener.class);

    @Autowired
    JmsProperties props;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    MessageConverter messageConverter;

    @JmsListener(destination = "azra-queue-inbound" )
    public void receiveMessages(final Message myMessage){
        String myData = "";
        String res = "";
        try {
            logger.info("Message Received For Q" + myMessage);


            if (myMessage instanceof TextMessage) {
                TextMessage tm = (TextMessage) myMessage;
                myData = tm.getText();
                logger.info(myData);
                res = "Message Acknowledged : "+myData;
            }

        } catch(JMSException e) {
            logger.error("Exception "+e);
        }
    }

    @JmsListener(destination = "azra-topic-inbound" )//TODO: Load these values from the properties files
    public String receiveMessagesFromTopic(final Message myMessage){
        String myData = "";
        String res = "";
        try {
            logger.info("Message Received For Topic" + myMessage);
            if (myMessage instanceof TextMessage) {
                TextMessage tm = (TextMessage) myMessage;
                myData = tm.getText();
                logger.info(myData);
                res = "Message Acknowledged : "+myData;
            }

        } catch(JMSException e) {
            logger.error("Exception "+e);
        }
        return res;

    }
}
