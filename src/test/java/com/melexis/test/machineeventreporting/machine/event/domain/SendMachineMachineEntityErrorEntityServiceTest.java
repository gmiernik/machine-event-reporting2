package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.Date;

class SendMachineMachineEntityErrorEntityServiceTest {

    private final MachineEventRepository machineEventRepository = new InMemoryMachineEventAdapter();
    private final SendMachineErrorUseCase sendMachineErrorUseCase = new SendMachineErrorService(machineEventRepository);

    @Test
    public void testSendMachineErrorService() {
        Assert.notNull(sendMachineErrorUseCase, "Service is not initialized");
        String machineId = "MACHINE_1";
        String machineType = "MACHINE_TYPE2";
        SendMachineErrorCommand.MachineType enumMachineType = SendMachineErrorCommand.MachineType.valueOf(machineType);
        Assert.notNull(enumMachineType, "Cannot find value in MachineType enum");
        Assert.isTrue(enumMachineType.name().equals(machineType), "Incorrect MachineType value");
        ZonedDateTime dateTime = ZonedDateTime.now();
        String errorType = "TEMPERATURE_ERROR";
        SendMachineErrorCommand.ErrorType enumErrorType = SendMachineErrorCommand.ErrorType.valueOf(errorType);
        Assert.notNull(enumMachineType, "Cannot find value in ErrorType enum");

        SendMachineErrorCommand command = SendMachineErrorCommand.builder().machineID(machineId)
                .machineType(enumMachineType).dateTime(dateTime).errorType(enumErrorType)
                .build();
        sendMachineErrorUseCase.sendMachineErrorUseCase(command);
        MachineError error = machineEventRepository.findByCodeAndTime(machineId, dateTime);
        Assert.notNull(error, "Cannot find machine error");
        Assert.isTrue(error.getMachine().getId().equals(machineId), "Incorrect MachineId");
    }

}
