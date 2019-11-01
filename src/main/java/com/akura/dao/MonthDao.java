package com.akura.dao;

import com.akura.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthDao extends JpaRepository<Month,Integer> {
    @Query(value="SELECT new Month (m.id,m.name) FROM Month m")
    List<Month> list();
}
