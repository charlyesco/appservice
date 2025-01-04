package com.appservice.main.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appservice.main.entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
	List<LoginEntity> findByUsername(String username);
}
