package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import com.melexis.test.machineeventreporting.machine.event.domain.ErrorDefinition;
import com.melexis.test.machineeventreporting.machine.event.domain.Machine;
import com.melexis.test.machineeventreporting.machine.event.domain.MachineError;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class JpaMachineEventAdapter implements MachineEventRepository {

    private final static Logger logger = Logger.getLogger(JpaMachineEventAdapter.class.getName());

    @Autowired
    private ErrorDefinitionRepository errorDefinitionRepository;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Override
    public void addMachineError(MachineError error) {
        ErrorDefinitionEntity errorDefinitionEntity = new ErrorDefinitionEntity();
        if (error.getDefinition().getCode() != null) {
            errorDefinitionEntity.setCode(error.getDefinition().getCode().getValue());
        }
        errorDefinitionEntity.setDetail(error.getDefinition().getDetail());
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimeStamp(error.getTimeStamp());
        errorEntity.setErrorId(error.getId());
        errorEntity.setDefinition(errorDefinitionEntity);
        MachineTypeEntity machineTypeEntity = new MachineTypeEntity();
        machineTypeEntity.setName(error.getMachine().getType().name());
        MachineEntity machineEntity = machineRepository.findByMachineId(error.getMachine().getId());
        if (machineEntity == null) {
            machineEntity = new MachineEntity();
            machineEntity.setMachineId(error.getMachine().getId());
        }
        machineEntity.setType(machineTypeEntity);
        errorEntity.setMachine(machineEntity);
        errorRepository.save(errorEntity);
    }

    @Override
    public MachineError findByCodeAndTime(String machineId, ZonedDateTime timeStamp) {
        ErrorEntity errorEntity = errorRepository.findByCodeAndTime(machineId, timeStamp);
        ErrorDefinition errorDefinition = ErrorDefinition.create(
                new ErrorDefinition.ErrorCode(errorEntity.getDefinition().getCode()),
                errorEntity.getDefinition().getDetail());
        return MachineError.create(
                errorEntity.getErrorId(),
                errorDefinition,
                errorEntity.getTimeStamp(),
                Machine.create(errorEntity.getMachine().getMachineId(),
                        Machine.MachineType.valueOf(errorEntity.getMachine().getType().getName())));
    }
}
