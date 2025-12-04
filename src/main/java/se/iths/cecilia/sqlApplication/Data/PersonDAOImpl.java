package se.iths.cecilia.sqlApplication.Data;

import se.iths.cecilia.sqlApplication.Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {

    DbContext dbContext;
    private String sqlQuery = "";

    public PersonDAOImpl() {
        this.dbContext = DbContext.getInstance();
    }

    @Override
    public void insertPerson(Person person) {
        String sqlQuery = "INSERT INTO person (first_name, last_name, dob, income) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            pstmt.setString(1, person.getFirst_name());
            pstmt.setString(2, person.getLast_name());
            pstmt.setDate(3, new java.sql.Date(person.getDob().getTime()));
            pstmt.setDouble(4, person.getIncome());

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Antal infogade rader" + rowsInserted);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePerson(Person person) {
        sqlQuery = "UPDATE person WHERE (first_name, last_name, dob, income) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setString(1, person.getFirst_name());
                pstmt.setString(2, person.getLast_name());
                pstmt.setDate(3, new java.sql.Date(rs.getDate("dob").getTime()));
                pstmt.setDouble(4, rs.getDouble("income"));

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Antal rader som har uppdaterats: " + rowsUpdated);
                } else {
                    System.out.println("Uppdateringen misslyckades.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void deletePerson(int id) {
        sqlQuery =
                "DELETE FROM person WHERE person_id=?";
        try (Connection conn = dbContext.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);

            try (ResultSet rs = pstmt.executeQuery()) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Antal rader som har tagits bort: " + rowsDeleted);
                } else {
                    System.out.println("Inga rader matchade.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAll() {
        sqlQuery = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Person.PersonBuilder personBuilder = new Person.PersonBuilder();
                    personBuilder.setFirst_name(rs.getString("first_name"));
                    personBuilder.setLast_name(rs.getString("last_name"));
                    personBuilder.setDob(rs.getDate("dob"));
                    personBuilder.setIncome(rs.getDouble("income"));

                    Person person = new Person(personBuilder);
                    persons.add(person);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }

    @Override
    public Person findById(int id) {
        return null;
    }
}
