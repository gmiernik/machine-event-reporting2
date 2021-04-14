package com.melexis.test.machineeventreporting.machine.event.adapter.in.jms;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import lombok.Value;

@Value
public class MachineErrorMessage implements Serializable {
	private static final long serialVersionUID = -2104220153622042523L;

	String machineType;
	String machineID;
	LocalDateTime dateTime;
	String errorType;
}
