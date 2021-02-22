package myapp.service;

import myapp.model.Employee;

import java.util.List;

public interface InterfaceEmployeesService {

     Employee getEmployeeById(int id);

     Employee getEmployeeByName(String name);

     String deleteEmployeeById(int id);

     List<Employee> getAllEmployees();

     List<Employee> getEmployeesByDepartmentId(int departmentId);

     boolean updateExistingEmployee(Employee newEmployee);

     boolean addEmployee(Employee employee);
}

