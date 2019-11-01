package com.akura.dao;

import com.akura.entity.Placementtestpayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PtestpaymentDao extends JpaRepository<Placementtestpayment, Integer> {


    @Query("SELECT p FROM Placementtestpayment p ORDER BY p.id DESC")
    Page<Placementtestpayment> findAll(Pageable of);

   /* @Query("SELECT p FROM Placementtestpayment p ORDER BY p.id DESC")
    List<Placementtestpayment> list();*/





}

