package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

}
