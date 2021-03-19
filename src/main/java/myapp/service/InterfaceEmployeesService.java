package myapp.service;

import myapp.model.Employee;

public interface InterfaceEmployeesService {

     Employee getEmployeeById(Long id);

     Employee getEmployeeByName(String name);

     void deleteEmployeeById(Long id);

     void updateExistingEmployee(Employee newEmployee);

     void addEmployee(Employee employee);
}

