package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class UsersService implements  UsersImplementation{
    @Override
    public void addEntity(UsersEntity usersEntity, SessionFactory sessionFactory) {
     Session session = sessionFactory.getCurrentSession();
     Transaction tx = session.beginTransaction();

        session.save(usersEntity);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteEntity(Integer id, SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        UsersEntity usersEntity = session.createQuery("select u from UsersEntity u where u.userId = :id", UsersEntity.class)
                .setParameter("id", id)
                        .getSingleResult();

        session.delete(usersEntity);
        tx.commit();
        session.close();
    }
}
