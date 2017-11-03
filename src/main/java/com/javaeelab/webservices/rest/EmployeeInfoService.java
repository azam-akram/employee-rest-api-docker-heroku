package com.javaeelab.webservices.rest;

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

    boolean deleteEmployee(int id);
}
