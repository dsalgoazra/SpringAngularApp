package com.azra.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JmsProperties {
    @Value("${spring.activemq.broker-url}")
    private String url;

    @Value("${spring.activemq.user}")
    private String userName;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.jms.queue.inbound.name}")
    private String inboundQueueName;

    @Value("${spring.jms.queue.outbound.name}")
    private String outboundQueueName;

    @Value("${spring.jms.topic.inbound.name}")
    private String inboundTopicName;



    @Value("${spring.websocket.relayport}")
    private int relayPort;

    @Value("${spring.websocket.protocol}")
    private String websocketProtocol;

    @Value("${spring.websocket.relayhost}")
    private String wsHost;

    public String getWebsocketProtocol() {
        return websocketProtocol;
    }

    public String getWsHost() {
        return wsHost;
    }

    public int getRelayPort() {
        return relayPort;
    }



    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getInboundQueueName() {
        return inboundQueueName;
    }

    public String getOutboundQueueName() {
        return outboundQueueName;
    }

    public String getInboundTopicName() {
        return inboundTopicName;
    }
}
