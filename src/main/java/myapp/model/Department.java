package myapp.model;

import myapp.utils.validator.constraints.department.CheckUniqueName;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, max = 35, message = "Название 2...35 символов.")
    @CheckWith(value = CheckUniqueName.class, message = "Это имя уже занято.")
    @MatchPattern(pattern = "[\\w_ А-Яа-я]+", message = "Допустимы только буквы, цифры и _")
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, targetEntity = Employee.class,
            mappedBy = "workingDepartment", orphanRemoval = true, fetch = FetchType.EAGER)
    List<Employee> employeesList = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department makeCopy() {
        return new Department(this.id, this.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public boolean addEmployee(Employee employee) {
        employee.setDepartment(this);
        return employeesList.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        employee.setDepartment(null);
        return employeesList.remove(employee);
    }

}

