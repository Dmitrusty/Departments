package myapp.service;

import myapp.model.Employee;

import java.util.List;

public interface InterfaceEmployeesService {

     Employee getEmployeeById(int id);

     String deleteEmployeeById(int id);

     List<Employee> getAllEmployees();

     List<Employee> getEmployees(int departmentId);

     boolean updateExistingEmployee(Employee newEmployee);

     boolean addEmployee(Employee employee);
}

