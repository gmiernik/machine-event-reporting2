package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Machine {

	String id;
	MachineType type;
	List<MachineError> errorList;

	public enum MachineType {
		MACHINE_TYPE1("t1"),
		MACHINE_TYPE2("t2"),
		MACHINE_TYPE3("t_new"),
		OTHER_TYPE("o1"),
		AND_ANOTHER_ONE("another2");

		private final String value;

		MachineType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
}
