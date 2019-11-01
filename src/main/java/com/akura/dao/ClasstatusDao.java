package com.akura.dao;

import com.akura.entity.Classtatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClasstatusDao extends JpaRepository<Classtatus,Integer> {

        @Query(value="SELECT new Classtatus (c.id,c.name) FROM Classtatus c")
        List<Classtatus> list();
}
