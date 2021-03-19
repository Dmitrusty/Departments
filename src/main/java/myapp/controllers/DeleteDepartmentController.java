package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementation.hibernate.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentController implements InterfaceController {
    private final InterfaceDepartmentsService departmentsService;

    public DeleteDepartmentController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentName = request.getParameter("departmentName");
        Department department = departmentsService.getDepartmentByName(departmentName);

        if (department != null) {
            departmentsService.deleteDepartmentById(department.getId());
            request.setAttribute("infoMessage", "Был удален отдел: " + department.getName());

        } else {
            request.setAttribute("infoMessage", "для удаления не найден отдел: " + departmentName);
        }

        request.getRequestDispatcher("/main/departments/list").forward(request, response);
    }
}