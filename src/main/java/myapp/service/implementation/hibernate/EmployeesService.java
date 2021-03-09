package myapp.service.implementation.hibernate;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;

import java.util.List;

public class EmployeesService implements InterfaceEmployeesService {
    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return null;
    }

    @Override
    public String deleteEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        return null;
    }

    @Override
    public boolean updateExistingEmployee(Employee newEmployee) {
        return false;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }
}
