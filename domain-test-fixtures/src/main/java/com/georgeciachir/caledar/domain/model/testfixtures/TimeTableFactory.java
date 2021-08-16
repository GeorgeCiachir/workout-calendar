package com.georgeciachir.caledar.domain.model.testfixtures;

import com.georgeciachir.calendar.entry.domain.model.TimeTable;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeTableFactory {

    public static TimeTable interval(LocalTime start, LocalTime end) {
        return TimeTable.builder()
                .startTime(start.truncatedTo(ChronoUnit.SECONDS))
                .endTime(end.truncatedTo(ChronoUnit.SECONDS))
                .build();
    }
}
