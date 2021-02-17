package myapp.service.implementation.jdbc;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesService implements InterfaceEmployeesService {
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employees WHERE id = ?";
    private static final String GET_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String GET_ALL_EMPLOYEES_BY_DEPARTMENT = "SELECT * FROM employees WHERE departmentId = ?";
    private static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE employees SET name = ?, startDate = ?, salary = ?, departmentId = ? WHERE id = ?";
    private static final String ADD_EMPLOYEE_BY_ID = "INSERT INTO employees (name, startDate, salary, departmentId) VALUES (?,?,?,?)";

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(id,
                        resultSet.getString("name"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("departmentId"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public String deleteEmployeeById(int id) {
        String name = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement psName = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
             PreparedStatement psDelete = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {

            psName.setInt(1, id);
            ResultSet resultSet = psName.executeQuery();
            if (resultSet.next()) {
                String bufferName = resultSet.getString("name");

                psDelete.setInt(1, id);
                if (psDelete.executeUpdate() > 0) {
                    name = bufferName;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> result = new ArrayList<>();
        Employee employee;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("departmentId"));
                result.add(employee);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        List<Employee> result = new ArrayList<>();
        Employee employee;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES_BY_DEPARTMENT)) {

            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("departmentId"));
                result.add(employee);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateExistingEmployee(Employee newEmployee) {
        boolean result = false;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_BY_ID)) {

            preparedStatement.setString(1, newEmployee.getName());
            preparedStatement.setDate(2, Date.valueOf(newEmployee.getStartDate()));
            preparedStatement.setDouble(3, newEmployee.getSalary());
            preparedStatement.setInt(4, newEmployee.getDepartmentID());
            preparedStatement.setInt(5, newEmployee.getId());
            result = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        boolean result = false;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE_BY_ID)) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDate(2, Date.valueOf(employee.getStartDate()));
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setInt(4, employee.getDepartmentID());
            result = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
