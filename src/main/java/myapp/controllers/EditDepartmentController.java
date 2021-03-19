package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementation.hibernate.DepartmentsService;
import myapp.utils.validator.OvalValidator;
import net.sf.oval.ConstraintViolation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditDepartmentController implements InterfaceController {
    private final InterfaceDepartmentsService departmentsService;
    private final OvalValidator validator;

    public EditDepartmentController() {
        this.departmentsService = new DepartmentsService();
        validator = new OvalValidator();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean goToListDepartments = false;

        String departmentName = request.getParameter("departmentName");
        Department department = departmentsService.getDepartmentByName(departmentName);

        if (department != null) {
            request.setAttribute("department", department);

            switch (request.getMethod()) {
                case "POST":
                    String newName = request.getParameter("newName");

                    if (newName != null) {
                        department.setName(newName);
                        List<ConstraintViolation> violations = validator.validate(department);

                        if (!violations.isEmpty()) {
                            request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                            request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                        } else {
                            departmentsService.updateExistingDepartment(department);
                            request.setAttribute("infoMessage", "Сохранены данные отдела: " + newName);
                            goToListDepartments = true;

                        }
                        violations.clear();
                    }
                case "GET":
                default:
            }
        }

        if (goToListDepartments){
            request.getRequestDispatcher("/main/departments/list").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/editDepartment.jsp").forward(request, response);
        }
    }
}
