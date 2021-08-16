package com.georgeciachir.calendar.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WsDailyWeatherDto {

    @JsonProperty("dt")
    long epochSeconds;

    @JsonProperty("temp")
    WsTemperatureDto temperature;
}
