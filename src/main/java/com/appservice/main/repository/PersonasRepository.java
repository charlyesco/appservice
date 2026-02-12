package com.appservice.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.appservice.main.entity.PersonaEntity;

public interface PersonasRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query(value = "select p from personas p where p.id=:id")
    PersonaEntity findByIdPersona(@Param(value = "id") Integer id);
}
