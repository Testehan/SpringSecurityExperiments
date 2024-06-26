package com.testehan.springsecurity.chpt18.service;

import com.testehan.springsecurity.chpt18.model.Workout;
import com.testehan.springsecurity.chpt18.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;

    // By preauthorization, ensures the method isn’t called if the workout record doesn’t belong to the user
    @PreAuthorize("#workout.userr == authentication.principal.getClaims().get('user_name')")
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }
    public List<Workout> findWorkouts() {
        return workoutRepository.findAllByUser();
    }
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }
}
