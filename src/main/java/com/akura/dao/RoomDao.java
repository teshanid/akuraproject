package com.akura.dao;

import com.akura.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomDao extends JpaRepository <Room,Integer> {

    @Query(value="SELECT new Room (r.id,r.name,r.capacity,r.branchId) FROM Room r")
    List<Room> list();

    @Query(value = "SELECT new Room (r.id,r.name,r.capacity,r.branchId) FROM Room r WHERE r.branchId.id = :branchid")
    List<Room> listByBranch(@Param("branchid") Integer branchid);


}
