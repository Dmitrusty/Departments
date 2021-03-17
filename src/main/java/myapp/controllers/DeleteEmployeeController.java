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
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));

        if (employeeID > 0) {
            String name = employeesService.deleteEmployeeById(employeeID);
            if (name != null) {
                request.setAttribute("infoMessage", "Был удален сотрудник: " + name);
                request.setAttribute("departmentName", request.getParameter("departmentName"));
            }
        }

        request.getRequestDispatcher("/main/employees/list").forward(request, response);
    }

    void handle(long id){
        Employee employee = employeesService.getEmployeeById(id);
        if(!employeesService.deleteEmployee(employee)) {
            System.out.println("Не получилось удалить сотрудника " + employee.getName());
        }
    }
}
