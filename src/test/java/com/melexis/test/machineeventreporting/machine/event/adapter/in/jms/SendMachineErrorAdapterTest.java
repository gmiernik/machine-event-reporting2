package com.melexis.test.machineeventreporting.machine.event.adapter.in.jms;

import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SendMachineErrorAdapterTest {
    private final static Logger logger = LoggerFactory.getLogger(SendMachineErrorAdapterTest.class);

    private static SendMachineErrorCommand sendMachineErrorCommand = null;

    @TestConfiguration
    static class SendMachineErrorUseCaseConfiguration {
        @Bean
        public SendMachineErrorUseCase sendMachineErrorUseCase() {
            return command -> {
                logger.info("SendMachineError use case invoked");
                SendMachineErrorAdapterTest.sendMachineErrorCommand = command;
            };
        }
    }

    @Autowired
    private SendMachineErrorAdapter adapter;

    @Autowired
    private MachineErrorSender sender;

    @Value("${machine_event_reporting.machine_event_queue}")
    private String queueName;

    @Test
    public void testSendMachineError() throws Exception {
        final String machineId = "MACHINE_123";
        final String eventStr = "{" +
                "\"machineType\":\"MACHINE_TYPE2\"," +
                "\"machineID\":\"" + machineId + "\"," +
                "\"dateTime\":" +
                "{" +
                "\"nano\":134268000," +
                "\"year\":2021," +
                "\"monthValue\":4," +
                "\"dayOfMonth\":9," +
                "\"hour\":7," +
                "\"minute\":30," +
                "\"second\":18," +
                "\"dayOfWeek\":\"FRIDAY\"," +
                "\"dayOfYear\":99," +
                "\"month\":\"APRIL\"," +
                "\"chronology\":" +
                "{" +
                "\"calendarType\":\"iso8601\"," +
                "\"id\":\"ISO\"" +
                "}" +
                "}," +
                "\"errorType\":\"TEMPERATURE_ERROR\"" +
                "}";
        logger.info("MachineEvent queue name: {}", queueName);
        sender.send(queueName, eventStr);
        Thread.sleep(3000);
        logger.info("Message was sent");
        SendMachineErrorCommand command = SendMachineErrorAdapterTest.sendMachineErrorCommand;
        Assert.notNull(command, "UseCaseCommand cannot be null");
        Assert.isTrue(command.getMachineID().equals(machineId), "Incorrect Machine IS");
    }
}
