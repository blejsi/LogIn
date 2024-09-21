package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogInSingInService {




    public void LogIN(Scanner scanner, SessionFactory sessionFactory ,UsersService usersService){
        LocalDate selectedDate;
        UsersEntity user = new UsersEntity();
        UsersService userService = new UsersService();
        System.out.println("Mirsevini ne websitin tone: ");
        System.out.println("Ju lutem vendosni emrin tuaj: ");
        String name = scanner.next();
        System.out.println("Ju lutem vendosni mbiemrin tuaj: ");
        String surname = scanner.next();
        System.out.println("Ju lutem vendosni daten tuaj te lindjes ne formatin (YYYY-MM-DD): ");
        String date = scanner.next();
        selectedDate = LocalDate.parse(date);
        System.out.println("Ju lutem vendosni gjinin tuaj (M/f): ");
        String gender = scanner.next();
        System.out.println("Ju lutem vendosni emailin tuaj: ");
        String email = scanner.next();
        System.out.println("Ju lutem vendosni passwordin tuaj: ");
        String password = scanner.next();

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<UsersEntity> usersEntityList = session.createQuery("from UsersEntity").getResultList();

        tx.commit();
        session.close();

        for (UsersEntity usersEntity : usersEntityList) {
            if (usersEntity.getEmail().equals(email) && usersEntity.getPassword().equals(password)) {
                System.out.println("Email and password Already exists!");
                System.out.println("Vendosni emailin: ");
                String repeatEmail = scanner.next();
                System.out.println("Vendosni passwordin: ");
                String repeatPassword = scanner.next();

                user.setUsername(name);
                user.setUserLastname(surname);
                user.setDateOfBirth(selectedDate);
                user.setGender(gender);
                user.setEmail(repeatEmail);
                user.setPassword(password);


                userService.addEntity(user, sessionFactory);
                break;

            } else if (usersEntity.getEmail().equals(email)) {
                System.out.println("Email allready exists!");
                String repeatEmail = scanner.next();

                user.setUsername(name);
                user.setUserLastname(surname);
                user.setDateOfBirth(selectedDate);
                user.setGender(gender);
                user.setEmail(repeatEmail);
                user.setPassword(password);


                userService.addEntity(user, sessionFactory);
                break;
            } else if (usersEntity.getPassword().equals(password)) {
                System.out.println("Password allready exists!");

                String repeatPassword = scanner.next();

                user.setUsername(name);
                user.setUserLastname(surname);
                user.setDateOfBirth(selectedDate);
                user.setGender(gender);
                user.setEmail(email);
                user.setPassword(repeatPassword);


                userService.addEntity(user, sessionFactory);
                break;
            } else {
                {
                    user.setUsername(name);
                    user.setUserLastname(surname);
                    user.setDateOfBirth(selectedDate);
                    user.setGender(gender);
                    user.setEmail(email);
                    user.setPassword(password);


                    userService.addEntity(user, sessionFactory);
                    break;
                }

            }
        }
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
