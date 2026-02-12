package org.example;


import jakarta.validation.ConstraintViolation;
import org.example.model.Inhabitant;
import org.example.model.SreamStatistics;
import org.example.utils.InhabitantsListCreator;
import org.example.utils.Streamator;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.validation.MyValidator.VALIDATOR;


public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Alf", "Bro", "Cinamon", "Drue", "Elon", "Faith", "Gina", "Hero", "Init", "Joe");
        List<Inhabitant> inhabitansList = IntStream.range(0, 100)
                .mapToObj(index -> InhabitantsListCreator.buildInhabitant(index, names))
                .peek(inhabitant -> validate(inhabitant))
                .filter(inhabitant -> VALIDATOR.validate(inhabitant).isEmpty())
                .collect(Collectors.toList());

        //Білі кошенята до 6 місяців
        List<Inhabitant> whiteBabyCats = Streamator.streamedData(inhabitansList, "cat", "white", age -> age < 12);
        //whiteBabyCats.forEach(System.out::println);
        SreamStatistics whiteBabyCatsStat = SreamStatistics.ofData(whiteBabyCats);
        System.out.println("Statistics for whiteBabyCats:  " + whiteBabyCatsStat);
        //Білі кошенята без дати народження
        List<Inhabitant> catsWithemptyBirthday = Streamator.getInhabitantsWIthEmptyBirthday(inhabitansList, "cat", "white");
        SreamStatistics whiteCatsWithEmptyBirthdayStat = SreamStatistics.ofData(catsWithemptyBirthday);
        System.out.println("Statistics for white Cats with empty birthday:  " + whiteCatsWithEmptyBirthdayStat);

        //Імена коричневих собачок віком від 6 місяців
        Set<String> dogsNames = SreamStatistics.ofData(
                Streamator.streamedData(inhabitansList, "dog", "brown", age -> age > 6)).distinctNames();
        System.out.println("Імена коричневих собачок віком від 6 місяців:");
        Optional.of(dogsNames)
                .filter(name -> !name.isEmpty())
                .ifPresentOrElse(n -> n.forEach(System.out::println), // Если есть данные
                        () -> System.out.println("немає таких собачок"));
        List<Inhabitant> brownDogsWithEmptyBirthday = Streamator.getInhabitantsWIthEmptyBirthday(inhabitansList, "dog", "brown");

        System.out.println("В базі " + brownDogsWithEmptyBirthday.size() + " коричневих собачок без дати народження");
    }

    private static void validate(Inhabitant inhabitant) {
        Set<ConstraintViolation<Inhabitant>> violations = VALIDATOR.validate(inhabitant);
        if (!violations.isEmpty()) {
            System.err.println(" Validation errors for: " + inhabitant);
            violations.forEach(v ->
                    System.err.println("   → " + v.getPropertyPath() + ": " + v.getMessage())
            );
        }

    }
}

