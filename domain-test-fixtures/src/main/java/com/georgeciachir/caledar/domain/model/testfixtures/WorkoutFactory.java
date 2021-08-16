package com.georgeciachir.caledar.domain.model.testfixtures;

import com.georgeciachir.calendar.entry.domain.model.TimeTable;
import com.georgeciachir.calendar.entry.domain.model.Workout;

import java.time.LocalDate;

import static com.georgeciachir.calendar.entry.domain.model.WeekDay.MONDAY;

public class WorkoutFactory {

    public static Workout workoutAtDayAndInterval(LocalDate date, TimeTable interval) {
        return Workout.builder()
                .date(date)
                .description("some description")
                .location("location")
                .timeTable(interval)
                .weekday(MONDAY)
                .build();
    }
}
