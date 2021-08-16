package com.georgeciachir.calendar.entry.persistence;

import com.georgeciachir.calendar.entry.domain.WorkoutRepository;
import com.georgeciachir.calendar.entry.domain.model.Workout;
import com.georgeciachir.calendar.entry.persistence.entity.WorkoutEntity;
import com.georgeciachir.calendar.entry.persistence.entity.WorkoutMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MySqlWorkoutRepository implements WorkoutRepository {

    private final SpringDataWorkoutRepository springDataRepository;
    private final WorkoutMapper mapper;

    public Optional<Workout> findById(String id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Workout save(Workout workout) {
        WorkoutEntity entity = Optional.ofNullable(workout.getId())
                .flatMap(springDataRepository::findById)
                .map(existing -> updateExistingEntity(workout))
                .orElseGet(() -> newEntity(workout));

        springDataRepository.save(entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Set<Workout> findInInterval(LocalDate startDate, LocalDate endDate) {

        Set<WorkoutEntity> entitiesByDates = springDataRepository.findWorkoutEntityByDateBetween(startDate, endDate);

        return mapper.toDomain(entitiesByDates);
    }

    @Override
    public void deleteById(String id) {
        springDataRepository.deleteById(id);
    }

    private WorkoutEntity updateExistingEntity(Workout workout) {
        return mapper.toEntity(workout);
    }

    private WorkoutEntity newEntity(Workout workout) {
        return mapper.toEntity(workout)
                .toBuilder()
                .id(UUID.randomUUID().toString())
                .build();
    }
}
