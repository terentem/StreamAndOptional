package org.example.utils;

import org.example.model.Inhabitant;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InhabitantsListCreator {

    public static Inhabitant buildInhabitant(int index, List<String> names) {
        return Inhabitant.builder()
                .type(index % 2 == 0 ? "cat" : "dog")
                .name(names.get(index % names.size()))
                .color(index % 3 > 0 ? "white" : "brown")
                .gender(index % 2 == 0 ? "male" : "female")
                .birthDate(index % 10 < 5
                        ? LocalDate.of(2024, (index % 10) + 1, 1)
                        : null)
                .chipCode(index % 10 > 5
                        ? String.format("%016d",
                        ThreadLocalRandom.current().nextLong(0, 1_000_000_000_000_000L))
                        : null)
                .build();
    }
}
