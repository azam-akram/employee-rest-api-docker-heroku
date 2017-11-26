package com.javaeelab.webservices.rest.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author azam.akram
 *
 * Organization level entry
 */

@XmlRootElement (name = "organization")
@XmlType(propOrder={"orgName", "employees"})
@JsonInclude(Include.NON_EMPTY)
public class EmployeesDTO {

    private String orgName;

    private List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

    @XmlElement( name = "orgName")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @XmlElementWrapper ( name = "employees")
    @XmlElement( name = "employee")
    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
