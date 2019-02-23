package com.azra.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@Configuration
public class JMSConfig {
    Logger logger = LoggerFactory.getLogger(JMSConfig.class);

    @Autowired
    JmsProperties props;

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        logger.info("JMS Config url:"+props.getUrl()+" , userName:"+props.getUserName()+" , password:"+props.getPassword());
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(props.getUrl());
        activeMQConnectionFactory.setUserName(props.getUserName());
        activeMQConnectionFactory.setPassword(props.getPassword());

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName(props.getInboundQueueName());
        //To enable dealing with topics need to explicitly tell spring
        jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setConcurrency("1-1");
        containerFactory.setPubSubDomain(true);
        return containerFactory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
