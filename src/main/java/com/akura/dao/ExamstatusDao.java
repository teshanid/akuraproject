package com.akura.dao;

import com.akura.entity.Examstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamstatusDao extends JpaRepository <Examstatus,Integer> {

    @Query(value="SELECT new Examstatus(e.id,e.name) FROM Examstatus e")
    List<Examstatus> list();
}
