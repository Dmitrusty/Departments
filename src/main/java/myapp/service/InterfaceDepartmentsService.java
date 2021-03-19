package myapp.service;

import myapp.model.Department;

import java.util.List;

public interface InterfaceDepartmentsService {

    Department getDepartmentByName(String name);

    void deleteDepartmentById(Long id);

    List<Department> getAllDepartments();

    void updateExistingDepartment(Department newDepartment);

    void addDepartment(Department department);
}
