package myapp.model;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {
    private String id = UUID.randomUUID().toString();
    private String name;
    private LocalDate startDate;
    private double salary;

    private String departmentName;

    public Employee(String name, LocalDate startDate, double salary, String departmentName) {
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    private Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.startDate = employee.startDate;
        this.salary = employee.salary;
        this.departmentName = employee.departmentName;
    }

    public Employee makeCopy(){
        return new Employee(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
