package br.com.estudos.springboot.rabbitmq.pusblisher.service;

import br.com.estudos.springboot.rabbitmq.pusblisher.dto.Message;

public interface AmqpService {
    void sendToConsumer(Message message);
}
