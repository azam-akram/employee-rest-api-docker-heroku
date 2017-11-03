package com.javaeelab.webservices.rest;

/**
 * @author azam.akram
 *
 * Factory to produce singleton Employee Information Service
 *
 */

public class EmployeeInfoServiceFactory {

    private static EmployeeInfoService employeeInfoService = new EmployeeInfoServiceImpl();

    public static EmployeeInfoService getEmployeeInfoService() {
        return employeeInfoService;
    }
}
