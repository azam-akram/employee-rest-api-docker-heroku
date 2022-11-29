package com.javaeelab.webservices.rest;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.javaeelab.webservices.rest.model.EmployeeDTO;
import com.javaeelab.webservices.rest.model.EmployeesDTO;

/**
 * @author azam.akram
 *
 * Manages organization's employee record.
 *
 */

public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    private EmployeesDTO employees = new EmployeesDTO();

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public EmployeesDTO getAllEmployees() {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            // Should be Java class name instead of actual database table name
            Query query = session.createQuery("from EmployeeDTO");
            List<EmployeeDTO> employeeList = (List<EmployeeDTO>) query.list();
            employees.setOrgName("My Organization");
            employees.setEmployees(employeeList);

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public EmployeesDTO getEmployeeById(int id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            // Should be Java class name instead of actual database table name
            Query query = session.createQuery("from EmployeeDTO where id = :id");
            query.setParameter("id", id);
            List<EmployeeDTO> employee = (List<EmployeeDTO>) query.list();
            employees.setOrgName("My Organization");
            employees.setEmployees(employee);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public void addEmployee(EmployeesDTO employeesDTO) {

        employees.setOrgName(employeesDTO.getOrgName());
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            for (EmployeeDTO emp: employeesDTO.getEmployees()) {
                tx = session.beginTransaction();
                session.save(emp);
                tx.commit();
                employees.getEmployees().add(emp);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            EmployeeDTO employee = (EmployeeDTO) session.get(EmployeeDTO.class, employeeDTO.getId());
            if(employeeDTO.getName() != null) {
                employee.setName(employeeDTO.getName());
            }
            if(employeeDTO.getDepartment() != null) {
                employee.setDepartment(employeeDTO.getDepartment());
            }
            session.update(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            EmployeeDTO employee = (EmployeeDTO) session.get(EmployeeDTO.class, id);
            session.delete(employee);
            session.getTransaction().commit();

            // OR using Hibernate Query Language
            // Should be Java class name instead of actual database table name
            /*Query query = session.createQuery("delete from EmployeeDTO where id = :id");
            query.setParameter("id", id);
            int result = query.executeUpdate();
            if (result > 0) {
                logger.info("Employee with id " + id + " is deleted");
            }*/

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}