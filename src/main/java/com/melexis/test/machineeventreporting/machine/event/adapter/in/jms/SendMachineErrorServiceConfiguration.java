package com.melexis.test.machineeventreporting.machine.event.adapter.in.jms;

import com.melexis.test.machineeventreporting.machine.event.domain.SendMachineErrorService;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import com.melexis.test.machineeventreporting.machine.event.port.out.MachineEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendMachineErrorServiceConfiguration {
    @Bean
    public SendMachineErrorUseCase sendMachineErrorUseCase(MachineEventRepository repository) {
        return new SendMachineErrorService(repository);
    }
}
