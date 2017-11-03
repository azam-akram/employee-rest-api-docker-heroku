package com.javaeelab.webservices.rest;

import com.javaeelab.webservices.rest.model.EmployeeDTO;
import com.javaeelab.webservices.rest.model.EmployeesDTO;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author azam.akram
 *
 * Employee Information Service: A singleton
 *
 * Manages organization's employee record.
 *
 */

public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    private static EmployeeInfoService employeeInfoService = new EmployeeInfoServiceImpl();

    // Make this singleton
    public static EmployeeInfoService getInstance() {
        return employeeInfoService;
    }

    private EmployeesDTO employees = new EmployeesDTO();

    private AtomicInteger empId = new AtomicInteger(0);

    @Override
    public EmployeesDTO getAllEmployees() {
        return employees;
    }

    @Override
    public EmployeesDTO getEmployeeById(int id) {

        EmployeesDTO empDTO = new EmployeesDTO();
        for (EmployeeDTO emp: employees.getEmployees()) {
            if (emp.getId() == id) {
                empDTO.getEmployees().add(emp);
                return empDTO;
            }
        }
        return null;
    }

    @Override
    public void addEmployee(EmployeesDTO employeesDTO) {

        employees.setOrgName(employeesDTO.getOrgName());

        for (EmployeeDTO emp: employeesDTO.getEmployees()) {
            EmployeeDTO newEmployee = new EmployeeDTO();
            newEmployee.setId(empId.incrementAndGet());
            newEmployee.setName(emp.getName());
            newEmployee.setDepartment(emp.getDepartment());
            employees.getEmployees().add(newEmployee);
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        for (EmployeeDTO emp: employees.getEmployees()) {
            if (emp.getId() == id) {
                return employees.getEmployees().remove(emp);
            }
        }
        return false;
    }
}
