package com.melexis.test.machineeventreporting.machine.event.application.port;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
public class SendMachineErrorCommand {
    private final String machineID;
    private final String machineType;
    private final Date dateTime;
    private final String errorType;
}
