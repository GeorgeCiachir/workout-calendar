package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Value
public class Day {

    LocalDate date;
    Set<Workout> workouts;

    public static Day having(LocalDate date, Set<Workout> workouts) {
        return Day.builder()
                .date(date)
                .workouts(workouts)
                .build();
    }


}
