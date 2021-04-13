package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;

import java.util.Date;
import java.util.HashMap;

public class InMemoryMachineEventAdapter implements MachineEventRepository {
    private final HashMap<String, MachineError> data = new HashMap<>();

    @Override
    public void addMachineError(MachineError error) {
        data.put(getKey(error.getMachine().getId(), error.getTimeStamp()), error);
    }

    @Override
    public MachineError findByCodeAndTime(String machineId, Date timeStamp) {
        return data.get(getKey(machineId, timeStamp));
    }

    private String getKey(String machineId, Date timeStamp) {
        return machineId+":"+timeStamp.toString();
    }
}
