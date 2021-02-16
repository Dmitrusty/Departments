package myapp.controllers;

import myapp.model.Department;
import myapp.model.Employee;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.InterfaceEmployeesService;
//import myapp.service.implementations.inMemory.DepartmentsService;
import myapp.service.implementations.jdbc.DepartmentsService;
import myapp.service.implementations.inMemory.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class EditEmployeeController implements InterfaceController{
    private final InterfaceEmployeesService employeesService;
    private final InterfaceDepartmentsService departmentsService;

    public EditEmployeeController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        Employee employee = employeesService.getEmployeeById(employeeID);
        if (employee != null) {
            switch (request.getMethod()) {
                case "POST":
                    String newDepartmentName = request.getParameter("newDepartmentName");
                    Department newDepartment = departmentsService.getDepartmentByName(newDepartmentName);
                    if (newDepartment != null){
                        int newDepartmentID = newDepartment.getId();
                        String newName = request.getParameter("newName");
                        LocalDate newStartDate = LocalDate.parse(request.getParameter("newStartDate"));
                        double newSalary = Double.parseDouble(request.getParameter("newSalary"));
                        if (newName != null && newDepartmentID > 0 && newStartDate != null && newSalary != 0) {
                            employee.setName(newName);
                            employee.setDepartmentID(newDepartmentID);
                            employee.setStartDate(newStartDate);
                            employee.setSalary(newSalary);
                            if (employeesService.updateExistingEmployee(employee)) {
                                request.setAttribute("message", "Сохранены данные сотрудника ");
                                request.setAttribute("name", newName);
                                request.setAttribute("departmentName", newDepartmentName);
                            }
                        }
                    } else {
                        request.setAttribute("message", "Не найден отдел с названием: ");
                        request.setAttribute("name", newDepartmentName);
                    }
                    break;
                case "GET":
                default:
                    String departmentName = request.getParameter("departmentName");
                    if (departmentName != null){
                        Department department = departmentsService.getDepartmentByName(departmentName);
                        if (department != null) {
                            request.setAttribute("departmentName", departmentName);
                        } else {
                            request.setAttribute("message", "Не найден отдел с названием: ");
                            request.setAttribute("name", departmentName);
                        }
                    }
            }
        } else {
            request.setAttribute("message", "Не найдены данные редактируемого сотрудника.");
        }

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/views/editEmployee.jsp").forward(request, response);
    }
}
