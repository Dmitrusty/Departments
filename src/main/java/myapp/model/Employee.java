package myapp.model;

import myapp.utils.validator.constraints.employee.CheckUniqueName;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table( name = "employees" )
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, max = 35, message = "Длина имени 2...35 символов.")
    @CheckWith(value = CheckUniqueName.class, message = "Это имя уже занято.")
    @MatchPattern(pattern = "[a-zA-Z_ А-Яа-я]+", message = "Допустимы только буквы и _")
    private String name;

    @NotNull(message = "Дата начала работы должна быть задана.")
    @NotEmpty(message = "Пожалуйста, введите дату начала работы.")
    @ValidateWithMethod(methodName = "isValidDate", parameterType = LocalDate.class, message = "Невозможная дата начала работы.")
    private LocalDate startDate;

    @NotNull(message = "Размер зарплаты должен быть задан.")
    @NotEmpty(message = "Пожалуйста, введите размер зарплаты.")
    @Min(value = 100.0, message = "Не может быть меньше 100")
    @Max(value = 100000.0, message = "Не может быть больше 100000")
    private double salary;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department workingDepartment;

    public Employee() {
    }

    public Employee(Long id, String name, LocalDate startDate, double salary, Department workingDepartment) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.workingDepartment = workingDepartment;
    }

    public Employee(String name, LocalDate startDate, double salary, Department workingDepartment) {
        this.id = 0L;
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.workingDepartment = workingDepartment;
    }

    private Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.startDate = employee.startDate;
        this.salary = employee.salary;
        this.workingDepartment = employee.workingDepartment;
    }

    // TODO delete it
    @Deprecated
    private boolean isValidDate(LocalDate date) {
        return date.isBefore(LocalDate.now().plusDays(1)) && date.isAfter(LocalDate.of(2000, 01, 01));
    }
    // TODO delete it
    @Deprecated
    public Employee makeCopy() {
        return new Employee(this);
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

    public Department getDepartment() {
        return workingDepartment;
    }

    public void setDepartment(Department workingDepartment) {
        this.workingDepartment = workingDepartment;
    }

}
