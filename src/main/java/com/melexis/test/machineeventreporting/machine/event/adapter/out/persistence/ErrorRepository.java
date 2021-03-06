package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Integer> {
    @Query("select e from ErrorEntity e where e.machine.machineId = :machineId and e.timeStamp = :timeStamp")
    Optional<ErrorEntity> findByCodeAndTime(@Param("machineId") String machineId, @Param("timeStamp") ZonedDateTime timeStamp);
}
