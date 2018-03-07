package com.sssp.dao;

import com.sssp.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer>{

    Employee getByName(String name);
    Employee getById(Integer id);
}
