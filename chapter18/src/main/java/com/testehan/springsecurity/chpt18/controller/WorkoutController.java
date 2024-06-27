package com.testehan.springsecurity.chpt18.controller;

import com.testehan.springsecurity.chpt18.model.Workout;
import com.testehan.springsecurity.chpt18.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/workout")
    public void add(@RequestBody Workout workout) {
        workoutService.saveWorkout(workout);
    }
    @GetMapping("/workout")
    public List<Workout> findAll() {
        return workoutService.findWorkouts();
    }
    @DeleteMapping("/workout/{id}")
    public void delete(@PathVariable Integer id) {
        workoutService.deleteWorkout(id);
    }
}
