package myapp.service.implementation.inMemory;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeesService implements InterfaceEmployeesService {
    // todo просмотреть все ругательства IDEа
    private static List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Bob Marley", LocalDate.of(1997, 01, 01), 2000., DepartmentsService.getIdByName("IT")),
            new Employee("Исаак Ньютон", LocalDate.of(1685, 03, 03), 5000., DepartmentsService.getIdByName("IT")),
            new Employee("Альберт Эйнштейн", LocalDate.of(1943, 06, 25), 4000., DepartmentsService.getIdByName("IT")),
            new Employee("Уи́лбур Райт", LocalDate.of(1906, 11, 11), 1100., DepartmentsService.getIdByName("Sales")),
            new Employee("О́рвил  Райт", LocalDate.of(1906, 11, 11), 1100., DepartmentsService.getIdByName("Sales")),
            new Employee("Томас Эдисон", LocalDate.of(1927, 12, 25), 3000., DepartmentsService.getIdByName("Security")),
            new Employee("Адольф Гитлер", LocalDate.of(1925, 11, 15), 100., DepartmentsService.getIdByName("Cleaners"))
    ));

    private int getIndexById(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Employee getEmployeeById(int id) {
        int index = getIndexById(id);
        return index < 0 ? null : employees.get(index).makeCopy();
    }

    @Override
    public String deleteEmployeeById(int id) {
        int index = getIndexById(id);
        if (index >= 0) {
            return employees.remove(index).getName();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees){
            result.add(e.makeCopy());
        }
        return result;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        List<Employee> result = new ArrayList<>();
        for (Employee e : employees){
            if (e.getDepartmentID() == departmentId) {
                result.add(e.makeCopy());
            }
        }
        return result;
    }

    @Override
    public boolean updateExistingEmployee(Employee newEmployee) {
        int index = getIndexById(newEmployee.getId());
        if (index >= 0) {
            employees.set(index, newEmployee);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }
}
