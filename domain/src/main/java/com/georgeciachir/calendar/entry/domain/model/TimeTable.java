package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class TimeTable {

    LocalTime startTime;
    LocalTime endTime;
}
