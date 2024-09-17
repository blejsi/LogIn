package Database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsersService implements  UsersImplementation{
    @Override
    public void addEntity(UsersEntity usersEntity, SessionFactory sessionFactory) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
         transaction.begin();
        entityManager.persist(usersEntity);
        transaction.commit();
        entityManager.close();
    }
}
