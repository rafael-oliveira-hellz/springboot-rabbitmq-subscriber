package br.com.estudos.springboot.rabbitmq.pusblisher.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SubscriberRabbitmqConfiguration {
    @Value("${spring.rabbitmq.routing-key.subscriber}")
    private String routingKey;

    @Value("${spring.rabbitmq.exchange.subscriber}")
    private String exchange;

    @Value("${spring.rabbitmq.dead-letter.subscriber}")
    private String deadLetter;

    @Value("${spring.rabbitmq.parking-lot.subscriber}")
    private String parkingLot;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(routingKey)
                .deadLetterExchange(exchange)
                .deadLetterRoutingKey(deadLetter)
                .build();
    }

    @Bean
    Queue deadletter() {
        return QueueBuilder.durable(deadLetter)
                .deadLetterExchange(exchange)
                .deadLetterRoutingKey(routingKey)
                .build();
    }

    @Bean
    Queue parkingLot() {
        return new Queue(parkingLot);
    }


    @Bean
    Binding bindingQueue() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    Binding bindingDeadletter() {
        return BindingBuilder.bind(deadletter()).to(exchange()).with(deadLetter);
    }

    @Bean
    Binding bindingParkingLot() {
        return BindingBuilder.bind(parkingLot()).to(exchange()).with(parkingLot);
    }
}
