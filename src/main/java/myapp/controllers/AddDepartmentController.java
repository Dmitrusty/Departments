package myapp.controllers;


import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementations.inMemory.DepartmentsService;
import myapp.service.implementations.jdbc.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDepartmentController implements InterfaceController {
    private InterfaceDepartmentsService departmentsService;

    public AddDepartmentController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()){
            case "POST":
                String name = request.getParameter("name");
                if (name != null) {
                    Department department = new Department(name);
                    if (departmentsService.addDepartment(department)) {
                        request.setAttribute("name", name);
                        request.setAttribute("message", "Был добавлен новый отдел:");
                    }
                }
            case "GET":
            default:
                request.getRequestDispatcher("/views/addDepartment.jsp").forward(request, response);
        }
    }
}
