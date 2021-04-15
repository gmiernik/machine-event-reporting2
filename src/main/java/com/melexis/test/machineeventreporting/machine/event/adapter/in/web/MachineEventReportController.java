package com.melexis.test.machineeventreporting.machine.event.adapter.in.web;

import com.melexis.test.machineeventreporting.machine.event.port.in.GetMachineToFocusOnCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.GetMachineToFocusOnUseCase;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;
import com.melexis.test.machineeventreporting.machine.event.port.in.ReportPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineEventReportController {

    @Autowired
    private GetMachineToFocusOnUseCase getMachineToFocusOnUseCase;

    @GetMapping("/api/v1/machine/event/machine-to-focus-on")
    List<MachineToFocusOn> getMachineToFocusOn(@RequestParam String ndays) {
        ReportPeriod reportPeriod;
        try {
            Integer numberOfDays = Integer.getInteger(ndays);
            reportPeriod = new ReportPeriod(numberOfDays);
        } catch (NumberFormatException e) {
            reportPeriod = null;
        }
        return getMachineToFocusOnUseCase.getMachineToFocusOn(new GetMachineToFocusOnCommand(reportPeriod));
    }
}
