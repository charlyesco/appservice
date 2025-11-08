package com.appservice.main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appservice.main.entity.LoginEntity;
@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
	List<LoginEntity> findByUsername(String username);

	@Query(value = "select l from login l where l.username=:user")
	LoginEntity findUser(@Param(value = "user")String user);
}
