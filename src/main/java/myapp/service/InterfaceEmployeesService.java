package myapp.service;

import myapp.model.Employee;

import java.util.List;

public interface InterfaceEmployeesService {

     Employee getEmployeeById(Long id);

     Employee getEmployeeByName(String name);

     String deleteEmployeeById(int id);

     List<Employee> getEmployeesByDepartmentId(Long departmentId);

     boolean updateExistingEmployee(Employee newEmployee);

     boolean addEmployee(Employee employee);
}

