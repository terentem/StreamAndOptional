package org.example.utils;

import org.example.model.Inhabitant;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streamator {

    public static List<Inhabitant> streamedData(List<Inhabitant> data, String type) {
        return data.stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    public static List<Inhabitant> streamedData(List<Inhabitant> data, String type, String color) {
        return data.stream()
                .filter(i -> i.getType().equals(type))
                .filter((i -> i.getColor().equals(color)))
                .collect(Collectors.toList());
    }

    public static List<Inhabitant> streamedData(List<Inhabitant> data, String type, String color, Predicate<Integer> ageCondition) {
        return data.stream()
                .filter(i->type==null||type.equals(i.getType()))
                .filter(i->color==null||color.equals(i.getColor()))
                .filter(i->i.getAgeinMonths()
                .map(ageCondition::test)
                        .orElse(false))
                .collect(Collectors.toList());
    }

    public static List<Inhabitant> getInhabitantsWIthEmptyBirthday(List<Inhabitant> data, String type, String color){
        return data.stream()
                .filter(emptyBirthday->emptyBirthday.getBirthDay().isEmpty())
                .filter(i->type==null||i.getType().equals(type))
                .filter(i->color==null||i.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
