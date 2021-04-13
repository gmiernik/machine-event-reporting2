package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorDefinitionRepository extends JpaRepository<ErrorDefinitionEntity, Integer> {

}
