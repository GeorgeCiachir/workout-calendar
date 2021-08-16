package com.georgeciachir.calendar.weather.response;

import java.util.Arrays;

public class WsIntervalForecastDtoFactory {

    public static WsIntervalForecastDto forecast(WsDailyWeatherDto... dailyWeather) {
        return WsIntervalForecastDto.builder()
                .intervalWeather(Arrays.asList(dailyWeather))
                .build();
    }
}
