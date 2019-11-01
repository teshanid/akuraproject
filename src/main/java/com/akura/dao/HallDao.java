package com.akura.dao;

import com.akura.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HallDao extends JpaRepository <Hall,Integer> {

    @Query(value="SELECT new Hall (h.id,h.name,h.capacity,h.branchId) FROM Hall h")
    List<Hall> list();

    @Query(value = "SELECT new Hall (h.id,h.name,h.capacity,h.branchId) FROM Hall h WHERE h.branchId.id = :branchid")
    List<Hall> listByBranch(@Param("branchid") Integer branchid);


}
