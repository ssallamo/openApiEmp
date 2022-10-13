package com.jiseon.openapi.EmpApi.controller;

import com.jiseon.openapi.EmpApi.model.EmployeeRepo;
import com.jiseon.openapi.EmpApi.service.EmpService;
import com.jiseon.openapi.employee.api.EmployeeApi;
import com.jiseon.openapi.employee.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class EmpController implements EmployeeApi {

    @Autowired
    private EmpService empService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> eList = new ArrayList<>();


        List<EmployeeRepo> erListTmp = empService.findAllEmployees();
        if(erListTmp.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            for(int i=0; i<erListTmp.size(); i++) {
                Employee te = new Employee();
                te.setId(erListTmp.get(i).getId());
                te.setName(erListTmp.get(i).getName());
                te.setMobile(erListTmp.get(i).getMobile());
                te.setDepartment(erListTmp.get(i).getDepartment());
                eList.add(te);
            }
            return new ResponseEntity<>(eList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Integer empId) {

        Employee e = new Employee();

        //Employee class by OpenApi doesn't match to JpaRepository
        Optional<EmployeeRepo> oEmpTmp = empService.getEmployeeById(empId);
        if(oEmpTmp.isPresent()){
            e.setId(oEmpTmp.get().getId());
            e.setName(oEmpTmp.get().getName());
            e.setMobile(oEmpTmp.get().getMobile());
            e.setDepartment(oEmpTmp.get().getDepartment());

            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Employee> saveEmployee(Employee employee) {

        EmployeeRepo er = new EmployeeRepo(employee.getId(),
                employee.getName(),
                employee.getMobile(),
                employee.getDepartment());

        if (empService.saveEmployee(er) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity removeEmployeeById(Integer empId){
        if(empService.deleteById(empId)==1){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
