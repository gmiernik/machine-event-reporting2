package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.in.MachineErrorDto;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Date;

class SendMachineMachineEntityErrorEntityServiceTest {

    private final MachineEventRepository machineEventRepository = new InMemoryMachineEventAdapter();
    private final SendMachineErrorUseCase sendMachineErrorUseCase = new SendMachineErrorService(machineEventRepository);

    @Test
    public void testSendMachineErrorService() {
        Assert.notNull(sendMachineErrorUseCase, "Service is not initialized");
        String machineId = "MACHINE_1";
        String machineType = "MACHINE_TYPE2";
        MachineErrorDto.MachineType enumMachineType = MachineErrorDto.MachineType.valueOf(machineType);
        Assert.notNull(enumMachineType, "Cannot find value in MachineType enum");
        Assert.isTrue(enumMachineType.name().equals(machineType), "Incorrect MachineType value");
        Date dateTime = new Date();
        String errorType = "TEMPERATURE_ERROR";
        MachineErrorDto.ErrorType enumErrorType = MachineErrorDto.ErrorType.valueOf(errorType);
        Assert.notNull(enumMachineType, "Cannot find value in ErrorType enum");

        MachineErrorDto errorDto = new MachineErrorDto(machineId, enumMachineType, dateTime, enumErrorType);
        sendMachineErrorUseCase.sendMachineErrorUseCase(errorDto);
        MachineError error = machineEventRepository.findByCodeAndTime(machineId, dateTime);
        Assert.notNull(error, "Cannot find machine error");
        Assert.isTrue(error.getMachine().getId().equals(machineId), "Incorrect MachineId");
    }

}
