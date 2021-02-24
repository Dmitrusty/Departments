package myapp.utils;

import myapp.controllers.*;

import java.util.HashMap;
import java.util.Map;

public class URIDispatcher {

    private final Map<String, InterfaceController> controllersMap;


    public URIDispatcher() {
        controllersMap = new HashMap<>();
        controllersMap.put("/main/login", new LoginController());

        controllersMap.put("/main/departments/list", new ListDepartmentsController());
        controllersMap.put("/main/departments/add", new AddDepartmentController());
        controllersMap.put("/main/departments/edit", new EditDepartmentController());
        controllersMap.put("/main/departments/delete", new DeleteDepartmentController());

        controllersMap.put("/main/employees/list", new ListEmployeesController());
        controllersMap.put("/main/employees/add", new AddEmployeeController());
        controllersMap.put("/main/employees/edit", new EditEmployeeController());
        controllersMap.put("/main/employees/delete", new DeleteEmployeeController());
    }

    public InterfaceController getController(String s) {
        if (controllersMap.containsKey(s)) {
            return controllersMap.get(s);
        } else return controllersMap.get("/main/departments/list");
    }
}
