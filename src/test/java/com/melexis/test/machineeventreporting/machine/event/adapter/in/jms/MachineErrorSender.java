package com.melexis.test.machineeventreporting.machine.event.adapter.in.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MachineErrorSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MachineErrorSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String queue, Object content) {
        jmsTemplate.convertAndSend(queue, content);
    }
}
