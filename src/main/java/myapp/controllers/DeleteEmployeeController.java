package myapp.controllers;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementation.hibernate.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeController implements InterfaceController {
    private final InterfaceEmployeesService employeesService;

    public DeleteEmployeeController() {
        this.employeesService = new EmployeesService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("employeeId"));
        Employee employee = employeesService.getEmployeeById(id);

        if (id > 0 && employee != null) {
            employeesService.deleteEmployeeById(id);
            request.setAttribute("infoMessage", "Был удален сотрудник: " + employee.getName());
        } else {
            request.setAttribute("infoMessage", "Не найден удаляемый сотрудник.")   ;
        }

        request.setAttribute("departmentName", request.getParameter("departmentName"));
        request.getRequestDispatcher("/main/employees/list").forward(request, response);
    }
}
