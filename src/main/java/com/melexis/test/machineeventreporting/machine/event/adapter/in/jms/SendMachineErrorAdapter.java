package com.melexis.test.machineeventreporting.machine.event.adapter.in.jms;

import com.google.gson.*;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorCommand;
import com.melexis.test.machineeventreporting.machine.event.port.in.SendMachineErrorUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class SendMachineErrorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SendMachineErrorAdapter.class);

    private final SendMachineErrorUseCase sendMachineErrorUseCase;

    @JmsListener(destination = "${machine_event_reporting.machine_event_queue}")
    public void processMachineEvent(String content) {
        logger.info("Receiving message");
        logger.debug("Content={}", content);
        MachineErrorMessage errorMessage = fromContent(content);
        SendMachineErrorCommand command = SendMachineErrorCommand.builder()
                .machineID(errorMessage.getMachineID())
                .errorType(SendMachineErrorCommand.ErrorType.valueOf(errorMessage.getErrorType()))
                .dateTime(ZonedDateTime.of(errorMessage.getDateTime(), ZoneOffset.UTC))
                .machineType(SendMachineErrorCommand.MachineType.valueOf(errorMessage.getMachineType()))
                .build();
        logger.info("UseCaseC command object = {}", command);
        sendMachineErrorUseCase.sendMachineErrorUseCase(command);
    }

    private MachineErrorMessage fromContent(String content) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> {
            JsonObject jo = json.getAsJsonObject();
            return LocalDateTime.of(jo.get("year").getAsInt(),
                    jo.get("monthValue").getAsInt(),
                    jo.get("dayOfMonth").getAsInt(),
                    jo.get("hour").getAsInt(),
                    jo.get("minute").getAsInt(),
                    jo.get("second").getAsInt(),
                    jo.get("nano").getAsInt());
        }).create();

        return gson.fromJson(content, MachineErrorMessage.class);
    }
}
