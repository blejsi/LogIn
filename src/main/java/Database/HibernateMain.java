package Database;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.GenericJDBCException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HibernateMain {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("Hibernate.xml")
                .addAnnotatedClass(UsersEntity.class)
                .buildSessionFactory();


        UsersService usersService = new UsersService();
        LogInSingInService logInSingInService = new LogInSingInService();


        System.out.println("Pershendetje ky eshte nje program i thjeshte: ");

        System.out.println("Pershendetje jeni te lutur te zgjidhni nje nga opsionet e cituara: \n" +
                " 1. Log In (nese jeni perdorues i ri)---2. Sing In(Nese jeni i rregjistruar)");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
       try {
           choice = scanner.nextInt();
       }catch (InputMismatchException e){
           System.out.println("Input Mismatch Exception!");
       }



      if(choice == 1) {
              LocalDate selectedDate;
              UsersEntity user = new UsersEntity();
              UsersService userService = new UsersService();
              System.out.println("Mirsevini ne websitin tone: ");
              System.out.println("Ju lutem vendosni emrin tuaj: ");
              String name = scanner.next();
              user.setUsername(name);
              System.out.println("Ju lutem vendosni mbiemrin tuaj: ");
              String surname = scanner.next();
              user.setUserLastname(surname);
              System.out.println("Ju lutem vendosni daten tuaj te lindjes ne formatin (YYYY-MM-DD): ");
              try{String date = scanner.next();
              selectedDate = LocalDate.parse(date);
              user.setDateOfBirth(selectedDate);}
              catch (DateTimeParseException e){
                  System.out.println("Date Input Exception!");
              }
              System.out.println("Ju lutem vendosni gjinin tuaj (M/f): ");
              String gender = scanner.next();
              user.setGender(gender);

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
                      System.out.println("Email and Password already in use!");
                     logInSingInService.emailExists(scanner);
                     user.setEmail(logInSingInService.emailExists(scanner));
                      logInSingInService.passwordExists(scanner);
                      user.setPassword(logInSingInService.passwordExists(scanner));

                      userService.addEntity(user, sessionFactory);
                      break;

                  } else if (usersEntity.getEmail().equals(email))  {
                      System.out.println("Email already in use!");
                      logInSingInService.emailExists(scanner) ;
                    user.setEmail(logInSingInService.emailExists(scanner));
                    userService.addEntity(user, sessionFactory);
                    break;
                  }else if (usersEntity.getPassword().equals(password))  {
                      System.out.println("Password already in use!");
                      logInSingInService.passwordExists(scanner);
                      user.setPassword(logInSingInService.passwordExists(scanner));
                      userService.addEntity(user, sessionFactory);
                  }else{
                      userService.addEntity(user, sessionFactory);
                  }
              }
          }



      else if (choice==2){

          UsersEntity user = new UsersEntity();

          System.out.println("Mirsevini ne websitin tone: ");
          System.out.println("Ju lutem vendosni emailin tuaj: ");
          String email = scanner.next();
          System.out.println("Ju lutem vendosni passwordin tuaj: ");
          String password = scanner.next();




          Session session = sessionFactory.getCurrentSession();
          Transaction tx = session.beginTransaction();

          List<UsersEntity> usersEntityList = session.createQuery(" FROM  UsersEntity u", UsersEntity.class)
                  .getResultList();

          tx.commit();
          session.close();


          for (UsersEntity usersEntity : usersEntityList) {
              if (usersEntity.getEmail().equals(email) && usersEntity.getPassword().equals(password)) {
                  System.out.println("Miresevini!");
                  break;
              }else{

                  if(logInSingInService.signIn(scanner, sessionFactory, usersService)){
                      System.out.println("Mirsevini!");
                      break;
                  } else if (!logInSingInService.signIn(scanner, sessionFactory, usersService))  {
                      int i=0;
                      while(i<1){
                          logInSingInService.signIn(scanner, sessionFactory, usersService);
                          i++;
                    }

                  };
                      System.out.println("Ju nuk jeni i rregjistruar");
                      break;

                  }


              }
          }




      }






        }


