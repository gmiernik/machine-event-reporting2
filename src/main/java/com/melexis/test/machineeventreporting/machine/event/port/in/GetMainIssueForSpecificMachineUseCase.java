package com.melexis.test.machineeventreporting.machine.event.port.in;

import java.util.List;

/***
 * As a maintenance
 * I want to get for last xx days (up to 90)
 * And for a specific machine a report showing the top 10 errors
 * So that I can see what kind of repair I need to do on that machine
 */
public interface GetMainIssueForSpecificMachineUseCase {
    List<MainIssueForSpecificMachine> getMainIssueForSpecificMachine(GetMainIssueForSpecificMachineCommand command);
}
