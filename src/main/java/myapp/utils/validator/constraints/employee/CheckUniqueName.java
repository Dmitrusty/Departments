package myapp.utils.validator.constraints.employee;

import myapp.model.Employee;
import myapp.service.implementation.hibernate.EmployeesService;
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

        if (employeeFromDB == null) { // Уникальное новое имя - разрешаем
            return true;
        }

        if (employeeFromDB.getId() == employee.getId()) {
            // Сотрудник один и тот же, идет коррекция
            // Разрешаем если имя не меняется, и наоборот
            return employeeFromDB.getName().equals(employee.getName());
        } else {
            // Сотрудник новый, идет добавление
            // Разрешаем если имя тоже новое, и наоборот
            return !employeeFromDB.getName().equals(employee.getName());
        }
    }
}