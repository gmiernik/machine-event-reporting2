package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Integer> {
    @Query("select e from ErrorEntity e where e.machine.machineId = :machineId and e.timeStamp = :timeStamp")
    ErrorEntity findByCodeAndTime(@Param("machineId") String machineId, @Param("timeStamp") Date timeStamp);
}
