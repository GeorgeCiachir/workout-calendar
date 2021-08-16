package com.georgeciachir.calendar.entry.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableEntity {

    private LocalTime startTime;
    private LocalTime endTime;
}
