package com.georgeciachir.calendar.entry.textfixtures;

import com.georgeciachir.calendar.entry.api.dto.DailyWeatherDto;
import com.georgeciachir.calendar.entry.api.dto.TimeTableDto;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDto;

import java.time.LocalDate;

import static com.georgeciachir.calendar.entry.api.dto.WeekDayDto.MONDAY;

public class WorkoutDtoFactory {

    public static WorkoutDto workoutAtDayAndInterval(LocalDate date, TimeTableDto interval) {
        return WorkoutDto.builder()
                .date(date)
                .description("some description")
                .location("location")
                .timeTable(interval)
                .weekday(MONDAY)
                .build();
    }

    public static WorkoutDto workoutWithWeather(LocalDate date, TimeTableDto interval, DailyWeatherDto weather) {
        return workoutAtDayAndInterval(date, interval)
                .toBuilder()
                .weather(weather)
                .build();
    }
}
