package com.akura.dao;

import com.akura.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeDao extends JpaRepository <Grade,Integer> {

    @Query(value="SELECT new Grade (g.id,g.name) FROM Grade g")
    List<Grade> list();

    @Query("SELECT g FROM Grade g WHERE g.name= :name")
    Grade findByName(@Param("name") String name);

}
