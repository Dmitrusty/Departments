package myapp.controllers;

import myapp.service.InterfaceEmployeesService;
//import myapp.service.implementation.inMemory.EmployeesService;
import myapp.service.implementation.jdbc.EmployeesService;

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
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        if (employeeID > 0) {
            String name = employeesService.deleteEmployeeById(employeeID);
            if (name != null){
                request.setAttribute("infoMessage", "Был удален сотрудник: " + name);
                request.setAttribute("departmentName", request.getParameter("departmentName"));
//                request.setAttribute("departmentID", request.getParameter("departmentID"));
            }
        }
//        response.sendRedirect(request.getContextPath() + "/main/employees/list");
        request.getRequestDispatcher("/main/employees/list").forward(request, response);
    }
}
