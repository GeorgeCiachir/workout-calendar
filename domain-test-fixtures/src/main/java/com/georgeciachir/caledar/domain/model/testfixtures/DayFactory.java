package com.georgeciachir.caledar.domain.model.testfixtures;

import com.georgeciachir.calendar.entry.domain.model.Day;
import com.georgeciachir.calendar.entry.domain.model.Workout;

import java.time.LocalDate;
import java.util.Set;

public class DayFactory {

    public static Day day(LocalDate date, Set<Workout> workouts) {
        return Day.builder()
                .date(date)
                .workouts(workouts)
                .build();
    }
}
