package com.georgeciachir.calendar.entry.persistence.entity;

import com.georgeciachir.calendar.entry.domain.model.Workout;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    Set<Workout> toDomain(Set<WorkoutEntity> entities);

    Workout toDomain(WorkoutEntity entity);

    WorkoutEntity toEntity(Workout domain);
}
