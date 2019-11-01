package com.akura.dao;

import com.akura.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectDao extends JpaRepository <Subject,Integer>{
    @Query(value="SELECT new Subject (s.id,s.name) FROM Subject s")
    List<Subject> list();

    @Query("SELECT s FROM Subject s WHERE s.name= :name")
    Subject findByName(@Param("name") String name);

    @Query(value="SELECT new Subject (s.id,s.name) FROM Subject s WHERE  s IN (SELECT g.subjectId FROM Gradesubject g WHERE g.gradeId.id= :gradeid)")
    List<Subject> listByGrade(@Param("gradeid") Integer gradeid);
}
