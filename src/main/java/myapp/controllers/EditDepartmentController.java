package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementations.inMemory.DepartmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDepartmentController implements InterfaceController {
    private InterfaceDepartmentsService departmentsService;

    public EditDepartmentController() {
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentID = Integer.parseInt(request.getParameter("departmentID"));
        if (departmentID > 0) {
            Department oldDepartment = departmentsService.getDepartmentById(departmentID);
            if (oldDepartment != null) {
                switch (request.getMethod()) {
                    case "POST":
                        String name = request.getParameter("name");
                        if (name != null) {
                            oldDepartment.setName(name);
                            if (departmentsService.updateExistingDepartment(oldDepartment)) {
                                request.setAttribute("name", name);
                                request.setAttribute("message", "Изменены данные отдела:");
                            }
                        }
                    case "GET":
                    default:
                        request.setAttribute("department", oldDepartment);
                }
            }
        }

        request.getRequestDispatcher("/views/editDepartment.jsp").forward(request, response);
    }
}
