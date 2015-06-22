package com.imysak.mq.service;

import com.imysak.mq.model.Stats;

public interface MessageQueue<M extends Message> {

    void push(M e);

    M pop();

    Stats getStats();

}
