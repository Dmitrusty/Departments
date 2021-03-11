package myapp.controllers;

import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.service.implementation.hibernate.DepartmentsService;

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
        request.setAttribute("departmentsList", departmentsService.getAllDepartments());
        request.getRequestDispatcher("/views/listDepartments.jsp").forward(request, response);
    }
}
