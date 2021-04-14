package com.melexis.test.machineeventreporting.machine.event.port.out;

import com.melexis.test.machineeventreporting.machine.event.domain.MachineError;

import java.time.ZonedDateTime;

public interface MachineEventRepository {
    void addMachineError(MachineError error);
    MachineError findByCodeAndTime(String machineId, ZonedDateTime timeStamp);
}
