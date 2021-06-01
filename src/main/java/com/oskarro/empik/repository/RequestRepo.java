package com.oskarro.empik.repository;

import com.oskarro.empik.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request, String> {

    @Modifying
    @Query("update Request req set req.counter = req.counter + 1 WHERE req.login = :login")
    void incrementValue(String login);
}
