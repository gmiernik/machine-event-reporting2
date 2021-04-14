package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MachineError {
	String id;
	ErrorDefinition definition;
	ZonedDateTime timeStamp;
	Machine machine;

	public static MachineError create(String id, ErrorDefinition errorDefinition, ZonedDateTime timeStamp, Machine machine) {
		return new MachineError(id, errorDefinition, timeStamp, machine);
	}
}
