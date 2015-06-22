package com.imysak.mq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imysak.mq.model.Stats;
import com.imysak.mq.service.MessageQueueManager;
import com.imysak.mq.service.TextMessage;

@RestController
@RequestMapping("/queue")
public class ApiController {
    @Autowired
    private MessageQueueManager<TextMessage> messageQueueManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Stats> getAllStatistics() {
        return messageQueueManager.getAllStatistics();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createQueue(@RequestBody String id) {
        messageQueueManager.registerQueue(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteQueue(@PathVariable String id) {
        messageQueueManager.deleteQueue(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TextMessage getStats(@PathVariable String id) {
        return messageQueueManager.popFromQueue(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void getStats(@PathVariable String id, @RequestBody TextMessage message) {
        messageQueueManager.pushInQueue(id, message);
    }

}
