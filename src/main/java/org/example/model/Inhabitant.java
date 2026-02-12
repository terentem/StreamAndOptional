package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


@Getter
@ToString
@Builder
public class Inhabitant {
    @NotBlank(message = "type must not be empty")
    private final String type;

    @NotBlank(message = "name must not be empty")
    private final String name;

    @NotBlank(message = "name must not be empty")
    private final String color;

    @Past(message = "date must be greater than now")
    private final LocalDate birthDate;

    @NotBlank(message = "name must not be empty")
    private final String gender;

    @Pattern(regexp = "^(\\d{16})?$",
            message = "chipCode must contain at least 16 digits")
    private final String chipCode;


    public Optional<LocalDate> getBirthDay() {
        return Optional.ofNullable(birthDate);
    }

    public Optional<Integer> getAgeInMonths() {
        return getBirthDay()
                .map(birth -> (int) Period.between(birth, LocalDate.now()).getMonths());
    }

}
