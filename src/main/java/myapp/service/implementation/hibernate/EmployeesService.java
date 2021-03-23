package myapp.service.implementation.hibernate;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.utils.HiberNative;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.logging.Logger;

public class EmployeesService implements InterfaceEmployeesService {
    private final Logger logger;

    public EmployeesService() {
        logger = Logger.getLogger(String.valueOf(DepartmentsService.class));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        Employee result = session.find(Employee.class, id);

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        Employee result = (Employee) session.createQuery("select e from Employee e where e.name = :name")
                .setParameter("name", name)
                .uniqueResult();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.find(Employee.class, id);
        session.delete(employee);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateExistingEmployee(Employee newEmployee) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(newEmployee);

        transaction.commit();
        session.close();
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();
        session.close();
    }
}
