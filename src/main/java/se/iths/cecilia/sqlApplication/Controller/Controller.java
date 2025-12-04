package se.iths.cecilia.sqlApplication.Controller;

import se.iths.cecilia.sqlApplication.Data.PersonDAO;
import se.iths.cecilia.sqlApplication.Data.PersonDAOImpl;
import se.iths.cecilia.sqlApplication.Model.Person;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Controller {
    PersonDAO personDAO;


    public Controller() {
        this.personDAO = new PersonDAOImpl();
    }

    public void getAllPersons() {
        List<Person> people = personDAO.findAll();
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }

    public void getPersonById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        int id = sc.hasNextInt() ? sc.nextInt() : -1;
        Person person = personDAO.findById(id);
        if (person == null) {
            System.out.println("No person was found with id " + id);
        } else {
            System.out.println(person);
        }
    }

    public void addPerson() {
        Person.PersonBuilder personBuilder = new Person.PersonBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name: ");
        personBuilder.setFirst_name(sc.nextLine());
        System.out.println("Enter last name: ");
        personBuilder.setLast_name(sc.nextLine());
        System.out.println("Enter date of birth (YYYY-MM-DD): ");
        String dob = sc.nextLine();
        personBuilder.setDob(Date.valueOf(dob));

        System.out.println("Enter income: ");
        personBuilder.setIncome(sc.hasNextDouble() ? sc.nextDouble() : 0);
        Person newPerson = personBuilder.build();

        personDAO.insertPerson(newPerson);
        System.out.println("Adding person...");
    }

    public void updatePerson() {
        System.out.println("Enter the ID of the person you want to update: ");
        Person person = personDAO.findById(new Scanner(System.in).nextInt());
        System.out.println("Your current values are: " + person);
        Scanner scanner = new Scanner(System.in);
        String answer = "0";

        while (!answer.equals("5")) {

            System.out.println("""
                    [1] First name
                    [2] Last name
                    [3] Date of birth
                    [4] Income
                    [5] Done
                    What value do you want to update:
                    """);
            answer = scanner.nextLine();

            switch (answer) {
                case "1" -> {
                    System.out.println("Enter the new value: ");
                    person.setFirst_name(scanner.nextLine());
                }
                case "2" -> {
                    System.out.println("Enter the new value: ");
                    person.setLast_name(scanner.nextLine());
                }
                case "3" -> {
                    System.out.println("Enter the new value: ");
                    person.setDob(Date.valueOf(scanner.next()));
                }
                case "4" -> {
                    System.out.println("Enter the new value: ");
                    person.setIncome(scanner.hasNextDouble() ? scanner.nextDouble() : 0);
                }
                case "5" -> {
                    personDAO.updatePerson(person);
                }
                default -> System.out.println("Enter a valid number.");
            }
        }
    }

    public void deletePerson() {
        System.out.println("Enter id of the person you want to delete: ");
        personDAO.deletePerson(new Scanner(System.in).nextInt());

    }

}
