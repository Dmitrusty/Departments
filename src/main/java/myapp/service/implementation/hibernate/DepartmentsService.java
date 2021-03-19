package myapp.service.implementation.hibernate;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.utils.HibernateSetup;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentsService implements InterfaceDepartmentsService {
    private final Logger logger;

    public DepartmentsService() {
        logger = Logger.getLogger(String.valueOf(DepartmentsService.class));
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department result = null;
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            result = entityManager.createQuery("select d " +
                    " from Department d " +
                    " where d.name = :name ", Department.class)
                    .setParameter("name", name)
                    .getSingleResult();

        } catch (NoResultException e) {
            logger.log(Level.WARNING, "get Department By Name: no department with required name " + name);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public void deleteDepartmentById(Long id) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Department> getAllDepartments() {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Department> result = entityManager.createQuery("select d from Department d", Department.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public void updateExistingDepartment(Department newDepartment) {

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(newDepartment);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void addDepartment(Department department) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(department);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
