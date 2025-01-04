package com.appservice.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appservice.main.entity.PersonaEntity;

public interface PersonasRepository extends JpaRepository<PersonaEntity, Integer>{

}
