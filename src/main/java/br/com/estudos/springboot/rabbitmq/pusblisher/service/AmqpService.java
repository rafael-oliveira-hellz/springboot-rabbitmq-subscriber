package br.com.estudos.springboot.rabbitmq.pusblisher.service;

import br.com.estudos.springboot.rabbitmq.pusblisher.dto.MessageQueue;

public interface AmqpService {
    void sendToConsumer(MessageQueue message);
}
