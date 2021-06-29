package com.inveox;

import java.util.Objects;

// FIXME
public class person {
    String namE;
    public String surname;
    static Integer Year;

    public person(String namE, String surname, Integer year) {
        this.namE = namE;
        this.surname = surname;
        Year = year;
    }


    public String tostring() {
        return "person{" +
                "namE='" + namE + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        person person = (person) o;
        return Objects.equals(namE, person.namE) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public static void main(String[] args) {
        System.out.println(new person("john", "smith", 35));
    }
}
