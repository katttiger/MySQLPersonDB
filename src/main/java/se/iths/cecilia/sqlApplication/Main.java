package se.iths.cecilia.sqlApplication;

import se.iths.cecilia.sqlApplication.Controller.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        while (answer != 6) {
            System.out.println("""
                    Enter option:
                    [1] Create one person
                    [2] Read all persons in database
                    [3] Read one person from database by id
                    [4] Update one person
                    [5] Delete one person
                    [6] Close application
                    """);
            answer = sc.hasNextInt() ? sc.nextInt() : -1;

            switch (answer) {
                case 1 -> controller.addPerson();
                case 2 -> controller.getAllPersons();
                case 3 -> controller.getPersonById();
                case 4 -> controller.updatePerson();
                case 5 -> controller.deletePerson();
                case 6 -> System.out.println("Good Bye!");
                default -> System.out.println("Invalid option");
            }
        }
    }
}
