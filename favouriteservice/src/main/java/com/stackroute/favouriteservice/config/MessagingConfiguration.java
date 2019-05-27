package com.stackroute.favouriteservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

  private String exchangeName = "matches_exchange";
  private String matchesQueueName=  "matches_queue";

  @Bean
  DirectExchange directExchange(){
    return new DirectExchange(exchangeName);
  }

  @Bean
  Queue matchesQueue(){
    return new Queue(matchesQueueName,false);
  }

  @Bean
  public Jackson2JsonMessageConverter produceJackson2JsonMessageConverter(){
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(produceJackson2JsonMessageConverter());
    return rabbitTemplate;
  }

  @Bean
  Binding matchesBinding(Queue matchesQueue, DirectExchange directExchange){
    return BindingBuilder.bind(matchesQueue).to(directExchange).with("matches_routing");
  }
}
