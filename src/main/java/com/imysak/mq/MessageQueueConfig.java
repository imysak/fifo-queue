package com.imysak.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imysak.mq.service.MessageQueueFactory;
import com.imysak.mq.service.MessageQueueManager;
import com.imysak.mq.service.SimpleMessageQueue;
import com.imysak.mq.service.SimpleMessageQueueManager;
import com.imysak.mq.service.TextMessage;

@Configuration
public class MessageQueueConfig {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public MessageQueueManager<TextMessage> messageQueueManager() {
        MessageQueueFactory messageQueueFactory = MessageQueueFactory.createFactory(SimpleMessageQueue.class, TextMessage.class);
        return new SimpleMessageQueueManager<TextMessage>(messageQueueFactory);
    }
}
