package com.georgeciachir.calendar.weather;

import com.georgeciachir.calendar.http.HttpClient;
import com.georgeciachir.calendar.weather.response.WsIntervalForecastDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Log4j2
@Component
@RequiredArgsConstructor
public class WeatherFacade {

    private static final String FORECAST_ENDPOINT = "/forecast/daily";

    private final HttpClient httpClient;

    @Value("${api.weather.basepath}")
    private String basePath;

    @Value("${api.weather.app.key}")
    private String appKeyHeader;

    @Value("${api.weather.app.host}")
    private String appHostHeader;

    public Optional<WsIntervalForecastDto> getForecast(String city) {
        String url = basePath + FORECAST_ENDPOINT;
        try {
            return getForecastInternal(city, url)
                    .map(HttpEntity::getBody);
        } catch (Exception e) {
            log.error("Error retrieving the forecast", e);
            return empty();
        }
    }

    @CircuitBreaker(name = "getForecastInternal")
    private Optional<ResponseEntity<WsIntervalForecastDto>> getForecastInternal(String city, String url) {
        ResponseEntity<WsIntervalForecastDto> response = httpClient.doGet(buildUri(url, city), WsIntervalForecastDto.class, headersForForecast());
        return ofNullable(response);
    }

    private URI buildUri(String url, String city) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParam("q", city)
                .queryParam("cnt", 16)
                .queryParam("units", "metric")
                .build()
                .toUri();
    }

    private HttpHeaders headersForForecast() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", appKeyHeader);
        headers.set("x-rapidapi-host", appHostHeader);
        return headers;
    }
}
