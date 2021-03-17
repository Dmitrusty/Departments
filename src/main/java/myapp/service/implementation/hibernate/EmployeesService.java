package myapp.service.implementation.hibernate;

import myapp.model.Employee;
import myapp.service.InterfaceEmployeesService;
import myapp.utils.HibernateSetup;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesService implements InterfaceEmployeesService {
    private final Logger logger;

    public EmployeesService() {
        logger = Logger.getLogger(String.valueOf(DepartmentsService.class));
    }

    @Override
    // todo проверить
    public Employee getEmployeeById(Long id) {
        Employee result = null;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            // todo
            result = entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            result = entityManager.find(Employee.class, id);
        } catch (Exception e) {
            // todo как применять сообщения от Exception?
            e.getMessage();
            // todo Level.INFO - какой уровень когда ставить?
            logger.log(Level.INFO, "get EmployeeBy By Id: no employee with required id ");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    // todo проверить
    public Employee getEmployeeByName(String name) {
        Employee result = null;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            result = entityManager.createQuery("select e from Employee e where e.name = :name", Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            // todo как применять сообщения от Exception?
            e.getMessage();
            // todo Level.INFO - какой уровень когда ставить?
            logger.log(Level.INFO, "get EmployeeBy By Name: no employee with required name ");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    // todo проверить
    // todo void / boolean
    // void deleteEmployee(Employee employee) {}
    public String deleteEmployeeById(int id) {
        String name = null;
        String bufferName = null;
        int deletedEmployees;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            bufferName = entityManager.createQuery("select e.name from Employee e where e.id = :id", String.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            // todo как применять сообщения от Exception?
            e.getMessage();
            // todo Level.INFO - какой уровень когда ставить?
            logger.log(Level.INFO, "delete EmployeeBy By Id: no employee with required id ");
        }

        if (bufferName != null) {
            try {
                entityManager.remove();
                deletedEmployees = entityManager.createQuery("delete from Employee e where e.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                if (deletedEmployees == 1) {
                    name = bufferName;
                } else {
                    logger.log(Level.SEVERE, "delete Employee By Id: unexpected result of deleting employee " + bufferName + " with required id ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "delete Employee By Id: error deleting employee " + bufferName + " with required id ");
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return name;
    }

    @Override
    // todo проверить
    public List<Employee> getEmployeesByDepartmentId(Long departmentID) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> result = entityManager.createQuery("select e from Employee e where e.departmentID = :departmentID", Employee.class)
                .setParameter("departmentID", departmentID)
                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    // todo проверить
    public boolean updateExistingEmployee(Employee newEmployee) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(newEmployee);

        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    // todo проверить
    public boolean addEmployee(Employee employee) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
}
// todo ID camel case проверить
