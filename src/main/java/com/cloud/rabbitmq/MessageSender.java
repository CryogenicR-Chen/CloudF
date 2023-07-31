package com.cloud.rabbitmq;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
@Service
public class MessageSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendDirectMessage(Entry entry) {
        rabbitTemplate.convertAndSend("canal_mq_exchange", "", entry);
    }


}