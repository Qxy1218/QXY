package com.sssp.controller;

import com.sssp.dao.impl.EmployeeImpl;
import com.sssp.pojo.Department;
import com.sssp.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeImpl employee;

    @RequestMapping(value="empList")
    public String pageIndex(@RequestParam(value="pageNo", required = false, defaultValue = "1") String pageStr
        , Model model){
        int pageNo=1;
        try {
            pageNo=Integer.parseInt(pageStr);
            if(pageNo<1){
                pageNo=1;
            }
        }catch (Exception exception){}
        Page<Employee> page=employee.getPage(pageNo,3);
        model.addAttribute("page",page);
        return "empList";
    }
    //ajax验证
    @ResponseBody
    @RequestMapping(value = "ajaxName")
    public String ajaxName(@RequestParam(value="name", required = false)String name){
        Employee emp=employee.lastName(name);
        if(emp==null){
            return "0";
        }else {
            return "1";
        }
    }
    @RequestMapping(value="insertEmp")
    public String insertEmp(Model model){
        List<Department> listDep=employee.selectDepartmentList();
        model.addAttribute("departmentList",listDep);
        return "insert";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Employee employees){
        //新增
        if(employees.getId()==null) {
            employees.setWorkTime(new Date());
            employee.save(employees);
        }else if (employees.getId()!=null){
            //修改
            Employee emp = employee.openUpdate(employees.getId());
            employees.setWorkTime(emp.getWorkTime());
            employee.save(employees);
        }
        return "redirect:empList";
    }
    //打开修改页面
    @RequestMapping(value = "openUpdate", method = RequestMethod.GET)
    public String openUpdate(String id, Model model){
        Integer ids = Integer.parseInt(id);
        Employee emp=employee.openUpdate(ids);
        model.addAttribute("employee",emp);
        model.addAttribute("departmentList",employee.selectDepartmentList());
        return "insert";
    }
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(String id){
        Integer ids = Integer.parseInt(id);
        employee.delete(ids);
        return "redirect:empList";
    }
}
