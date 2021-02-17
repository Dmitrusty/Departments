package myapp.controllers;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
//import myapp.service.implementation.inMemory.DepartmentsService;
//import myapp.service.implementation.inMemory.EmployeesService;
import myapp.service.implementation.jdbc.DepartmentsService;
import myapp.service.implementation.jdbc.EmployeesService;
import myapp.service.InterfaceEmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListEmployeesController implements InterfaceController {
    private InterfaceEmployeesService employeesService;
    private InterfaceDepartmentsService departmentsService;

    public ListEmployeesController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getMethod()) {
            case "POST":
                break;
            case "GET":
            default:
                String departmentName = request.getParameter("departmentName");
                Department department = departmentsService.getDepartmentByName(departmentName);
                if (department != null && departmentName != null){
                    request.setAttribute("departmentName", departmentName);
                    request.setAttribute("employeesList", employeesService.getEmployeesByDepartmentId(department.getId()));
                } else {
                    request.setAttribute("message", "Задайте правильное название отдела для вывода списка его соторудников.");
                }
                request.getRequestDispatcher("/views/listEmployees.jsp").forward(request, response);
        }
    }
}
