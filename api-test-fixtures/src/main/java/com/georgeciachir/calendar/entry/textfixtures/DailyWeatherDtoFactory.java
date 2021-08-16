package com.georgeciachir.calendar.entry.textfixtures;

import com.georgeciachir.calendar.entry.api.dto.DailyWeatherDto;
import com.georgeciachir.calendar.entry.api.dto.TemperatureDto;

public class DailyWeatherDtoFactory {

    public static DailyWeatherDto dailyWeatherDto(int min, int max) {
        TemperatureDto temperatureDto = TemperatureDto.builder()
                .min(min)
                .max(max)
                .build();

        return DailyWeatherDto.builder()
                .temperature(temperatureDto)
                .build();
    }
}
