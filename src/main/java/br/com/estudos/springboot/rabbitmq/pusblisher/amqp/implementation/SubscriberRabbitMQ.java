package br.com.estudos.springboot.rabbitmq.pusblisher.amqp.implementation;

import br.com.estudos.springboot.rabbitmq.pusblisher.amqp.AmqpSubscriber;
import br.com.estudos.springboot.rabbitmq.pusblisher.dto.MessageQueue;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubscriberRabbitMQ implements AmqpSubscriber<MessageQueue> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.subscriber}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key.subscriber}")
    private String queue;

    @Override
    public void subscriber(MessageQueue message) {
        try {
            rabbitTemplate.convertAndSend(exchange, queue, message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
