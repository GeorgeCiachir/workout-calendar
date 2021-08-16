package com.georgeciachir.calendar.entry.api;

import com.georgeciachir.calendar.entry.api.dto.DayDto;
import com.georgeciachir.calendar.entry.api.dto.DayDtoMapper;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDto;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDtoMapper;
import com.georgeciachir.calendar.entry.domain.model.Day;
import com.georgeciachir.calendar.entry.domain.model.Workout;
import com.georgeciachir.calendar.entry.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class WorkoutFacade {

    private final WorkoutService workoutService;
    private final WorkoutDtoMapper workoutDtoMapper;
    private final DayDtoMapper dayDtoMapper;

    public WorkoutDto findById(String id) {
        Workout workout = workoutService.findById(id);
        return workoutDtoMapper.toDto(workout);
    }

    public WorkoutDto save(WorkoutDto workoutDto) {
        Workout workout = workoutDtoMapper.toDomain(workoutDto);
        Workout created = workoutService.save(workout);
        return workoutDtoMapper.toDto(created);
    }

    public Set<DayDto> findInInterval(LocalDate startDate, LocalDate endDate) {
        Set<Day> day = workoutService.findInInterval(startDate, endDate);
        return dayDtoMapper.toDto(day);
    }

    public void deleteById(String id) {
        workoutService.deleteById(id);
    }
}
