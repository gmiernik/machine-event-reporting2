package com.melexis.test.machineeventreporting.machine.event.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ErrorDefinitionRepository extends JpaRepository<ErrorDefinitionEntity, Integer> {
    @Query("select ed from ErrorDefinitionEntity ed where ed.detail = :detail")
    Optional<ErrorDefinitionEntity> findByDetail(@Param("detail") String detail);
}
