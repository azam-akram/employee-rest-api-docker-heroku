package com.javaeelab.webservices.rest;

/**
 * @author azam.akram
 *
 * Factory to produce singleton Employee Information Service
 *
 */

public class EmployeeInfoServiceFactory {

    private static EmployeeInfoService employeeInfoService;

    public static EmployeeInfoService getEmployeeInfoService() {
        if(employeeInfoService == null) {
            employeeInfoService = new EmployeeInfoServiceImpl();
        }
        return employeeInfoService;
    }
}
