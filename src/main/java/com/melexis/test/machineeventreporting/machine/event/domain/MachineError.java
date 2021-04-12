package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class MachineError {
	String id;
	ErrorDefinition definition;
	Date timeStamp;
	Machine machine;
}
