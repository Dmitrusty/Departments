package myapp.service.implementation.hibernate;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.utils.HiberNative;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Logger;

public class DepartmentsService implements InterfaceDepartmentsService {
    private final Logger logger;

    public DepartmentsService() {
        logger = Logger.getLogger(String.valueOf(DepartmentsService.class));
    }

    @Override
    public Department getDepartmentByName(String name) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        Department result = (Department) session.createQuery("select d from Department d where d.name = :name")
            .setParameter("name", name)
            .uniqueResult();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        Department department = session.find(Department.class, id);
        session.delete(department);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Department> getAllDepartments() {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        List<Department> result = session.createQuery("select d from Department d").list();

        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void updateExistingDepartment(Department newDepartment) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(newDepartment);

        transaction.commit();
        session.close();
    }

    @Override
    public void addDepartment(Department department) {
        Session session = HiberNative.getSession();
        Transaction transaction = session.beginTransaction();

        session.save(department);

        transaction.commit();
        session.close();
    }
}
