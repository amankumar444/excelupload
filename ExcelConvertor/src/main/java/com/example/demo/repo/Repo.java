package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.EmployeeEntity;

@Repository
public interface Repo extends JpaRepository<EmployeeEntity, Long> {
	@Query(value = "SELECT avg(salary) FROM EmployeeEntity WHERE salary != -99999")
	public Integer avg();

}
