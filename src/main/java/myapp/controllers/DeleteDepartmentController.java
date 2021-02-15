package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementations.inMemory.DepartmentsService;

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
        String departmentName = request.getParameter("departmentName");
        Department department = departmentsService.getDepartmentByName(departmentName);

        if (department != null){
            String name = departmentsService.deleteDepartmentById(department.getId());
            if (name != null) {
                request.setAttribute("message", "Был удален отдел:");
                request.setAttribute("name", name);
            }
        } else {
            request.setAttribute("message", "для удаления не найден отдел:");
            request.setAttribute("name", department.getName());
        }
        
        request.getRequestDispatcher("/main/departments/list").forward(request, response);
    }
}
