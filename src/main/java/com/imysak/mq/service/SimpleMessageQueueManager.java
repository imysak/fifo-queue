package com.imysak.mq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.imysak.mq.model.Stats;

public class SimpleMessageQueueManager<M extends Message> implements MessageQueueManager<M> {

    private ConcurrentHashMap<String, MessageQueue<M>> messageQueueMap = new ConcurrentHashMap<>();

    private final MessageQueueFactory<? extends MessageQueue<M>, ? extends M> messageQueueFactory;

    public SimpleMessageQueueManager(MessageQueueFactory<? extends MessageQueue<M>, ? extends M> messageQueueFactory) {
        this.messageQueueFactory = messageQueueFactory;
    }
    
    @Override
    public boolean registerQueue(String id) {
        try {
            MessageQueue<M> mq = messageQueueFactory.create();
            // in current implementation we ignore trying to re create Queue if queue with such 'id' already present
            messageQueueMap.putIfAbsent(id, mq);
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO: add logs
            throw new IllegalStateException();
        }
        return true;
    }

    @Override
    public boolean deleteQueue(String id) {
        // in current implementation we ignore 'missed'(not exist) queue
        messageQueueMap.remove(id);
        return true;
    }

    @Override
    public void pushInQueue(String id, M e) {
        MessageQueue<M> mq = messageQueueMap.get(id);
        if (mq == null) {
            registerQueue(id);
            mq = messageQueueMap.get(id);
        }
        if (mq == null)
            throw new IllegalStateException();
        mq.push(e);
    }

    @Override
    public M popFromQueue(String id) {
        MessageQueue<M> mq = messageQueueMap.get(id);
        if (mq == null) {
            return null;
        }
        M m = mq.pop();
        return m;
    }

    @Override
    public List<Stats> getAllStatistics() {
        List<Stats> statistics = new ArrayList<>();
        messageQueueMap.forEach((k, v) -> statistics.add(v.getStats()));
        return statistics;
    }

    @Override
    public Stats getStatisticsForQueue(String id) {
        MessageQueue<M> mq = messageQueueMap.get(id);
        return mq != null ? mq.getStats() : null;
    }

}
