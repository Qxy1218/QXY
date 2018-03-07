package com.sssp.dao.impl;

import com.sssp.dao.DepartmentDao;
import com.sssp.dao.EmployeeDao;
import com.sssp.pojo.Department;
import com.sssp.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeImpl {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    public Page<Employee> getPage(int pageNo, int pageSize){

        PageRequest page=new PageRequest(pageNo-1,pageSize);
        return employeeDao.findAll(page);
    }
    public Employee lastName(String name){
        return employeeDao.getByName(name);
    }
    public List<Department> selectDepartmentList(){
        return departmentDao.findAll();
    }
    public void save(Employee employee){
        employeeDao.saveAndFlush(employee);
    }
    public Employee openUpdate(Integer id){
        return employeeDao.getById(id);
    }
    public void delete(Integer id) {
        employeeDao.delete(id);
    }
}
