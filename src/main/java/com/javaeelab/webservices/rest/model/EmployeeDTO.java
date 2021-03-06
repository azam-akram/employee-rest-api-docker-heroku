package com.javaeelab.webservices.rest.model;

/**
 * @author azam.akram
 *
 * EmployeeDTO entry
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Employee")
@XmlType(propOrder={"id", "name", "department"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDTO {

    private Integer id;

    private String name;

    private String department;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "department")
    @XmlElement(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
