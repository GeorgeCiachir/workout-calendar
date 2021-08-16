package com.georgeciachir.calendar.weather;

import com.georgeciachir.calendar.entry.domain.WeatherAdapter;
import com.georgeciachir.calendar.entry.domain.model.IntervalForecast;
import com.georgeciachir.calendar.weather.response.WeatherDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService implements WeatherAdapter {

    private final WeatherFacade facade;
    private final WeatherDtoMapper mapper;

    @Override
    @Cacheable("weatherForecast")
    public Optional<IntervalForecast> getForecast(String city, LocalDate date) {
        return facade.getForecast(city)
                .map(mapper::toDomain);
    }
}
