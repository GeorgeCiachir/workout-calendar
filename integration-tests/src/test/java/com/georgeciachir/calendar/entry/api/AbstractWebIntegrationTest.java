package com.georgeciachir.calendar.entry.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeciachir.calendar.entry.service.WorkoutService;
import com.georgeciachir.calendar.weather.response.WsIntervalForecastDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@AutoConfigureMockMvc
@SpringBootTest
public abstract class AbstractWebIntegrationTest {

    private static final String FORECAST_ENDPOINT = "/forecast/daily?q=%s&cnt=%s&units=%s";

    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(options().port(1234));

    @Autowired
    protected WorkoutService workoutService;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void reset() {
        WIRE_MOCK_SERVER.resetAll();
    }

    @BeforeAll
    static void startWireMock() {
        WIRE_MOCK_SERVER.resetAll();
        WIRE_MOCK_SERVER.start();
        WireMock.configureFor("localhost", 1234);
    }

    @AfterAll
    static void stopWireMock() {
        WIRE_MOCK_SERVER.stop();
    }

    @SneakyThrows
    void stubWeatherService(WsIntervalForecastDto forecastDto, String city) {
        String stubbedResponse = objectMapper.writeValueAsString(forecastDto);

        String urlWithParams = String.format(FORECAST_ENDPOINT, city, 16, "metric");

        WIRE_MOCK_SERVER.stubFor(
                get(urlEqualTo(urlWithParams))
                        .withHeader("x-rapidapi-key", equalTo("key"))
                        .withHeader("x-rapidapi-host", equalTo("host"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withStatus(HttpStatus.OK.value())
                                .withBody(stubbedResponse)));
    }
}
