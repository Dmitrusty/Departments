package myapp.service.implementations.inMemory;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentsService implements InterfaceDepartmentsService {
    private static List<Department> departments = new ArrayList<>(Arrays.asList(
            new Department("IT"),
            new Department( "Sales"),
            new Department( "Security"),
            new Department( "Cleaners")
    ));

    private int getIndexById(int id) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId()== id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Department getDepartmentById(int id) {
        int index = getIndexById(id);
        return index < 0 ? null : departments.get(index).makeCopy();
    }

    @Override
    public Department getDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department.makeCopy();
            }
        }
        return null;
    }

    public String getNameById(int id) {
        int index = getIndexById(id);
        return index < 0 ? null : departments.get(index).makeCopy().getName();
    }

    public static int getIdByName(String name) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getName().equals(name)) {
                return departments.get(i).getId();
            }
        }
        return -1;
    }

    @Override
    public String deleteDepartmentById(int id) {
        int index = getIndexById(id);
        if (index >= 0) {
            return departments.remove(index).getName();
        }
        return null;
    }
    @Override
    public List<Department> getAllDepartments() {
        List<Department> result = new ArrayList<>();
        for (Department d : departments){
            result.add(d.makeCopy());
        }
        return result;
    }

    // в новом отделе id = id обновляемого отдела
    @Override
    public boolean updateExistingDepartment(Department newDepartment) {
        int index = getIndexById(newDepartment.getId());
        if (index >= 0) {
            departments.set(index, newDepartment);
            return true;
        }
        return false;
    }

    @Override
    public boolean addDepartment(Department department) {
        return departments.add(department);
    }
}
