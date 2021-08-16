package com.georgeciachir.calendar.entry.persistence;

import com.georgeciachir.calendar.entry.persistence.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface SpringDataWorkoutRepository extends JpaRepository<WorkoutEntity, String> {

    Set<WorkoutEntity> findWorkoutEntityByDateBetween(LocalDate start, LocalDate end);
}
