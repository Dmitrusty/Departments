package myapp.service;

import myapp.model.Employee;

import java.util.List;

public interface InterfaceEmployeesService {

     Employee getEmployeeById(String id);

     String deleteEmployeeById(String id);

     List<Employee> getAllEmployees();

     boolean updateExistingEmployee(Employee newEmployee);

     boolean addEmployee(Employee employee);
}

