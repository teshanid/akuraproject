package com.akura.dao;

import com.akura.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayDao extends JpaRepository<Day,Integer> {
    @Query(value="SELECT new Day (d.id,d.name) FROM Day d")
    List<Day> list();
}
