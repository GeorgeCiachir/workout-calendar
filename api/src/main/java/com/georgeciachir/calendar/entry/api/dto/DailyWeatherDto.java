package com.georgeciachir.calendar.entry.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyWeatherDto {

    TemperatureDto temperature;
}
