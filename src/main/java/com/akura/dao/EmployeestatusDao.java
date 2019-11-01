package com.akura.dao;


import com.akura.entity.Employeestatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeestatusDao extends JpaRepository<Employeestatus, Integer>
{

    @Query(value="SELECT new Employeestatus(e.id,e.name) FROM Employeestatus e")
    List<Employeestatus> list();


}