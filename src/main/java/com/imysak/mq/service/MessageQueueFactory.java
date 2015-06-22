package com.imysak.mq.service;

public class MessageQueueFactory<Q extends MessageQueue<M>, M extends Message> {

    final Class<Q> messageQueueClass;
    final Class<M> messageClass;
    
    private MessageQueueFactory(final Class<Q> messageQueueClass, final Class<M> messageClass) {
        this.messageQueueClass = messageQueueClass;
        this.messageClass = messageClass;
    }
    
    public static <Q extends MessageQueue<M>, M extends Message> MessageQueueFactory<Q, M> createFactory(final Class<Q> messageQueueClass, final Class<M> messageClass) {
        return new MessageQueueFactory<Q,M>(messageQueueClass, messageClass);
    }

    public Q create() throws InstantiationException, IllegalAccessException {
        return messageQueueClass.newInstance();
    }
}
