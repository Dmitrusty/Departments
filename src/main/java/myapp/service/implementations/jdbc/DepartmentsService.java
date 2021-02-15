package myapp.service.implementations.jdbc;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;

import java.util.List;

public class DepartmentsService implements InterfaceDepartmentsService {
    @Override
    public Department getDepartmentById(int id) {
        return null;
    }

    @Override
    public Department getDepartmentByName(String name) {
        return null;
    }

    @Override
    public String deleteDepartmentById(int id) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public boolean updateExistingDepartment(Department newDepartment) {
        return false;
    }

    @Override
    public boolean addDepartment(Department department) {
        return false;
    }
}
