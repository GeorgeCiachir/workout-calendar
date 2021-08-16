package com.georgeciachir.calendar.weather.response;

import com.georgeciachir.calendar.entry.domain.model.DailyWeather;
import com.georgeciachir.calendar.entry.domain.model.IntervalForecast;
import com.georgeciachir.calendar.entry.domain.model.Temperature;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface WeatherDtoMapper {

    IntervalForecast toDomain(WsIntervalForecastDto dto);

    default DailyWeather toDomain(WsDailyWeatherDto dto) {
        Instant instant = Instant.ofEpochSecond(dto.getEpochSeconds());
        LocalDate date = LocalDate.ofInstant(instant, ZoneOffset.UTC);
        return DailyWeather.builder()
                .date(date)
                .temperature(
                        Temperature.builder()
                                .max(dto.getTemperature().getMax())
                                .min(dto.getTemperature().getMin())
                                .build())
                .build();
    }
}

