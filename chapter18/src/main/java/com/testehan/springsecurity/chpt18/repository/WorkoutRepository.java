package com.testehan.springsecurity.chpt18.repository;

import com.testehan.springsecurity.chpt18.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    // A SpEL expression retrieves the value of the authenticated username from the security context.
    @Query("SELECT w FROM Workout w WHERE w.userr = ?#{ authentication.principal.getClaims().get('user_name')}")
    List<Workout> findAllByUser();
}
