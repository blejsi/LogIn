package Database;

import org.hibernate.SessionFactory;

public interface UsersImplementation {

    public void addEntity(UsersEntity usersEntity, SessionFactory sessionFactory);


    void deleteEntity(Integer id, SessionFactory sessionFactory);
}
