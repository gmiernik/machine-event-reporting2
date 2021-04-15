package com.melexis.test.machineeventreporting.machine.event.domain;

import com.melexis.test.machineeventreporting.machine.event.port.in.GetMachineToFocusOnCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.GetMachineToFocusOnUseCase;
import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MachineEventReportService implements GetMachineToFocusOnUseCase {

    private MachineEventRepository repository;

    @Override
    public List<MachineToFocusOn> getMachineToFocusOn(GetMachineToFocusOnCommand command) {
        return repository.getMachineToFocusOn(command.getReportPeriod());
    }
}
