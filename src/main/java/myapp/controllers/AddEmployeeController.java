package myapp.controllers;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementations.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddEmployeeController implements InterfaceController{
    private InterfaceEmployeesService employeesService;

    public AddEmployeeController() {
        this.employeesService = new EmployeesService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()){
            case "POST":
                String name = request.getParameter("name");
                String departmentName = request.getParameter("departmentName");
                LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                double salary = Double.parseDouble(request.getParameter("salary"));
                if (name != null && departmentName != null && startDate != null && salary != 0) {
                    Employee newEmployee = new Employee(name, startDate, salary, departmentName);
                    if (employeesService.addEmployee(newEmployee)) {
                        request.setAttribute("name", name);
                        request.setAttribute("message", "Добавлен сотрудник:");
                    }
                }
            case "GET":
            default:
        }
        request.getRequestDispatcher("/views/addEmployee.jsp").forward(request, response);
    }
}
