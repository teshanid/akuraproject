package com.akura.dao;

import com.akura.entity.Clas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClasDao extends JpaRepository<Clas,Integer> {

    @Query(value="SELECT c FROM Clas c")
    List<Clas> list();


    @Query("SELECT c FROM Clas c ORDER BY c.id DESC")
    Page<Clas> findAll(Pageable of);


    @Query("SELECT c FROM Clas c WHERE c.name= :name")
    Clas findByName(@Param("name") String name);

    @Query(value="SELECT new Clas (c.id,c.name,c.fee) FROM Clas c WHERE c IN (SELECT s.clasId FROM Studentclas s WHERE s.studentId.id= :studentid)")
    List<Clas> listByStudent(@Param("studentid") Integer studentid);







}
