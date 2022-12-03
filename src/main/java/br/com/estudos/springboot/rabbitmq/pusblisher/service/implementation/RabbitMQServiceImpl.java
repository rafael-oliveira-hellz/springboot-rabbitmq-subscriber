package br.com.estudos.springboot.rabbitmq.pusblisher.service.implementation;

import br.com.estudos.springboot.rabbitmq.pusblisher.amqp.AmqpSubscriber;
import br.com.estudos.springboot.rabbitmq.pusblisher.dto.MessageQueue;
import br.com.estudos.springboot.rabbitmq.pusblisher.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpSubscriber<MessageQueue> amqpSubscriber;

    @Override
    public void sendToConsumer(MessageQueue message) {
        amqpSubscriber.subscriber(message);
    }
}
