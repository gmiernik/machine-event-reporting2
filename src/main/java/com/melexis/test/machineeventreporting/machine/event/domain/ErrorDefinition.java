package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.*;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorDefinition {
	ErrorCode code;
	String detail;

	public static ErrorDefinition create(ErrorCode code, String detail) {
		return new ErrorDefinition(code, detail);
	}

	public static ErrorDefinition create(String detail) {
		return new ErrorDefinition(null, detail);
	}

	@Value
	public static class ErrorCode {
		int value;
	}
}
