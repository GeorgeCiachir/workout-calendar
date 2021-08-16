package com.georgeciachir.calendar.entry.service;

import com.georgeciachir.calendar.ResourceNotFoundException;
import com.georgeciachir.calendar.entry.domain.WeatherAdapter;
import com.georgeciachir.calendar.entry.domain.WorkoutRepository;
import com.georgeciachir.calendar.entry.domain.model.Day;
import com.georgeciachir.calendar.entry.domain.model.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WeatherAdapter weatherAdapter;

    public Workout findById(String id) {
        return workoutRepository.findById(id)
                .map(this::addWeather)
                .orElseThrow(() -> new ResourceNotFoundException(Workout.class, id));
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Set<Day> findInInterval(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Set<Workout>> workoutsByDate = workoutRepository.findInInterval(startDate, endDate).stream()
                .collect(groupingBy(Workout::getDate, toSet()));

        return workoutsByDate.entrySet().stream()
                .map(entry -> Day.having(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Workout addWeather(Workout workout) {
        return weatherAdapter.getForecast(workout.getLocation(), workout.getDate())
                .map(workout::withWeather)
                .orElse(workout);
    }

    public void deleteById(String id) {
        workoutRepository.deleteById(id);
    }
}
