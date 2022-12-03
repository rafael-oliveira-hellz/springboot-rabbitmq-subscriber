package br.com.estudos.springboot.rabbitmq.pusblisher.amqp;

public interface AmqpSubscriber<T> {
    void subscriber(T t);
}
