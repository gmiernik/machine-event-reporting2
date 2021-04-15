package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.in.*;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MachineEventReportService implements GetMachineToFocusOnUseCase, GetMainIssueForSpecificMachineUseCase {

    private MachineEventRepository repository;

    @Override
    public List<MachineToFocusOn> getMachineToFocusOn(GetMachineToFocusOnCommand command) {
        return repository.getMachineToFocusOn(command.getReportPeriod());
    }

    @Override
    public List<MainIssueForSpecificMachine> getMainIssueForSpecificMachine(GetMainIssueForSpecificMachineCommand command) {
        return repository.getMainIssueForSpecificMachine(command.getReportPeriod(), command.getMachineId());
    }
}
