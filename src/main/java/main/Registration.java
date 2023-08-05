package main;

import Entity.City;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class Registration {
    public void createUser(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner sc = new Scanner(System.in);

        TypedQuery<City> query = manager.createQuery(
                "select c from City c", City.class
        );

        List<City> cityList = query.getResultList();

        System.out.println("--- Список городов ---");
        for (City city : cityList) {
            System.out.println(city.getName()+" ["+city.getId()+"]");
        }

        /*
        System.out.print("Ввести другой город? (да/нет): ");
        String answer = sc.nextLine();
        if(answer.equals("да")){
            createCity();
            return;
        }
        */

        System.out.print("Введите id Вашего города: ");
        String cityId = sc.nextLine();


        System.out.print("Введите имя: ");
        String firstName = sc.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = sc.nextLine();
        System.out.print("Введите логин: ");
        String login = sc.nextLine();
        System.out.print("Введите пароль: ");
        String password = sc.nextLine();

        try {
            manager.getTransaction().begin();

            City city = manager.find(City.class, cityId);
            User user = new User();

            user.setCity(city);
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setLogin(login);
            user.setPassword(password);
            manager.persist(user);

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /*
    public void createCity(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите ваш город: ");
        String nameCity = sc.nextLine();
        System.out.print("Введите индекс города: ");
        String indexCity = sc.nextLine();

        try{
            manager.getTransaction().begin();
            City city = new City();
            city.setName(nameCity);
            city.setIndex(indexCity);
            manager.persist(city);
            System.out.println("город внесен в список");
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    */


}
