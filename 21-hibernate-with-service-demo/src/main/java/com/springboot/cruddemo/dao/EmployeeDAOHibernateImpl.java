package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager){

        entityManager=theEntityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);

        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {

        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save/upate the customer ... finally LOL
        currentSession.saveOrUpdate(theEmployee);

    }

    @Override
    @Transactional
    public Employee findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // now retrieve/read from database using the primary key
        Employee theEmployee = currentSession.get(Employee.class, theId);

        return theEmployee;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);;

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();
    }

}
