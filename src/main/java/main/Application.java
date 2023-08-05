package main;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Авторизация [1]");
        System.out.println("Регистрация [2]");
        System.out.print("Выберите действие: ");
        String action = sc.nextLine();

        if (action.equals("1")) {
            Authorization authorization = new Authorization();
            authorization.launch();
        }else if (action.equals("2")) {
            Registration registration = new Registration();
            registration.createUser();
        }else{
            System.out.println("Неправильные дынные, повторите попытку!");
        }






    }
}
