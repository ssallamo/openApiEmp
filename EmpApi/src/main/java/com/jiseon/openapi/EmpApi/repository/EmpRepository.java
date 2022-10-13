package com.jiseon.openapi.EmpApi.repository;

import com.jiseon.openapi.EmpApi.model.EmployeeRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRepository extends JpaRepository <EmployeeRepo, Integer> {
    Optional<EmployeeRepo> findEmployeeById(Integer id);
    Integer deleteEmployeeById(Integer id);

}