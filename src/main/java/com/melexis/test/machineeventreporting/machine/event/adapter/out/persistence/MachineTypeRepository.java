package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MachineTypeRepository extends JpaRepository<MachineTypeEntity, Integer> {
    @Query("select mt from MachineTypeEntity mt where mt.name = :typeName")
    Optional<MachineTypeEntity> findByName(@Param("typeName") String typeName);
}
