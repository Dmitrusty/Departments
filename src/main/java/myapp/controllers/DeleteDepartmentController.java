package myapp.controllers;

import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementations.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentController implements InterfaceController{
    private InterfaceDepartmentsService departmentsService;

    public DeleteDepartmentController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentID = request.getParameter("departmentID");
        if (departmentID != null) {
            String name = departmentsService.deleteDepartmentById(departmentID);
            if (name != null) {
                request.setAttribute("name", name);
                request.setAttribute("message", "Был удален отдел:");
            }
        }
        request.getRequestDispatcher("/main/departments/list").forward(request, response);
//        response.sendRedirect(request.getContextPath() + "/main/departments/list");
    }
}
