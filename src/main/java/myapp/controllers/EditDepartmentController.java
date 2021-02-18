package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementation.inMemory.DepartmentsService;
import myapp.service.implementation.jdbc.DepartmentsService;
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
        String departmentName = request.getParameter("departmentName");
        Department department = departmentsService.getDepartmentByName(departmentName);

        if (department != null){
            request.setAttribute("department", department);

            switch (request.getMethod()) {
                case "POST":
                    String newName = request.getParameter("newName");

                    if (newName != null) {
                        department.setName(newName);
                        List<ConstraintViolation> violations = validator.validate(department);

                        if (!violations.isEmpty()) {
                            // возврат данных в форму на коррекцию пользователем
                            request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                            request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                        } else {
                            // Данные восприняты, готовность к следующему изменению отдела
                            if (departmentsService.updateExistingDepartment(department)) {
                                request.setAttribute("infoMessage", "Сохранены данные отдела: " + newName);
                                request.setAttribute("departmentName", newName);
                            }
                        }
                        violations.clear();
                    }
                case "GET":
                default:
            }
        }

        request.getRequestDispatcher("/views/editDepartment.jsp").forward(request, response);
    }
}
