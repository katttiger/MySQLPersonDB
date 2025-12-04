package se.iths.cecilia.sqlApplication;

import se.iths.cecilia.sqlApplication.Controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.getAllPersons();
    }
}
