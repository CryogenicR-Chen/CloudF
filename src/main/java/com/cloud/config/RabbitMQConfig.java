package com.cloud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String CANAL_QUEUE = "canal_mq_queue";
    public static final String DIRECT_EXCHANGE = "canal_mq_exchange";
    public static final String ROUTING_KEY = "";

    @Bean
    public Queue canalMqQueue(){
        return new Queue(CANAL_QUEUE,true);
    }


    @Bean
    public DirectExchange directExchange(){
       return new DirectExchange(DIRECT_EXCHANGE);
    }


    @Bean
    public Binding EsBinding() {
        return BindingBuilder.bind(canalMqQueue()).to(directExchange()).with(ROUTING_KEY);
    }

}
