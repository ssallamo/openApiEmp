package com.jiseon.openapi.EmpApi.service;

import com.jiseon.openapi.EmpApi.model.EmployeeRepo;
import com.jiseon.openapi.EmpApi.repository.EmpRepository;
import com.jiseon.openapi.employee.models.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    private final EmpRepository empRepository;

    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    public Optional<EmployeeRepo> getEmployeeById(Integer empId){
        return empRepository.findEmployeeById(empId);
    }

    public List<EmployeeRepo> findAllEmployees(){
        return empRepository.findAll();
    }

    @Transactional
    public EmployeeRepo saveEmployee(EmployeeRepo employeeRepo){
        return empRepository.save(employeeRepo);
    }
    @Transactional
    public Integer deleteById(Integer empId){
        return empRepository.deleteEmployeeById(empId);
    }

}
