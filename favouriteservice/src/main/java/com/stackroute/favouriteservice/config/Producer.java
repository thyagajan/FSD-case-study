package com.stackroute.favouriteservice.config;

import com.stackroute.rabbitmq.domain.MatchesDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  private DirectExchange directExchange;
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public Producer(DirectExchange directExchange, RabbitTemplate rabbitTemplate) {
    this.directExchange = directExchange;
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMatchesToRabbitMQ(MatchesDTO matchesDTO){
    rabbitTemplate.convertAndSend(directExchange.getName(),"matches_routing",matchesDTO);
  }
}
