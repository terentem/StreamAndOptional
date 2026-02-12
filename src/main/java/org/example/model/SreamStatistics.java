package org.example.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record SreamStatistics(
        int listSize,
        Set<String> distinctNames,
        Set<String> distinctTypes,
        Set<String> distinctColors,
        int minAge,
        int maxAge,
        LocalDate minBirthDate,
        LocalDate maxBirthDay,
        long countNullBirthday) {

    public static SreamStatistics ofData(List<Inhabitant> data) {
        return new SreamStatistics(
                data.size(),
                data.stream()
                        .map(Inhabitant::getName)
                        .collect(Collectors.toSet()),
                data.stream()
                        .map(Inhabitant::getType)
                        .collect(Collectors.toSet()),
                data.stream()
                        .map(Inhabitant::getColor)
                        .collect(Collectors.toSet()),
                data.stream()
                        .map(Inhabitant::getAgeinMonths)
                        .flatMap(Optional::stream)
                        .mapToInt(age -> age)
                        .min()
                        .orElse(0),
                data.stream()
                        .map(Inhabitant::getAgeinMonths)
                        .flatMap(Optional::stream)
                        .mapToInt(age -> age)
                        .max()
                        .orElse(0),
                data.stream()
                        .map(Inhabitant::getBirthDay)
                        .flatMap(Optional::stream)
                        .min(LocalDate::compareTo)
                        .orElse(null),
                data.stream()
                        .map(Inhabitant::getBirthDay)
                        .flatMap(Optional::stream)
                        .max(LocalDate::compareTo)
                        .orElse(null),
                data.stream()
                        .filter(i -> i.getBirthDay().isEmpty())
                        .count()
        );
    }
}
