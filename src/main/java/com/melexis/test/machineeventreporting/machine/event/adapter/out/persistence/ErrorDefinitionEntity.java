package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "error_definition")
@Getter
@Setter
public class ErrorDefinitionEntity {
    @Id
    private Integer code;
    private String detail;
}
