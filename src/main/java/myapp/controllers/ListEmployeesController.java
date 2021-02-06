package myapp.controllers;

import myapp.service.implementations.EmployeesService;
import myapp.service.InterfaceEmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListEmployeesController implements InterfaceController {
    private InterfaceEmployeesService employeesService;

    public ListEmployeesController() {
        this.employeesService = new EmployeesService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()) {
            case "POST":
                break;
            case "GET":
            default:
                request.setAttribute("employeesList", employeesService.getAllEmployees());
                request.getRequestDispatcher("/views/listEmployees.jsp").forward(request, response);
        }
    }
}
