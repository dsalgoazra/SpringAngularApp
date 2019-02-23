package com.azra.demo.exclude;

import com.azra.demo.config.JmsProperties;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;

/**
 * STOMP over WebSocket
 * STOMP, an acronym for Simple Text Oriented Messaging Protocol, is a simple HTTP-like protocol for interacting with
 * any STOMP message broker. Any STOMP client can interact with the message broker and be interoperable among languages
 * and platforms.

 */

@Component
@EnableWebSocket
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {

    @Autowired
    JmsProperties props;

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/azra") //
                .setRelayHost(props.getWsHost()) //
                .setRelayPort(props.getRelayPort()) //
                .setClientLogin(props.getUserName()) //
                .setClientPasscode(props.getPassword()) //
        ;
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        final BrokerService broker = new BrokerService();
        broker.addConnector(props.getWebsocketProtocol()+"://"+props.getWsHost()+":"+props.getRelayPort());

        broker.setPersistent(false);
        final ManagementContext managementContext = new ManagementContext();
        managementContext.setCreateConnector(true);
        broker.setManagementContext(managementContext);

        return broker;
    }
}
