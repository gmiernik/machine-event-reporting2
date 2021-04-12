package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineErrorDto;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
class SendMachineErrorService implements SendMachineErrorUseCase {

    private final MachineEventRepository repository;
    private final static ErrorDefinition.ErrorCode STATIC_ERROR_CODE = new ErrorDefinition.ErrorCode(1);
    private final static String STATIC_ERROR_ID = "00001";

    @Override
    public void sendMachineErrorUseCase(MachineErrorDto errorDto) {
        ErrorDefinition errorDefinition = new ErrorDefinition(STATIC_ERROR_CODE, errorDto.getErrorType().getDescription());
        Machine machine = new Machine(errorDto.getMachineID(), Machine.MachineType.valueOf (errorDto.getMachineType().name()), new ArrayList<>());
        MachineError error = new MachineError(STATIC_ERROR_ID, errorDefinition, errorDto.getDateTime(), machine);
        repository.addMachineError(error);
    }
}
