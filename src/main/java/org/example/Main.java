package org.example;

import org.example.model.Inhabitant;
import org.example.model.SreamStatistics;
import org.example.utils.Streamator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Alf", "Bro", "Cinamon", "Drue", "Elon", "Faith", "Gina", "Hero", "Init", "Joe");
        List<Inhabitant> inhabitansList = IntStream.range(0, 100)
                .mapToObj(index -> new Inhabitant(
                        (index % 2 == 0) ? "cat" : "dog",
                        names.get(index % 10),
                        (index % 3 > 0) ? "white" : "brown",
                        (index % 10 < 5) ? LocalDate.of(2025, ((index % 10) + 1), 1) : null,
                        (index % 10 > 5) ? String.valueOf(ThreadLocalRandom.current().nextLong(100000, 1000000)) : null,
                        (index % 2 == 0) ? "male" : "female"))
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

}

