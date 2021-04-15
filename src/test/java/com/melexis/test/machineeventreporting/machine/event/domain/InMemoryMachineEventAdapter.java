package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.in.MainIssueForSpecificMachine;
import com.melexis.test.machineeventreporting.machine.event.port.in.ReportPeriod;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class InMemoryMachineEventAdapter implements MachineEventRepository {
    private final HashMap<String, MachineError> data = new HashMap<>();

    @Override
    public void addMachineError(MachineError error) {
        data.put(getKey(error.getMachine().getId(), error.getTimeStamp()), error);
    }

    @Override
    public MachineError findByCodeAndTime(String machineId, ZonedDateTime timeStamp) {
        return data.get(getKey(machineId, timeStamp));
    }

    @Override
    public List<MachineToFocusOn> getMachineToFocusOn(ReportPeriod reportPeriod) {
        return null;
    }

    @Override
    public List<MainIssueForSpecificMachine> getMainIssueForSpecificMachine(ReportPeriod reportPeriod, String machineId) {
        return null;
    }

    private String getKey(String machineId, ZonedDateTime timeStamp) {
        return machineId+":"+timeStamp.toString();
    }
}
