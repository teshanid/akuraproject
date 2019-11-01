package com.akura.dao;

import com.akura.entity.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediumDao extends JpaRepository<Medium,Integer> {
    @Query(value="SELECT new Medium (m.id,m.name) FROM Medium m")
    List<Medium> list();

    /*@Query(value="SELECT new Medium (m.id,m.name) FROM Medium m WHERE  m IN (SELECT s.mediumId FROM Subjectmedium s WHERE s.subject.id= :subject)")
    List<Medium> listBySubject(@Param("subject")Integer subject);*/

    @Query(value="SELECT new Medium (m.id,m.name) FROM Medium m WHERE  m IN (SELECT s.mediumId FROM Subjectmedium s WHERE s.subjectId.id= :subjectid)")
    List<Medium> listBySubject(@Param("subjectid") Integer subjectid);
}

