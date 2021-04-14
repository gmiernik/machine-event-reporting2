package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MachineRepository extends JpaRepository<MachineEntity, Integer> {
    @Query("select m from MachineEntity m where m.machineId = :machineId")
    MachineEntity findByMachineId(@Param("machineId") String machineId);
}
