package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "error")
@Getter
@Setter
public class ErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "error_id")
    private String errorId;
    @Column(name = "time_stamp")
    private Date timeStamp;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "error_code")
    private ErrorDefinitionEntity definition;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "machine_id")
    private MachineEntity machine;
}
