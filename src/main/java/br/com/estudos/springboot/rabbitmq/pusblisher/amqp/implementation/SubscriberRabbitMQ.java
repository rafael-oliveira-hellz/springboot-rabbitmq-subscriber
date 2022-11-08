package br.com.estudos.springboot.rabbitmq.pusblisher.amqp.implementation;

import br.com.estudos.springboot.rabbitmq.pusblisher.amqp.AmqpSubscriber;
import br.com.estudos.springboot.rabbitmq.pusblisher.dto.Message;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubscriberRabbitMQ implements AmqpSubscriber<Message> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.subscriber}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key.subscriber}")
    private String queue;

    @Override
    public void subscriber(Message message) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
