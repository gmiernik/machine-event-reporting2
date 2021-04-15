package com.melexis.test.machineeventreporting.machine.event.port.in;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
@EqualsAndHashCode
public class GetMachineToFocusOnCommand {

    ReportPeriod reportPeriod;
    static ReportPeriod LIMIT = new ReportPeriod(90);

    public GetMachineToFocusOnCommand(ReportPeriod reportPeriod) {
        this.reportPeriod = reportPeriod;
        Objects.requireNonNull(reportPeriod);
        if (reportPeriod.compareTo(LIMIT) >= 0)
            throw new ReportPeriodExceededException(LIMIT, reportPeriod);
    }
}
