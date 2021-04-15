package com.melexis.test.machineeventreporting.machine.event.adapter.in.web;

import com.melexis.test.machineeventreporting.machine.event.port.in.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineEventReportController {

    @Autowired
    private GetMachineToFocusOnUseCase getMachineToFocusOnUseCase;
    @Autowired
    private GetMainIssueForSpecificMachineUseCase getMainIssueForSpecificMachineUseCase;

    @GetMapping("/api/v1/machine/event/machine-to-focus-on")
    List<MachineToFocusOn> getMachineToFocusOn(@RequestParam String ndays) {
        ReportPeriod reportPeriod;
        try {
            Integer numberOfDays = Integer.valueOf(ndays);
            reportPeriod = new ReportPeriod(numberOfDays);
        } catch (NumberFormatException e) {
            reportPeriod = null;
        }
        return getMachineToFocusOnUseCase.getMachineToFocusOn(new GetMachineToFocusOnCommand(reportPeriod));
    }

    @GetMapping("/api/v1/machine/event/main-issue-for-machine")
    List<MainIssueForSpecificMachine> getMainIssueForSpecificMachine(@RequestParam String ndays, @RequestParam String machineId) {
        ReportPeriod reportPeriod;
        try {
            Integer numberOfDays = Integer.valueOf(ndays);
            reportPeriod = new ReportPeriod(numberOfDays);
        } catch (NumberFormatException e) {
            reportPeriod = null;
        }
        return getMainIssueForSpecificMachineUseCase.getMainIssueForSpecificMachine(new GetMainIssueForSpecificMachineCommand(reportPeriod, machineId));
    }
}
