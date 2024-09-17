package Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.GenericJDBCException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class HibernateMain {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("Hibernate.xml")
                .addAnnotatedClass(UsersEntity.class)
                .buildSessionFactory();


        System.out.println("Pershendetje ky eshte nje program i thjeshte: ");

        System.out.println("Pershendetje jeni te lutur te zgjidhni nje nga opsionet e cituara: \n" +
                " 1. Log In (nese jeni perdorues i ri)---2. Sing In(Nese jeni i rregjistruar)");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

      if(choice == 1) {

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


          user.setUsername(name);
          user.setUserLastname(surname);
          user.setDateOfBirth(selectedDate);
          user.setGender(gender);
          user.setEmail(email);
          user.setPassword(password);

          try {

           userService.addEntity(user , sessionFactory);




          } catch (GenericJDBCException e) {
              System.out.println(e.getCause());
          }

      }







        }
       }

