package com.georgeciachir.calendar.entry.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Value
public class WorkoutDto {

    String id;
    String location;
    String description;
    LocalDate date;
    TimeTableDto timeTable;
    DailyWeatherDto weather;
    WeekDayDto weekday;
}
