package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorDefinition {
	ErrorCode code;
	String detail;

	@Value
	public static class ErrorCode {
		int value;
	}
}
