package com.learn.notes.repository;

import com.learn.notes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {
    Optional<Notes> findByTitle(@Param("title") String title);
}
