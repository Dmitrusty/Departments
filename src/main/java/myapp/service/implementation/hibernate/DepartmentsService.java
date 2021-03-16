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
    public String deleteDepartmentById(int id) {
        String name = null;
        String bufferName = null;
        int deletedDepartments;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try {
            bufferName = (String) entityManager.createQuery("select d.name from Department d where d.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "delete Department By Id: no department with required id ");
        }

        if (bufferName != null) {
            try {
                deletedDepartments = entityManager.createQuery(
                        "delete from Department d where d.id = :id")
                        .setParameter("id", id)
                        .executeUpdate();

                if (deletedDepartments == 1) {
                    name = bufferName;
                } else {
                    logger.log(Level.SEVERE, "delete Department By Id: unexpected result of deleting department " + bufferName + " with required id ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "delete Department By Id: error deleting department " + bufferName + " with required id ");
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return name;
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
    public boolean updateExistingDepartment(Department newDepartment) {
        boolean result = false;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        int updatedDepartments = entityManager.createQuery(
                "update Department d set d.name = :newName where d.id = :id")
                .setParameter("id", newDepartment.getId())
                .setParameter("newName", newDepartment.getName())
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

        if (updatedDepartments == 1) {
            result = true;
        } else {
            logger.log(Level.WARNING, "Updating department by id: " + updatedDepartments +
                    " departments with required id was updated.");
        }

        return result;
    }

    @Override
    public boolean addDepartment(Department department) {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(department);

        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }
}
