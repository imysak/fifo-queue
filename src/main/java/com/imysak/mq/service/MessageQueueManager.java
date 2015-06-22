package com.imysak.mq.service;

import java.util.List;

import com.imysak.mq.model.Stats;

public interface MessageQueueManager<M extends Message> {

    boolean registerQueue(String id);

    boolean deleteQueue(String id);

    List<Stats> getAllStatistics();

    Stats getStatisticsForQueue(String id);

    void pushInQueue(String id, M e);

    M popFromQueue(String id);
}
