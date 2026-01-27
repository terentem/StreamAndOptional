package org.example.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

public class Inhabitant {
    private String type;
    private String name;
    private String color;
    private LocalDate birthDate;
    private String gender;
    private String chipCode;

    public Inhabitant(String type, String name, String color, LocalDate birthDate, String chipCode, String gender) {
        this.type = Objects.requireNonNull(type, "type must not be empty");
        this.name = Objects.requireNonNull(name, "name must not be empty");
        this.birthDate = birthDate;
        this.chipCode = chipCode;
        this.color = Objects.requireNonNull(color, "name must not be empty");
        this.gender = Objects.requireNonNull(gender, "name must not be empty");
    }

    public Optional<LocalDate> getBirthDay() {
        return Optional.ofNullable(birthDate);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getChipCode() {
        return chipCode;
    }

    public Optional<Integer> getAgeinMonths() {
        return getBirthDay()
                .map(birth -> (int) Period.between(birth, LocalDate.now()).getMonths());
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Inhabitant{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", age=" + getAgeinMonths() +
                '}';
    }
}
