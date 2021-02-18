package myapp.controllers;

import myapp.model.Department;
import myapp.model.Employee;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.InterfaceEmployeesService;
//import myapp.service.implementation.inMemory.DepartmentsService;
//import myapp.service.implementation.inMemory.EmployeesService;
import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.service.implementation.jdbc.EmployeesService;
import myapp.utils.validator.OvalValidator;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddEmployeeController implements InterfaceController{
    private final InterfaceEmployeesService employeesService;
    private final InterfaceDepartmentsService departmentsService;
//    private final Validator validator;
    private final OvalValidator validator;

    public AddEmployeeController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
//        validator = new Validator();
        validator = new OvalValidator();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            switch (request.getMethod()){
                case "POST":
                    String newDepartmentName = request.getParameter("newDepartmentName");
                    Department newDepartment = departmentsService.getDepartmentByName(newDepartmentName);

                    if (newDepartment != null){
                        String newName = request.getParameter("newName");
                        double newSalary = Double.parseDouble(request.getParameter("newSalary"));
                        LocalDate newStartDate = LocalDate.parse(request.getParameter("newStartDate"));

                        if (newName != null && newSalary != 0 && newStartDate != null) {
                            Employee newEmployee = new Employee(newName, newStartDate, newSalary, newDepartment.getId());
                            List<ConstraintViolation> violations = validator.validate(newEmployee);

                            if (!violations.isEmpty()) {
                                // возврат данных в форму на коррекцию пользователем
                                // Восстановим введенные значения полей:
                                request.setAttribute("infoMessage", "Пожалуйста, введите правильные данные:");
                                request.setAttribute("bufferedName", newName);
                                request.setAttribute("departmentName", newDepartmentName);
                                request.setAttribute("bufferedSalary", newSalary);
                                request.setAttribute("bufferedStartDate", newStartDate);
                                // Выводим сообщения о неправильных полях:
                                request.setAttribute("nameBadMessage", validator.getMessage("name", violations));
                                request.setAttribute("departmentNameBadMessage", validator.getMessage("name", violations));
                                request.setAttribute("salaryBadMessage", validator.getMessage("salary", violations));
                                request.setAttribute("startDateBadMessage", validator.getMessage("startDate", violations));
                            } else {
                                // Данные восприняты, готовность к следующему добавлению отдела
                                if (employeesService.addEmployee(newEmployee)) {
                                    request.setAttribute("infoMessage", "Добавлен сотрудник: " + newName);
                                    request.setAttribute("departmentName", newDepartmentName);
                                }
                            }
                            violations.clear();
                        }
                    } else {
                        request.setAttribute("infoMessage", "Не найден отдел с названием: " + newDepartmentName);
                    }
                    break;



                case "GET":
                default:
                    String departmentName = request.getParameter("departmentName");
                    if (departmentName != null){
                        request.setAttribute("departmentName", departmentName);
                    }
            }


        request.getRequestDispatcher("/views/addEmployee.jsp").forward(request, response);
    }
}
