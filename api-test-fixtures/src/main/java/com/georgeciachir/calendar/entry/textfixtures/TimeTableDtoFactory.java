package com.georgeciachir.calendar.entry.textfixtures;

import com.georgeciachir.calendar.entry.api.dto.TimeTableDto;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeTableDtoFactory {

    public static TimeTableDto interval(LocalTime start, LocalTime end) {
        return TimeTableDto.builder()
                .startTime(start.truncatedTo(ChronoUnit.SECONDS))
                .endTime(end.truncatedTo(ChronoUnit.SECONDS))
                .build();
    }
}
