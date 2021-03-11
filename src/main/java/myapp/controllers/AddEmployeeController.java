package myapp.controllers;

import myapp.model.Department;
import myapp.model.Employee;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementation.hibernate.DepartmentsService;
//import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.service.implementation.jdbc.EmployeesService;
import myapp.utils.validator.OvalValidator;
import net.sf.oval.ConstraintViolation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddEmployeeController implements InterfaceController {
    private final InterfaceEmployeesService employeesService;
    private final InterfaceDepartmentsService departmentsService;
    private final OvalValidator validator;

    public AddEmployeeController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
        validator = new OvalValidator();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean badFieldsExists = false;

        switch (request.getMethod()) {
            case "POST":
                String newDepartmentName = request.getParameter("newDepartmentName");
                Department newDepartment = departmentsService.getDepartmentByName(newDepartmentName);

                if (newDepartment == null) {
                    badFieldsExists = true;
                    request.setAttribute("departmentNameBadMessage", "Не найден отдел: " + newDepartmentName);
                    // восстановим старый departmentName и department
                    String oldDepartmentName = request.getParameter("departmentName");
                    request.setAttribute("departmentName", oldDepartmentName);
                    newDepartment = departmentsService.getDepartmentByName(oldDepartmentName);
                } else {
                    request.setAttribute("departmentName", newDepartmentName);
                }

                String newName = request.getParameter("newName");
                double newSalary = Double.parseDouble(request.getParameter("newSalary"));
                LocalDate newStartDate = LocalDate.parse(request.getParameter("newStartDate"));

                if (newName != null && newSalary != 0 && newStartDate != null) {
                    Employee newEmployee = new Employee(newName, newStartDate, newSalary, newDepartment.getId());
                    request.setAttribute("employee", newEmployee);
                    List<ConstraintViolation> violations = validator.validate(newEmployee);

                    if (!violations.isEmpty()) {
                        badFieldsExists = true;
                        // Выводим сообщения о неправильных полях:
                        request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                        request.setAttribute("salaryBadMessage", validator.getMessage("salary", violations));
                        request.setAttribute("startDateBadMessage", validator.getMessage("startDate", violations));
                    }

                    if (badFieldsExists) {
                        request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                    } else {
                        if (employeesService.addEmployee(newEmployee)) {
                            request.setAttribute("infoMessage", "Добавлен сотрудник: " + newName);
                            request.removeAttribute("employee");
                        }
                    }
                    violations.clear();
                }
                break;

            case "GET":
            default:
                String departmentName = request.getParameter("departmentName");
                if (departmentName != null) {
                    request.setAttribute("departmentName", departmentName);
                }
        }

        request.getRequestDispatcher("/views/addEmployee.jsp").forward(request, response);
    }
}
