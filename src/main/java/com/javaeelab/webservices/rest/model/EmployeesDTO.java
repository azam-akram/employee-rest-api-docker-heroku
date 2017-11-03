package com.javaeelab.webservices.rest.model;

import java.util.Set;
import java.util.HashSet;
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

    private Set<EmployeeDTO> employees = new HashSet<EmployeeDTO>();

    @XmlElement( name = "orgName")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @XmlElementWrapper ( name = "employees")
    @XmlElement( name = "employee")
    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
