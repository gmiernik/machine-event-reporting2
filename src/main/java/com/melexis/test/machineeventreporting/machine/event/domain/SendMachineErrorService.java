package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
public class SendMachineErrorService implements SendMachineErrorUseCase {

    private final MachineEventRepository repository;

    private final static String STATIC_ERROR_ID = "00001";
    private final static ErrorDefinition.ErrorCode STATIC_ERROR_CODE = new ErrorDefinition.ErrorCode(1);

    @Override
    public void sendMachineErrorUseCase(SendMachineErrorCommand command) {
        MachineError error = from(command);
        repository.addMachineError(error);
    }

    MachineError from(SendMachineErrorCommand command) {
        Objects.requireNonNull(command);
        ErrorDefinition errorDefinition = new ErrorDefinition(null, command.getErrorType().getDescription());
        Machine machine = new Machine(command.getMachineID(), Machine.MachineType.valueOf(command.getMachineType().name()), new ArrayList<>());
        return new MachineError(null, errorDefinition, command.getDateTime(), machine);
    }
}
