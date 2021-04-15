package com.melexis.test.machineeventreporting.machine.event.port.in;

public interface MachineToFocusOn {
    String getMachineId();
    String getMachineType();
    Integer getNumberOfError();
}
