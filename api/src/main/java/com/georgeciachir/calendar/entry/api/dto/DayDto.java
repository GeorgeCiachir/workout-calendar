package com.georgeciachir.calendar.entry.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Value
public class DayDto {

    LocalDate date;
    Set<WorkoutDto> workouts;
}
