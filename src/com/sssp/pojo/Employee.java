package com.sssp.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Table(name="SSSP_EMPLOYEE")
@Entity
public class Employee implements Serializable {

    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private Date workTime;
    private Department department;
    public Employee(){}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", workTime=" + workTime +
                ", department=" + department +
                '}';
    }

    public Employee(Integer id, String name, Date birth, Date workTime, Department department) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.workTime = workTime;
        this.department = department;
    }

    //设置主键，并且设置自增
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //设置时间日期，精确到日
    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    //设置时间日期，精确到时分秒
    @Temporal(TemporalType.TIMESTAMP)
    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    //配置多对一关系，JoinColumn设置的是外键关联的字段
    @JoinColumn(name = "DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
