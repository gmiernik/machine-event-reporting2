package com.melexis.test.machineeventreporting.machine.event.port.in;

public class ReportPeriodExceededException extends RuntimeException {
    public ReportPeriodExceededException(ReportPeriod limit, ReportPeriod actual) {
        super(String.format("Maximum report period exceeded: tried to send %s but limit is %s!", actual.getValue(), limit.getValue()));
    }
}