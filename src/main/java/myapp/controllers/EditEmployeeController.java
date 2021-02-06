package myapp.controllers;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementations.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class EditEmployeeController implements InterfaceController{
    private InterfaceEmployeesService employeesService;

    public EditEmployeeController() {
        this.employeesService = new EmployeesService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID");
        if (employeeID != null) {
            Employee oldEmployee = employeesService.getEmployeeById(employeeID);
            if (oldEmployee != null) {
                switch (request.getMethod()) {
                    case "POST":
                        String name = request.getParameter("name");
                        String departmentName = request.getParameter("departmentName");
                        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                        double salary = Double.parseDouble(request.getParameter("salary"));
                        if (name != null && departmentName != null && startDate != null && salary != 0) {
                            oldEmployee.setName(name);
                            oldEmployee.setDepartmentName(departmentName);
                            oldEmployee.setStartDate(startDate);
                            oldEmployee.setSalary(salary);
                            if (employeesService.updateExistingEmployee(oldEmployee)) {
                                request.setAttribute("message", name);
                                request.setAttribute("message", "Изменены данные сотрудника:");
                            }
                        }
                    case "GET":
                    default:
                        request.setAttribute("employee", oldEmployee);
                }
            }
        }
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/views/editEmployee.jsp").forward(request, response);


    }
}
