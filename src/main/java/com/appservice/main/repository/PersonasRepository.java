package com.appservice.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appservice.main.entity.Persona;

public interface PersonasRepository extends JpaRepository<Persona, Integer>{

}
