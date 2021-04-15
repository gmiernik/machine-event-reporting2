package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import com.melexis.test.machineeventreporting.machine.event.domain.ErrorDefinition;
import com.melexis.test.machineeventreporting.machine.event.domain.Machine;
import com.melexis.test.machineeventreporting.machine.event.domain.MachineError;
import com.melexis.test.machineeventreporting.machine.event.port.in.ReportPeriod;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private MachineTypeRepository machineTypeRepository;

    @Override
    @Transactional
    public void addMachineError(MachineError error) {
        ErrorDefinitionEntity errorDefinitionEntity = null;
        if (error.getDefinition().getDetail() != null) {
            Optional<ErrorDefinitionEntity> res = errorDefinitionRepository.findByDetail(error.getDefinition().getDetail());
            if (res.isPresent())
                errorDefinitionEntity = res.get();
            else {
                errorDefinitionEntity = new ErrorDefinitionEntity();
                errorDefinitionEntity.setDetail(error.getDefinition().getDetail());
                errorDefinitionRepository.save(errorDefinitionEntity);
            }
        }

        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setTimeStamp(error.getTimeStamp());
        errorEntity.setErrorId(error.getId());
        errorEntity.setDefinition(errorDefinitionEntity);
        MachineTypeEntity machineTypeEntity;
        Optional<MachineTypeEntity> mtRes = machineTypeRepository.findByName(error.getMachine().getType().name());
        if (mtRes.isPresent())
            machineTypeEntity = mtRes.get();
        else {
            machineTypeEntity = new MachineTypeEntity();
            machineTypeEntity.setName(error.getMachine().getType().name());
            machineTypeRepository.save(machineTypeEntity);
        }
        MachineEntity machineEntity;
        Optional<MachineEntity> mRes = machineRepository.findByMachineId(error.getMachine().getId());
        if (mRes.isPresent())
            machineEntity = mRes.get();
        else {
            machineEntity = new MachineEntity();
            machineEntity.setMachineId(error.getMachine().getId());
            machineRepository.save(machineEntity);
        }
        machineEntity.setType(machineTypeEntity);
        errorEntity.setMachine(machineEntity);
        errorRepository.save(errorEntity);
    }

    @Override
    public MachineError findByCodeAndTime(String machineId, ZonedDateTime timeStamp) {
        Optional<ErrorEntity> res = errorRepository.findByCodeAndTime(machineId, timeStamp);
        if (!res.isPresent())
            return null;
        ErrorEntity errorEntity = res.get();
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

    @Override
    public List<MachineToFocusOn> getMachineToFocusOn(ReportPeriod reportPeriod) {
        return machineRepository.getMachineToFocusOn(reportPeriod.getValue());
    }
}
