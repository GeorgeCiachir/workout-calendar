package com.georgeciachir.calendar.entry.domain;

import com.georgeciachir.calendar.entry.domain.model.Workout;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface WorkoutRepository {

    Optional<Workout> findById(String id);

    Workout save(Workout workout);

    Set<Workout> findInInterval(LocalDate startDate, LocalDate endDate);

    void deleteById(String id);
}
