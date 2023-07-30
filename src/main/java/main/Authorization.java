package main;

import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Scanner;

public class Authorization {
    public void launch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите логин: ");
        String login = sc.nextLine();
        System.out.println("Введите пароль: ");
        String password = sc.nextLine();

        try {
            manager.getTransaction().begin();
            TypedQuery<User> query = manager.createQuery(
                    "select u from User u where u.login = ?1 and u.password = ?2", User.class
            );
            query.setParameter(1,login);
            query.setParameter(2,password);
            User user = query.getSingleResult();

            System.out.println("Id: "+user.getId());
            System.out.println("Имя: "+user.getFirst_name());
            System.out.println("Фамилия: "+user.getLast_name());
            System.out.println("Город: "+user.getCity().getName());
            System.out.println("Индекс города: "+user.getCity().getIndex());

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
