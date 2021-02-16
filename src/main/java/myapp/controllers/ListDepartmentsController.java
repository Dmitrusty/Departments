package myapp.controllers;

import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementations.inMemory.DepartmentsService;
import myapp.service.implementations.jdbc.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListDepartmentsController implements InterfaceController {
    private InterfaceDepartmentsService departmentsService;

    public ListDepartmentsController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            switch (request.getMethod()) {
            case "POST":
                break;
            case "GET":
            default:
                request.setAttribute("departmentsList", departmentsService.getAllDepartments());
                request.getRequestDispatcher("/views/listDepartments.jsp").forward(request, response);
        }
    }
}
