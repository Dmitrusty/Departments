package myapp.service.implementation.jdbc;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsService implements InterfaceDepartmentsService {

    @Override
    public Department getDepartmentById(int id) {
        Department department = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = new Department(id, resultSet.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments WHERE name = ?")) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                department = new Department(resultSet.getInt("id"), resultSet.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    @Override
    public String deleteDepartmentById(int id) {
        String name = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement psName = connection.prepareStatement("SELECT * FROM departments WHERE id = ?");
            PreparedStatement psDelete = connection.prepareStatement("DELETE FROM departments WHERE id = ?")) {

            psName.setInt(1, id);
            ResultSet resultSet = psName.executeQuery();
            if (resultSet.next()) {
                String bufferName = resultSet.getString("name");

                psDelete.setInt(1, id);
                if (psDelete.executeUpdate() > 0){
                    name = bufferName;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> result = new ArrayList<>();
        Department department;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = new Department(resultSet.getInt("id"), resultSet.getString("name"));
                result.add(department);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateExistingDepartment(Department department) {
        boolean result = false;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE departments SET name = ? WHERE id = ?")) {

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            result = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addDepartment(Department department) {
        boolean result = false;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO departments (name) VALUES (?)")) {

            preparedStatement.setString(1, department.getName());
            result = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
