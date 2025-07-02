package com.uds.project.service_notification.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
//Cette classe configure le système de WebSocket avec Spring Boot en activant le support du protocole STOMP (un protocole de messagerie léger sur WebSocket).
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {//implements WebSocketMessageBrokerConfigurer Interface que l’on implémente pour personnaliser la configuration WebSocket/STOMP.

    //registerStompEndpoints() :C’est une méthode obligatoire quand tu configures un serveur WebSocket STOMP. Elle permet de dire à Spring : "Voici les URLs sur lesquelles les clients peuvent établir une connexion WebSocket."
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {//StompEndpointRegistry est une classe de configuration fournie par Spring qui permet de déclarer les points d’entrée WebSocket pour les clients qui veulent se connecter via le protocole STOMP.
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();//registry.addEndpoint("/ws") : Crée un endpoint WebSocket accessible à l’URL /ws. C’est l’adresse à laquelle les clients vont se connecter. .setAllowedOriginPatterns("*") : Permet les connexions CORS depuis n’importe quelle origine (site web). En prod, on peut mettre par exemple .setAllowedOriginPatterns("https://mon-site.com") pour restreindre.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
