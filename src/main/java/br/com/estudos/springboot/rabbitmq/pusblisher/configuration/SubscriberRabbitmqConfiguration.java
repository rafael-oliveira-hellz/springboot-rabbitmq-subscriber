package br.com.estudos.springboot.rabbitmq.pusblisher.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SubscriberRabbitmqConfiguration {
    @Value("${spring.rabbitmq.routing-key.subscriber}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange.subscriber}")
    private String exchangeName;

    @Value("${spring.rabbitmq.deadletter.subscriber}")
    private String deadLetterExchangeName;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Queue deadletter() {
        return new Queue(deadLetterExchangeName);
    }

    @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchangeName);
        args.put("x-dead-letter-routing-key", deadLetterExchangeName);

        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    public Binding bindingQueue() {
        return BindingBuilder.bind(queue()).to(exchange()).with(queueName);
    }

    @Bean
    public Binding bindingDeadletter() {
        return BindingBuilder.bind(deadletter()).to(exchange()).with(deadLetterExchangeName);
    }
}
