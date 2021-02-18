package myapp.utils.validator.constraints.department;

import myapp.model.Department;
import myapp.service.implementation.jdbc.DepartmentsService;
import net.sf.oval.constraint.CheckWithCheck;

public class CheckUniqueName implements CheckWithCheck.SimpleCheck {

    private final DepartmentsService departmentsService;

    public CheckUniqueName() {
        departmentsService = new DepartmentsService();
    }

    @Override
    public boolean isSatisfied(Object validatedDepartment, Object name) {
        Department vDepartment = (Department) validatedDepartment;
        Department departmentFromDB = departmentsService.getDepartmentByName(vDepartment.getName());
        return departmentFromDB == null;
    }
}

//public class CheckUniqueName implements CheckWithCheck.SimpleCheck {
//
//    private final DepartmentServiceImpl departmentService;
//
//    public CheckUniqueName() {
//        departmentService = new DepartmentServiceImpl();
//    }
//
//    @Override
//    public boolean isSatisfied(Object validatedDepartment, Object name) {
//        Department department = (Department) validatedDepartment;
//        Department departmentFromDB = departmentService.findByName(department.getName());
//        try {
//            if (departmentFromDB == null) {
//                return true;
//            }
//            return !(departmentFromDB.getId().equals(department.getId()) || departmentFromDB.getName().equals(department.getName()));
//        } catch (Exception ignored) {
//            return true;
//        }
//    }
//}