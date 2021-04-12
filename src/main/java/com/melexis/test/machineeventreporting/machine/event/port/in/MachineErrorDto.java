package com.melexis.test.machineeventreporting.machine.event.port.in;

import lombok.*;

import java.util.Date;

@Value
@EqualsAndHashCode
public class MachineErrorDto {
    String machineID;
    MachineType machineType;
    Date dateTime;
    ErrorType errorType;

    public enum ErrorType {
        PART_STUCK("part is stuck in the machine"),
        MACHINE_EMPTY("no part in machine loader"),
        SINGULATOR_ERROR("cannot move the part in the machine"),
        TEMPERATURE_ERROR("machine cannot go at expected temperature"),
        SORTER_ERROR("part cannot be sorted");

        private final String description;

        ErrorType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum MachineType {
        MACHINE_TYPE1("t1"),
        MACHINE_TYPE2("t2"),
        MACHINE_TYPE3("t_new"),
        OTHER_TYPE("o1"),
        AND_ANOTHER_ONE("another2");

        private final String id;

        MachineType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
