package com.georgeciachir.calendar.entry.domain;

import com.georgeciachir.calendar.entry.domain.model.IntervalForecast;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherAdapter {

    Optional<IntervalForecast> getForecast(String city, LocalDate date);
}
