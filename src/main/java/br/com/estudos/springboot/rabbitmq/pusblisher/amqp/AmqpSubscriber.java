package br.com.estudos.springboot.rabbitmq.pusblisher.amqp;

import br.com.estudos.springboot.rabbitmq.pusblisher.dto.Message;

public interface AmqpSubscriber<T> {
    void subscriber(T t);
}
