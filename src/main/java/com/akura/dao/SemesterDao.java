package com.akura.dao;

import com.akura.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SemesterDao extends JpaRepository <Semester,Integer> {

    @Query(value="SELECT new Semester (s.id,s.name,s.fee) FROM Semester s")
    List<Semester> list();




}
