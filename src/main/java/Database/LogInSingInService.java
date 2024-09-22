package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class LogInSingInService {




    public String emailExists(Scanner scanner){



                System.out.println("Vendosni emailin: ");
                String repeatEmail = scanner.next();

                return repeatEmail;
    }
    public String passwordExists(Scanner scanner){



                System.out.println("Vendosni passwordin: ");
                String repeatPassword = scanner.next();

                return repeatPassword;
    }

    public boolean signIn(Scanner scanner, SessionFactory sessionFactory ,UsersService usersService){

           boolean singIn = false;
            UsersEntity user = new UsersEntity();
            System.out.println("*********Emaili ose passwordi i pasakt ju lutem provojeni perseri!**********");
            System.out.println("Ju lutem vendosni emailin tuaj: ");
            String email = scanner.next();
            System.out.println("Ju lutem vendosni passwordin tuaj: ");
            String password = scanner.next();




            Session session = sessionFactory.getCurrentSession();
            Transaction tx = session.beginTransaction();

            List<UsersEntity> usersEntityList = session.createQuery(" FROM  UsersEntity u", UsersEntity.class)
                    .getResultList();

            for (UsersEntity usersEntity : usersEntityList) {
                if (usersEntity.getEmail().equals(email) && usersEntity.getPassword().equals(password)) {
                    singIn = true;
                    break;

                }else{
                     singIn = false;


                }
            }

            tx.commit();
            session.close();


        return singIn;
    }


}
