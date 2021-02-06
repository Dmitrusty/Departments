package myapp.controllers;

import myapp.service.InterfaceEmployeesService;
import myapp.service.implementations.DepartmentsService;
import myapp.service.implementations.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeController implements InterfaceController{
    private InterfaceEmployeesService employeesService;

    public DeleteEmployeeController() {
        this.employeesService = new EmployeesService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");
        if (employeeID != null) {
            String name = employeesService.deleteEmployeeById(employeeID);
            if (name != null){
                request.setAttribute("name", name);
                request.setAttribute("message", "Был удален сотрудник:");
            }
        }
//        response.sendRedirect(request.getContextPath() + "/main/employees/list");
        request.getRequestDispatcher("/main/employees/list").forward(request, response);
    }
}
