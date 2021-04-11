package com.melexis.test.machineeventreporting.machine.event.domain;

import lombok.Getter;
import java.util.Date;

@Getter
class Error {
	private String id;
	private int code;
	private Date timeStamp;
	private Machine machine;
}
