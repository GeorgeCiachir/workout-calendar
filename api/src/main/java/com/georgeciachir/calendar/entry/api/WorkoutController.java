package com.georgeciachir.calendar.entry.api;

import com.georgeciachir.calendar.entry.api.dto.DayDto;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RequestMapping("/workouts")
@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutFacade facade;

    @GetMapping("/{id}")
    public WorkoutDto findById(@PathVariable String id) {
        return facade.findById(id);
    }

    @PostMapping
    public WorkoutDto save(@RequestBody WorkoutDto workoutDto) {
        return facade.save(workoutDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        facade.deleteById(id);
    }

    @GetMapping
    public Set<DayDto> findByDateInterval(@RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate) {
        return facade.findInInterval(startDate, endDate);
    }

    //mock used for testing the circuitbreaker for the weather facade
    @GetMapping("/forecast/daily")
    public void testR4J() throws InterruptedException {
        throw new RuntimeException("foo");
//        Thread.sleep(1000);
    }
}
