package se.iths.cecilia.sqlApplication.Model;

import java.util.Date;

public class Person {
    private int person_id;
    private String first_name;
    private String last_name;
    private java.sql.Date dob;
    private double income;

    public Person(PersonBuilder personBuilder) {
        this.first_name = personBuilder.first_name;
        this.last_name = personBuilder.last_name;
        this.dob = personBuilder.dob;
        this.income = personBuilder.income;
    }

    public Person(PersonBuilder personBuilder, int person_id) {
        this.person_id = personBuilder.person_id;
        this.first_name = personBuilder.first_name;
        this.last_name = personBuilder.last_name;
        this.dob = personBuilder.dob;
        this.income = personBuilder.income;

    }

    public int getPerson_id() {
        return person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Date getDob() {
        return dob;
    }

    public double getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return person_id + ": " + first_name + " " + last_name + " - " + dob + " - " + income;
    }

    public static class PersonBuilder {
        public int person_id;
        private String first_name;
        private String last_name;
        private java.sql.Date dob;
        private double income;

        public PersonBuilder setPerson_id(int person_id) {
            this.person_id = person_id;
            return this;
        }

        public PersonBuilder setIncome(double income) {
            this.income = income;
            return this;
        }

        public PersonBuilder setDob(Date dob) {
            java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
            this.dob = sqlDate;
            return this;
        }

        public PersonBuilder setLast_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public PersonBuilder setFirst_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

}
