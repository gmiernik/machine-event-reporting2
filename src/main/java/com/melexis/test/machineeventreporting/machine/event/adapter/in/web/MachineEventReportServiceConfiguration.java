package com.melexis.test.machineeventreporting.machine.event.adapter.in.web;

import com.melexis.test.machineeventreporting.machine.event.domain.MachineEventReportService;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MachineEventReportServiceConfiguration {

    @Bean
    public MachineEventReportService machineEventReportService(MachineEventRepository repository) {
        return new MachineEventReportService(repository);
    }
}
