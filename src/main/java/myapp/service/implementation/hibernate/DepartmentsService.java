package myapp.service.implementation.hibernate;

import myapp.model.Department;
import myapp.service.InterfaceDepartmentsService;
import myapp.utils.DatabaseConnection;
import myapp.utils.HibernateSetup;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Department result = entityManager.createQuery("select p" +
                "from departments p " +
                "where name = :name", Department.class)
                .setParameter("name", name)
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public String deleteDepartmentById(int id) {
        String name = null;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Department department = entityManager.find(Department.class, id);
        if (department != null) {
            entityManager.remove(department);
            name = department.getName();
            entityManager.getTransaction().commit();
        } else {
            logger.log(Level.INFO, "Deleting department by id: no department with required id.");
        }

        entityManager.close();
        return name;
    }

    @Override
    public List<Department> getAllDepartments() {
        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Department> result = entityManager.createQuery("from Department", Department.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    @Override
    public boolean updateExistingDepartment(Department newDepartment) {
        boolean result = false;

        EntityManager entityManager = HibernateSetup.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Department dbDepartment = entityManager.find(Department.class, newDepartment.getId());
        if (dbDepartment != null){
            entityManager.merge(newDepartment);
            result = true;
            entityManager.getTransaction().commit();
        } else {
            logger.log(Level.INFO, "Updating department by id: no department with required id.");
        }

        entityManager.close();
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
        // todo return false
    }
}
