package myapp.utils.validator.constraints.department;

import myapp.model.Department;
import myapp.service.implementation.hibernate.DepartmentsService;
//import myapp.service.implementation.jdbc.DepartmentsService;
import net.sf.oval.constraint.CheckWithCheck;

public class CheckUniqueName implements CheckWithCheck.SimpleCheck {

    private final DepartmentsService departmentsService;

    public CheckUniqueName() {
        departmentsService = new DepartmentsService();
    }

    @Override
    public boolean isSatisfied(Object validatedDepartment, Object name) {
        Department department = (Department) validatedDepartment;
        String newName = (String) name;
        Department departmentFromDB = departmentsService.getDepartmentByName(newName);

        if (departmentFromDB == null) { // Уникальное новое имя - разрешаем
            return true;
        }

        // Новое имя отдела уже используется
        if (departmentFromDB.getId() == department.getId()) {
            // Отдел один и тот же - идет коррекция
            // Разрешаем если имя не меняется, и наоборот
            return departmentFromDB.getName().equals(newName);
        } else {
            // Отдел новый, идет добавление
            // Разрешаем если имя тоже новое, и наоборот
            return !departmentFromDB.getName().equals(newName);
        }
    }
}