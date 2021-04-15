package com.melexis.test.machineeventreporting.machine.event.port.out;

import com.melexis.test.machineeventreporting.machine.event.domain.MachineError;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;
import com.melexis.test.machineeventreporting.machine.event.port.in.ReportPeriod;

import java.time.ZonedDateTime;
import java.util.List;

public interface MachineEventRepository {
    void addMachineError(MachineError error);
    MachineError findByCodeAndTime(String machineId, ZonedDateTime timeStamp);
    List<MachineToFocusOn> getMachineToFocusOn(ReportPeriod reportPeriod);
}
