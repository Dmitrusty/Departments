package myapp.service.implementations;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentsService implements InterfaceDepartmentsService {
    private static List<Department> departments = new ArrayList<>(Arrays.asList(
            new Department("Sales"),
            new Department("IT"),
            new Department("Security"),
            new Department("Cleaners")
    ));

    private int getIndexById(String id) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Department getDepartmentById(String id) {
        int index = getIndexById(id);
        return index < 0 ? null : departments.get(index).makeCopy();
    }

    public String getNameById(String id) {
        int index = getIndexById(id);
        return index < 0 ? null : departments.get(index).makeCopy().getName();
    }

    public static String getIdByName(String name) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getName().equals(name)) {
                return departments.get(i).getId();
            }
        }
        return null;
    }

    @Override
    public String deleteDepartmentById(String id) {
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
