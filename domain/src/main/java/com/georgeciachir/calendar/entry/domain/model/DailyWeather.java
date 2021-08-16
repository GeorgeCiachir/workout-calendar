package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class DailyWeather {

    LocalDate date;
    Temperature temperature;
}
