package com.melexis.test.machineeventreporting.machine.event.port.in;

public interface MainIssueForSpecificMachine {
    String getErrorCode();
    String getErrorDetail();
    String getNumberOfErrors();
}
