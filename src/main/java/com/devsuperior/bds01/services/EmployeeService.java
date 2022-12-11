package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Page<EmployeeDTO> findAll(Pageable pageable){
        Page<Employee> page=employeeRepository.findAll(pageable);
         return page.map(x -> new EmployeeDTO(x));
    }
   @Transactional
    public EmployeeDTO insert(EmployeeDTO employeeDTO){
        var employees=new Employee();
        employees.setName(employeeDTO.getName());
        employees.setEmail(employeeDTO.getEmail());
        employees.setDepartment(new Department(employeeDTO.getDepartmentId(),null));
        employees=employeeRepository.save(employees);
        return new EmployeeDTO(employees);
    }

}
