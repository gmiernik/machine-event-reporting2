package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import com.melexis.test.machineeventreporting.machine.event.port.in.MachineToFocusOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MachineRepository extends JpaRepository<MachineEntity, Integer> {
    @Query("select m from MachineEntity m where m.machineId = :machineId")
    Optional<MachineEntity> findByMachineId(@Param("machineId") String machineId);

    @Query(value = "select m.machine_id as machineId, mt.type_name as machineType, count(e2.id) as numberOfError \n" +
            "from machine_event.machine m \n" +
            "join machine_event.error e2 on e2.machine_id = m.id\n" +
            "join machine_event.machine_type mt on mt.id = m.machine_type_id\n" +
            "where e2.time_stamp > now() - (interval '1 day' * :numberOfDays)\n" +
            "group by m.machine_id, mt.type_name\n" +
            "order by numberOfError desc\n" +
            "limit 10", nativeQuery = true)
    List<MachineToFocusOn> getMachineToFocusOn(@Param("numberOfDays") Integer numberOfDays);
}
