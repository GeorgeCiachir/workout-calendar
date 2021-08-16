package com.georgeciachir.calendar.entry.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.georgeciachir.caledar.domain.model.testfixtures.TimeTableFactory;
import com.georgeciachir.caledar.domain.model.testfixtures.WorkoutFactory;
import com.georgeciachir.calendar.entry.api.dto.DailyWeatherDto;
import com.georgeciachir.calendar.entry.api.dto.DayDto;
import com.georgeciachir.calendar.entry.api.dto.TimeTableDto;
import com.georgeciachir.calendar.entry.api.dto.WorkoutDto;
import com.georgeciachir.calendar.entry.domain.model.TimeTable;
import com.georgeciachir.calendar.entry.domain.model.Workout;
import com.georgeciachir.calendar.entry.textfixtures.DailyWeatherDtoFactory;
import com.georgeciachir.calendar.entry.textfixtures.TimeTableDtoFactory;
import com.georgeciachir.calendar.entry.textfixtures.WorkoutDtoFactory;
import com.georgeciachir.calendar.weather.response.WsDailyWeatherDto;
import com.georgeciachir.calendar.weather.response.WsDailyWeatherDtoFactory;
import com.georgeciachir.calendar.weather.response.WsIntervalForecastDto;
import com.georgeciachir.calendar.weather.response.WsIntervalForecastDtoFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WorkoutControllerIntegrationTest extends AbstractWebIntegrationTest {

    private static final String BASE_PATH = "/workouts";

    @Test
    void givenWorkoutDoesNotExistCreateWorkout() throws Exception {
        //given
        LocalDate today = LocalDate.now();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusHours(1);

        TimeTableDto intervalDto = TimeTableDtoFactory.interval(start, end);
        WorkoutDto toCreate = WorkoutDtoFactory.workoutAtDayAndInterval(today, intervalDto);

        //when
        String contentAsString = mockMvc.perform(
                post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toCreate)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        WorkoutDto actual = objectMapper.readValue(contentAsString, WorkoutDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(toCreate);
    }

    @Test
    void givenWorkoutExistsThenUpdateWorkout() throws Exception {
        //given
        LocalDate today = LocalDate.now();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusHours(1);

        TimeTable interval = TimeTableFactory.interval(start, end);
        Workout workout = WorkoutFactory.workoutAtDayAndInterval(today, interval);
        Workout saved = workoutService.save(workout);

        TimeTableDto intervalDto = TimeTableDtoFactory.interval(start, end);
        String updatedDescription = "a new description";
        WorkoutDto toUpdate = WorkoutDtoFactory.workoutAtDayAndInterval(today, intervalDto)
                .toBuilder()
                .id(saved.getId())
                .description(updatedDescription)
                .build();

        //when
        String contentAsString = mockMvc.perform(
                post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toUpdate)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        WorkoutDto actual = objectMapper.readValue(contentAsString, WorkoutDto.class);
        assertThat(actual).isEqualTo(toUpdate);
    }

    @Test
    void givenWorkoutExistsThenDeleteWorkout() throws Exception {
        //given
        LocalDate today = LocalDate.now();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusHours(1);

        TimeTable interval = TimeTableFactory.interval(start, end);
        Workout workout = WorkoutFactory.workoutAtDayAndInterval(today, interval);
        Workout saved = workoutService.save(workout);

        //when
        mockMvc.perform(delete(BASE_PATH + "/{id}", saved.getId()))
                .andExpect(status().isOk());

        String actual = mockMvc.perform(get(BASE_PATH + "/{id}", saved.getId()))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String messageTemplate = "The %s with id %s was not found";
        assertThat(actual).isEqualTo(String.format(messageTemplate, Workout.class.getSimpleName(), saved.getId()));
    }

    @Test
    void givenWorkoutsExistsThenRetrievedWorkoutsInIntervalDoNotContainWeather() throws Exception {
        //given
        LocalDate today = LocalDate.now();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusHours(1);

        TimeTable interval = TimeTableFactory.interval(start, end);
        Workout todayWorkout = WorkoutFactory.workoutAtDayAndInterval(today, interval);
        Workout nextWeekWorkout = WorkoutFactory.workoutAtDayAndInterval(today.plusDays(7), interval);
        Workout previousWeekWorkout = WorkoutFactory.workoutAtDayAndInterval(today.minusDays(7), interval);
        Workout todaySaved = workoutService.save(todayWorkout);
        workoutService.save(nextWeekWorkout);
        workoutService.save(previousWeekWorkout);

        //when
        String contentAsString = mockMvc.perform(
                get(BASE_PATH)
                        .queryParam("startDate", today.toString())
                        .queryParam("endDate", today.toString()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        Set<DayDto> actual = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        TimeTableDto intervalDto = TimeTableDtoFactory.interval(start, end);
        WorkoutDto workoutDto = WorkoutDtoFactory.workoutAtDayAndInterval(today, intervalDto)
                .toBuilder()
                .id(todaySaved.getId())
                .build();

        assertThat(actual).hasSize(1);
        DayDto actualDay = actual.stream().findFirst().get();
        assertThat(actualDay.getWorkouts()).contains(workoutDto);
        WorkoutDto actualWorkoutDto = actualDay.getWorkouts().stream().findFirst().get();
        assertThat(actualWorkoutDto.getWeather()).isNull();
    }

    @Test
    void givenWorkoutExistsThenRetrievedWorkoutByIdContainsWeather() throws Exception {
        //given
        LocalDate today = LocalDate.now();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusHours(1);

        TimeTable interval = TimeTableFactory.interval(start, end);
        Workout workout = WorkoutFactory.workoutAtDayAndInterval(today, interval);
        Workout saved = workoutService.save(workout);

        int minTemp = 12;
        int maxTemp = 22;
        stubWeatherService(wsForecast(today, minTemp, maxTemp), workout.getLocation());

        //when
        String contentAsString = mockMvc.perform(get(BASE_PATH + "/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        WorkoutDto actual = objectMapper.readValue(contentAsString, WorkoutDto.class);
        TimeTableDto intervalDto = TimeTableDtoFactory.interval(start, end);

        DailyWeatherDto weatherDto = DailyWeatherDtoFactory.dailyWeatherDto(minTemp, maxTemp);
        WorkoutDto expected = WorkoutDtoFactory.workoutWithWeather(today, intervalDto, weatherDto).toBuilder()
                .id(saved.getId())
                .build();

        assertThat(actual).isEqualTo(expected);
    }

    private WsIntervalForecastDto wsForecast(LocalDate start, int min, int max) {

        WsDailyWeatherDto startDayWeather = WsDailyWeatherDtoFactory.getWsDailyWeatherDto(start, min, max);
        WsDailyWeatherDto endDayWeather = WsDailyWeatherDtoFactory.getWsDailyWeatherDto(start.plusDays(1), min + 4, max + 3);

        return WsIntervalForecastDtoFactory.forecast(startDayWeather, endDayWeather);
    }
}
