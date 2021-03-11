package myapp.controllers;


import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.implementation.hibernate.DepartmentsService;
//import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.utils.validator.OvalValidator;
import net.sf.oval.ConstraintViolation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddDepartmentController implements InterfaceController {
    private final InterfaceDepartmentsService departmentsService;
    private final OvalValidator validator;

    public AddDepartmentController() {
        this.departmentsService = new DepartmentsService();
        validator = new OvalValidator();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()) {
            case "POST":
                String name = request.getParameter("name");
                if (name != null) {
                    Department department = new Department(name);
                    List<ConstraintViolation> violations = validator.validate(department);

                    if (!violations.isEmpty()) {
                        request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                        request.setAttribute("bufferedName", name);
                        request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                    } else {
                        if (departmentsService.addDepartment(department)) {
                            request.setAttribute("infoMessage", "Был добавлен новый отдел: " + name);
                        }
                    }
                    violations.clear();
                }
            case "GET":
            default:
                request.getRequestDispatcher("/views/addDepartment.jsp").forward(request, response);
        }
    }
}
