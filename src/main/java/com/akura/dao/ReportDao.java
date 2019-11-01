package com.akura.dao;

import com.akura.entity.Sessionlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportDao extends JpaRepository<Sessionlog,Integer> {
    @Query("SELECT s FROM Sessionlog s WHERE s.logintime BETWEEN :sdate AND :edate")
    List<Object> systemaccessanalysis(@Param("sdate") LocalDateTime sdate, @Param("edate") LocalDateTime edate);
}
