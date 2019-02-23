package com.azra.demo.message.converter;

import com.azra.demo.domain.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class OrderMessageConverter implements MessageConverter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderMessageConverter.class);

    ObjectMapper mapper;

    public OrderMessageConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object object, Session session)
            throws JMSException {

        TextMessage message = null;
        if (object instanceof OrderDTO) {
            OrderDTO order = (OrderDTO) object;
            String payload = null;
            try {
                payload = mapper.writeValueAsString(order);
                LOGGER.info("outbound json='{}'", payload);
            } catch (JsonProcessingException e) {
                LOGGER.error("error converting form person", e);
            }
            message = session.createTextMessage();
            message.setText(payload);
        }
        return message;
    }

    @Override
    public OrderDTO fromMessage(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        LOGGER.info("inbound json='{}'", payload);

        OrderDTO order = null;
        try {
            order = mapper.readValue(payload, OrderDTO.class);
        } catch (Exception e) {
            LOGGER.error("error converting to person", e);
        }

        return order;
    }
}