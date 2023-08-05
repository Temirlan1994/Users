package main;

import Entity.City;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
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

            System.out.println("Изменить данные [1]");
            System.out.println("Просмотреть информацию о пользователе [2]");
            System.out.println("Удалить свой аккаунт [3]");
            System.out.print("Выберите действие!");
            String action = sc.nextLine();

            if(action.equals("1")){
                System.out.print("Введите новое имя: ");
                String firstName = sc.nextLine();
                System.out.print("Введите новую фамилию: ");
                String lastName = sc.nextLine();

                TypedQuery<City> cityQuery = manager.createQuery(
                        "select c from City c", City.class
                );
                List<City> cities = cityQuery.getResultList();
                for (City city : cities) {
                    System.out.println(city.getName()+" ["+city.getId()+"]");
                }
                System.out.print("Введите id другого город из списка: ");
                String cityId = sc.nextLine();
                City city = manager.find(City.class, cityId);

                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                user.setCity(city);
            }else if(action.equals("2")){
                System.out.println("Id: "+user.getId());
                System.out.println("Имя: "+user.getFirst_name());
                System.out.println("Фамилия: "+user.getLast_name());
                System.out.println("Город: "+user.getCity().getName());
                System.out.println("Индекс города: "+user.getCity().getIndex());
            } else if (action.equals("3")) {
                manager.remove(user);
                System.out.println("Ваш аккаунт удален");
            }

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
