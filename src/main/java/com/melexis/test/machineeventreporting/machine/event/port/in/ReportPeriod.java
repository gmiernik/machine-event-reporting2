package com.melexis.test.machineeventreporting.machine.event.port.in;

import lombok.Value;

@Value
public class ReportPeriod implements Comparable<ReportPeriod> {
    int value;

    @Override
    public int compareTo(ReportPeriod reportPeriod) {
        return Integer.compare(getValue(), reportPeriod.getValue());
    }
}
