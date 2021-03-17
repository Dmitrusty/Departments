package myapp.service;

import myapp.model.Department;

import java.util.List;

public interface InterfaceDepartmentsService {

    Department getDepartmentByName(String name);

    String deleteDepartmentById(Long id);

    List<Department> getAllDepartments();

    boolean updateExistingDepartment(Department newDepartment);

    boolean addDepartment(Department department);
}
