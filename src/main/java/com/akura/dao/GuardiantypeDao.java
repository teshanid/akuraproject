package com.akura.dao;

import com.akura.entity.Guardiantype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


    public interface GuardiantypeDao extends JpaRepository<Guardiantype, Integer>
    {

        @Query(value="SELECT new Guardiantype (g.id,g.name) FROM Guardiantype g")
        List<Guardiantype> list();

        @Query("SELECT g FROM Guardiantype g WHERE g.name= :name")
        Guardiantype findByName(@Param("name") String name);

    }

