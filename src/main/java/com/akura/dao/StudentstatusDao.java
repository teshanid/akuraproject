package com.akura.dao;


import com.akura.entity.Studentstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentstatusDao extends JpaRepository<Studentstatus, Integer>
{

    @Query(value="SELECT new Studentstatus(s.id,s.name) FROM Studentstatus s")
    List<Studentstatus> list();

    @Query("SELECT s FROM Studentstatus s WHERE s.name= :name")
    Studentstatus findByName(@Param("name") String name);


}