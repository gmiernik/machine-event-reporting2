package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.Getter;
import java.util.List;

@Getter
class Machine {

	private String id;
	private MachineType type;
	private List<Error> errorList;

	enum MachineType {
		MACHINE_TYPE1("t1"),
		MACHINE_TYPE2("t2"),
		MACHINE_TYPE3("t_new"),
		OTHER_TYPE("o1"),
		AND_ANOTHER_ONE("another2");

		private final String id;

		MachineType(String id) {
			this.id = id;
		}
		public String getId() {
			return id;
		}
	}
}
