package br.com.estudos.springboot.rabbitmq.pusblisher.api;

import br.com.estudos.springboot.rabbitmq.pusblisher.dto.Message;
import br.com.estudos.springboot.rabbitmq.pusblisher.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpApi {

    @Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody Message message) {
        service.sendToConsumer(message);
        System.out.println("Message sent to consumer");
    }

}
