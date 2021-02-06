package myapp.service;

import myapp.model.Department;

import java.util.List;

public interface InterfaceDepartmentsService {

    Department getDepartmentById(String id);

    String deleteDepartmentById(String id);

    List<Department> getAllDepartments();

    boolean updateExistingDepartment(Department newDepartment);

    boolean addDepartment(Department department);
}
