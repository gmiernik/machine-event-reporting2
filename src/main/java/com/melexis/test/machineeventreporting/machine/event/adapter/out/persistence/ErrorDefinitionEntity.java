package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "error_definition")
@Getter
@Setter
public class ErrorDefinitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String detail;
}
