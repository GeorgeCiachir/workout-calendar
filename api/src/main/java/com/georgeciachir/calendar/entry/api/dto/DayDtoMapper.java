package com.georgeciachir.calendar.entry.api.dto;

import com.georgeciachir.calendar.entry.domain.model.Day;
import org.mapstruct.Mapper;

import java.util.Set;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", uses = WorkoutDtoMapper.class, injectionStrategy = CONSTRUCTOR)
public interface DayDtoMapper {

    Set<DayDto> toDto(Set<Day> domain);
}
