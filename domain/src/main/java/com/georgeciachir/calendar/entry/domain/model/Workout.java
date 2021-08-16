package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Value
public class Workout {

    String id;
    String location;
    String description;
    LocalDate date;
    TimeTable timeTable;
    DailyWeather weather;
    WeekDay weekday;

    public Workout withWeather(IntervalForecast forecast) {
        DailyWeather dailyWeather = forecast.getIntervalWeather().stream()
                .filter(this::matches)
                .findFirst()
                .orElse(null);

        return this.toBuilder()
                .weather(dailyWeather)
                .build();
    }

    private boolean matches(DailyWeather weather) {
        return this.date.equals(weather.getDate());
    }
}
