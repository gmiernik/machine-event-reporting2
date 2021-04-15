package com.melexis.test.machineeventreporting.machine.event.port.in;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
@EqualsAndHashCode
public class GetMainIssueForSpecificMachineCommand {
    static ReportPeriod LIMIT = new ReportPeriod(90);
    ReportPeriod reportPeriod;
    String machineId;

    public GetMainIssueForSpecificMachineCommand(ReportPeriod reportPeriod, String machineId) {
        this.reportPeriod = reportPeriod;
        Objects.requireNonNull(reportPeriod);
        if (reportPeriod.compareTo(LIMIT) >= 0)
            throw new ReportPeriodExceededException(LIMIT, reportPeriod);
        Objects.requireNonNull(machineId);
        this.machineId = machineId;
    }
}
