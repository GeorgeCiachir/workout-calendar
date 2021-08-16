package com.georgeciachir.calendar.entry.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

//@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemperatureDto {

    Integer max;
    Integer min;
}
