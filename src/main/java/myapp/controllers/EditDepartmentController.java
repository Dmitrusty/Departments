package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementations.inMemory.DepartmentsService;
import myapp.service.implementations.jdbc.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDepartmentController implements InterfaceController {
    private final InterfaceDepartmentsService departmentsService;

    public EditDepartmentController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departmentName = request.getParameter("departmentName");
        Department department = departmentsService.getDepartmentByName(departmentName);

        if (department != null){
            request.setAttribute("department", department);

            switch (request.getMethod()) {
                case "POST":
                    String newName = request.getParameter("newName");
                    if (newName != null) {
                        department.setName(newName);
                        if (departmentsService.updateExistingDepartment(department)) {
                            request.setAttribute("message", "Сохранены данные отдела:");
                            request.setAttribute("name", newName);
                            request.setAttribute("departmentName", newName);
                        }
                    }
                case "GET":
                default:
            }
        }

        request.getRequestDispatcher("/views/editDepartment.jsp").forward(request, response);
    }
}
