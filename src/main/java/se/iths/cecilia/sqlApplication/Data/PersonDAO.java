package se.iths.cecilia.sqlApplication.Data;

import se.iths.cecilia.sqlApplication.Model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> findAll();

    Person findById(int id);

    void insertPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(int id);

}
