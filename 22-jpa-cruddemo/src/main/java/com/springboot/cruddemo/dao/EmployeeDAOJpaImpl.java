package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){

        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {

        Query theQuery = entityManager.createQuery("from Employee");

        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    public void save(Employee theEmployee) {

        Employee dbEmployee = entityManager.merge(theEmployee);

        theEmployee.setId(dbEmployee.getId());


    }

    @Override
    public Employee findById(int theId) {

        Employee theEmployee = entityManager.find(Employee.class,theId);

        return theEmployee;
    }

    @Override
    public void deleteById(int theId) {

        Query theQuery = entityManager.createQuery("delete from Employee where id=: employeeId");

        theQuery.setParameter("employeeId",theId);

        theQuery.executeUpdate();
    }
}
