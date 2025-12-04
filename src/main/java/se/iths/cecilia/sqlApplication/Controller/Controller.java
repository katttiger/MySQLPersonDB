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
}
