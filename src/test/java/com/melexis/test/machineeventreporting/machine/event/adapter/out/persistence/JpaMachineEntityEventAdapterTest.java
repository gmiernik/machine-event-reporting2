package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import com.melexis.test.machineeventreporting.machine.event.domain.ErrorDefinition;
import com.melexis.test.machineeventreporting.machine.event.domain.Machine;
import com.melexis.test.machineeventreporting.machine.event.domain.MachineError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JpaMachineEntityEventAdapterTest {

    @Autowired
    private JpaMachineEventAdapter adapter;

    @Test
    public void testAddErrorDefinition() {
        final ErrorDefinition.ErrorCode errorCode = new ErrorDefinition.ErrorCode(123);
        final String errorDetail = "test_123";
        final String machineId = "MACHINE_123";
        final ZonedDateTime timeStamp = ZonedDateTime.now();
        ErrorDefinition errorDefinition = ErrorDefinition.create(errorCode, errorDetail);
        adapter.addMachineError(MachineError.create("00123", errorDefinition, timeStamp,
                Machine.create(machineId, Machine.MachineType.MACHINE_TYPE3)));
        MachineError error = adapter.findByCodeAndTime(machineId, timeStamp);
        Assert.notNull(error, "Cannot find error object");
        Assert.isTrue(errorCode.getValue() == error.getDefinition().getCode().getValue(), "Different error code");
        Assert.isTrue(error.getMachine().getId().equals(machineId), "Incorrect machine ID");
    }
}
