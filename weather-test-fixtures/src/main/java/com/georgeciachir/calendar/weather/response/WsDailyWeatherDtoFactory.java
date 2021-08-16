package com.georgeciachir.calendar.weather.response;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class WsDailyWeatherDtoFactory {

    public static WsDailyWeatherDto getWsDailyWeatherDto(LocalDate start, int min, int max) {
        return WsDailyWeatherDto.builder()
                .temperature(WsTemperatureDto.builder()
                        .min(min)
                        .max(max)
                        .build())
                .epochSeconds(start.atStartOfDay().toEpochSecond(ZoneOffset.UTC))
                .build();
    }
}
