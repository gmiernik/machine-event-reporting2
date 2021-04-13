package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "machine_type")
@Getter
@Setter
public class MachineTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "type_name")
    private String name;
}
