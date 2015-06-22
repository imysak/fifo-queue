package com.imysak.mq.service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.imysak.mq.model.Stats;

public class SimpleMessageQueue<M extends Message> implements MessageQueue<M> {

    private String name;
    private final long startTime;
    private final AtomicLong pushedTotal;
    private final AtomicLong popedTotal;

    ConcurrentLinkedQueue<M> queue;

    public SimpleMessageQueue() {
        queue = new ConcurrentLinkedQueue<>();
        startTime = System.currentTimeMillis();
        pushedTotal = new AtomicLong();
        popedTotal = new AtomicLong();
    }

    @Override
    public void push(M e) {
        queue.offer(e);
    }

    @Override
    public M pop() {
        return queue.poll();
    }

    @Override
    public Stats getStats() {
        Stats st = new Stats();
        st.id = name;
        st.elements = queue.size();
        double timeInSeconds = (double) (System.currentTimeMillis() - startTime) / 1000;
        st.pushingTempo = pushedTotal.doubleValue() / timeInSeconds;
        st.popingTempo = popedTotal.doubleValue() / timeInSeconds;
        return st;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
