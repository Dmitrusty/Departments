package myapp.service.implementation.hibernate;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.utils.HibernateSetup;

import javax.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesService implements InterfaceEmployeesService {
    private final Logger logger;

    public EmployeesService() {
        logger = Logger.getLogger(String.valueOf(DepartmentsService.class));
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Employee result = entityManager.find(Employee.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Employee result = null;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            result = entityManager.createQuery("select e from Employee e where e.name = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            logger.log(Level.INFO, "get EmployeeBy By Name: no employee with required name ");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateExistingEmployee(Employee newEmployee) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(newEmployee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void addEmployee(Employee employee) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
