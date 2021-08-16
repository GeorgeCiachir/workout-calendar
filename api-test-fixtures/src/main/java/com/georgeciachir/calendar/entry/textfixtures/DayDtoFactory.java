package com.georgeciachir.calendar.entry.textfixtures;

import com.georgeciachir.calendar.entry.api.dto.DayDto;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDto;
import com.georgeciachir.calendar.entry.domain.model.Day;
import com.georgeciachir.calendar.entry.domain.model.Workout;

import java.time.LocalDate;
import java.util.Set;

public class DayDtoFactory {

    public static DayDto day(LocalDate date, Set<WorkoutDto> workouts) {
        return DayDto.builder()
                .date(date)
                .workouts(workouts)
                .build();
    }
}
