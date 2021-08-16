package com.georgeciachir.calendar.entry.api.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class TimeTableDto {

    LocalTime startTime;
    LocalTime endTime;
}
