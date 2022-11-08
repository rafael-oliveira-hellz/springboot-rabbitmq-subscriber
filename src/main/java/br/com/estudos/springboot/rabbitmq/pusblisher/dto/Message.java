package br.com.estudos.springboot.rabbitmq.pusblisher.dto;

public class Message {
    private String message;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
