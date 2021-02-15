package myapp.controllers;

import myapp.model.Department;
import myapp.model.Employee;
import myapp.service.InterfaceDepartmentsService;
import myapp.service.InterfaceEmployeesService;
import myapp.service.implementations.inMemory.DepartmentsService;
import myapp.service.implementations.inMemory.EmployeesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AddEmployeeController implements InterfaceController{
    private final InterfaceEmployeesService employeesService;
    private final InterfaceDepartmentsService departmentsService;

    public AddEmployeeController() {
        this.employeesService = new EmployeesService();
        this.departmentsService = new DepartmentsService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo departmentName - department = текущий отдел редактируемого сотрудника
        //todo newDepartmentName - newDepartment = новый отдел редактируемого сотрудника




            switch (request.getMethod()){
                case "POST":
                    String newDepartmentName = request.getParameter("newDepartmentName");
                    Department newDepartment = departmentsService.getDepartmentByName(newDepartmentName);
                    if (newDepartment != null){
                        String newName = request.getParameter("newName");
                        LocalDate newStartDate = LocalDate.parse(request.getParameter("newStartDate"));
                        double newSalary = Double.parseDouble(request.getParameter("newSalary"));

                        if (newName != null && newStartDate != null && newSalary != 0) {
                            Employee newEmployee = new Employee(newName, newStartDate, newSalary, newDepartment.getId());
                            if (employeesService.addEmployee(newEmployee)) {
                                request.setAttribute("message", "Добавлен сотрудник:");
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
                        request.setAttribute("departmentName", departmentName);
                    }
            }


        request.getRequestDispatcher("/views/addEmployee.jsp").forward(request, response);
    }
}
