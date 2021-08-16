package com.georgeciachir.calendar.entry.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "WORKOUT")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutEntity {

    @Id
    private String id;

    private String location;

    private LocalDate date;

    @Embedded
    private TimeTableEntity timeTable;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private WeekDayEntity weekday;
}
