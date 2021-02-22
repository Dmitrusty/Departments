package myapp.utils.validator.constraints.employee;

import myapp.model.Employee;
import myapp.service.implementation.jdbc.EmployeesService;
import net.sf.oval.constraint.CheckWithCheck;

public class CheckUniqueName implements CheckWithCheck.SimpleCheck {

    private final EmployeesService employeeService;

    public CheckUniqueName() {
        employeeService = new EmployeesService();
    }

    @Override
    public boolean isSatisfied(Object validatedEmployee, Object name) {
        Employee employee = (Employee) validatedEmployee;
        Employee employeeFromDB = employeeService.getEmployeeByName(employee.getName());

        // Это создание сотрудника с уникальным именем. Пропускаем
        if (employeeFromDB == null) {
            return true;
        }

        if (employeeFromDB.getId() == employee.getId()) {
            // Сотрудник один и тот же
            // Разрешаем если имя не меняется, и наоборот
            return employeeFromDB.getName().equals(employee.getName());
        } else {
            // Сотрудник новый
            // Разрешаем если имя тоже новое, и наоборот
            return !employeeFromDB.getName().equals(employee.getName());
        }
    }
}

//public class CheckUniqueName implements CheckWithCheck.SimpleCheck {
//
//    private final employeeServiceImpl employeeService;
//
//    public CheckUniqueName() {
//        employeeService = new employeeServiceImpl();
//    }
//
//    @Override
//    public boolean isSatisfied(Object validatedemployee, Object name) {
//        employee employee = (employee) validatedemployee;
//        employee employeeFromDB = employeeService.findByName(employee.getName());
//        try {
//            if (employeeFromDB == null) {
//                return true;
//            }
//            return !(employeeFromDB.getId().equals(employee.getId()) || employeeFromDB.getName().equals(employee.getName()));
//        } catch (Exception ignored) {
//            return true;
//        }
//    }
//}