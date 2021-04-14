package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "machine")
@Getter
@Setter
public class MachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "machine_id")
    private String machineId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "machine_type_id")
    private MachineTypeEntity type;
}
