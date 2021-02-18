package myapp.model;

import myapp.utils.DatabaseConnection;
import net.sf.oval.constraint.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Employee {
    private static int counter = 0;

    private final int id;
    @NotNull(message = "Название должно быть задано.")
    @NotEmpty(message = "Пожалуйста, введите название.")
    @Length(min = 2, max = 35, message = "Длина имени 2...35 символов.")
    @ValidateWithMethod(methodName = "isUniqueEmpName", parameterType = String.class, message = "Это имя уже занято.")
    @MatchPattern(pattern = "[a-zA-Z ]+", message = "Допустимы только буквы и _")
    private String name;
    private LocalDate startDate;
    private double salary;
    private int departmentID;

    public Employee(int id, String name, LocalDate startDate, double salary, int departmentID) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.departmentID = departmentID;
    }

    public Employee(String name, LocalDate startDate, double salary, int departmentID) {
        this.id = ++counter;
        this.name = name;
        this.startDate = startDate;
        this.salary = salary;
        this.departmentID = departmentID;
    }

    private Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.startDate = employee.startDate;
        this.salary = employee.salary;
        this.departmentID = employee.departmentID;
    }

    private boolean isUniqueEmpName (String name){
        int count = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(name) AS count FROM employees WHERE name = ?")) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count == 0;
    }

    public Employee makeCopy(){
        return new Employee(this);
    }

    public int getId() {
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

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

}
