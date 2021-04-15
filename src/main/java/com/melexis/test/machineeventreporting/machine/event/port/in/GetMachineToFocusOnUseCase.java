package com.melexis.test.machineeventreporting.machine.event.port.in;

import java.util.List;

/***
 * As a maintenance person
 * I want to select specific period (last xx days with maximum of 90 days)
 * And get error count pareto for the top 10 machines
 * So that I know on which machine I can focus on
 */
public interface GetMachineToFocusOnUseCase {
    List<MachineToFocusOn> getMachineToFocusOn(GetMachineToFocusOnCommand command);
}
