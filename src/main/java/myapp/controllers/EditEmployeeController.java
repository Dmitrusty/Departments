package myapp.controllers;

import myapp.model.Department;
import myapp.model.Employee;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementation.hibernate.DepartmentsService;
//import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.service.implementation.hibernate.EmployeesService;
import myapp.utils.validator.OvalValidator;
import net.sf.oval.ConstraintViolation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class EditEmployeeController implements InterfaceController {
    private final InterfaceEmployeesService employeesService;
    private final InterfaceDepartmentsService departmentsService;
    private final OvalValidator validator;

    public EditEmployeeController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
        validator = new OvalValidator();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean badFieldsExists = false;

        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Employee employee = employeesService.getEmployeeById(employeeID);

        if (employee != null) {

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
                        request.setAttribute("oldName", employee.getName());
                        request.setAttribute("employee", employee);
                        employee.setName(newName);
                        employee.setDepartmentID(newDepartment.getId());
                        employee.setSalary(newSalary);
                        employee.setStartDate(newStartDate);
                        List<ConstraintViolation> violations = validator.validate(employee);

                        if (!violations.isEmpty()) {
                            badFieldsExists = true;
                            request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                            request.setAttribute("salaryBadMessage", validator.getMessage("salary", violations));
                            request.setAttribute("startDateBadMessage", validator.getMessage("startDate", violations));
                        }

                        if (badFieldsExists) {
                            request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                        } else {
                            if (employeesService.updateExistingEmployee(employee)) {
                                request.setAttribute("infoMessage", "Сохранены данные сотрудника " + newName);
                                request.setAttribute("departmentName", newDepartmentName);
                                request.removeAttribute("employee");
                                request.removeAttribute("oldName");
                            }
                        }
                        violations.clear();
                    }
                    break;

                case "GET":
                default:
                    request.setAttribute("oldName", employee.getName());
                    String departmentName = request.getParameter("departmentName");
                    if (departmentName != null) {
                        Department department = departmentsService.getDepartmentByName(departmentName);
                        if (department != null) {
                            request.setAttribute("departmentName", departmentName);
                        } else {
                            request.setAttribute("infoMessage", "Не найден отдел с названием: " + departmentName);
                        }
                    }
            }
        } else {
            request.setAttribute("infoMessage", "Не найдены данные редактируемого сотрудника.");
        }

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/views/editEmployee.jsp").forward(request, response);
    }
}
