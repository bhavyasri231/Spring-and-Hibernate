package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    public void save(Employee theEmployee);

    public Employee findById(int theId);

    public void deleteById(int theId);

}
