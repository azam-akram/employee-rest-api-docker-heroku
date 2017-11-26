package com.javaeelab.webservices.rest;

import com.javaeelab.webservices.rest.model.EmployeeDTO;
import com.javaeelab.webservices.rest.model.EmployeesDTO;

/**
 * @author azam.akram
 *
 * Interface for Employee Information Service
 *
 */

public interface EmployeeInfoService {

    EmployeesDTO getAllEmployees();

    EmployeesDTO getEmployeeById(int id);

    void addEmployee(EmployeesDTO employeesDTO);

    void updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(int id);
}
