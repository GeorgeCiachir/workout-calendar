package com.georgeciachir.calendar.entry.api.dto;

import com.georgeciachir.calendar.entry.domain.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutDtoMapper {

    WorkoutDto toDto(Workout domain);

    Workout toDomain(WorkoutDto dto);
}
