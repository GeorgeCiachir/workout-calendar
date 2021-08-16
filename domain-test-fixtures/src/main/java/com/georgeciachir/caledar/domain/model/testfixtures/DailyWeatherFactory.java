package com.georgeciachir.caledar.domain.model.testfixtures;

import com.georgeciachir.calendar.entry.domain.model.DailyWeather;
import com.georgeciachir.calendar.entry.domain.model.Temperature;

import java.time.LocalDate;

public class DailyWeatherFactory {

    public static DailyWeather weatherToday() {
        return DailyWeather.builder()
                .date(LocalDate.now())
                .temperature(
                        Temperature.builder()
                                .min(12)
                                .max(33)
                                .build())
                .build();
    }
}
