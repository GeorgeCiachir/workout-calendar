package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class IntervalForecast {

    List<DailyWeather> intervalWeather;
}
