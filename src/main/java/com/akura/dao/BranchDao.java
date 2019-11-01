package com.akura.dao;

import com.akura.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchDao extends JpaRepository<Branch,Integer> {
    @Query(value="SELECT new Branch (b.id,b.name) FROM Branch b")
    List<Branch> list();
}
