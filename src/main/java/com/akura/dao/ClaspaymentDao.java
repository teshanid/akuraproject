package com.akura.dao;

import com.akura.entity.Claspayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClaspaymentDao extends JpaRepository<Claspayment, Integer> {


    @Query("SELECT c FROM Claspayment c ORDER BY c.id DESC")
    Page<Claspayment> findAll(Pageable of);

/*    @Query("SELECT p FROM Placementtest p WHERE p.hallId= :hallid")
    Placementtestpayment findByHall(@Param("hallid") Integer hallid);*/

    /*
    @Query(value="SELECT new Employee(e.id,e.callingname) FROM Employee e WHERE e not in (Select u.employeeId from User u)")
    List<Employee> listWithoutUsers();

    @Query(value="SELECT new Employee(e.id,e.callingname) FROM Employee e WHERE e in (Select u.employeeId from User u)")
    List<Employee> listWithUseraccount();

    @Query("SELECT e FROM Employee e ORDER BY e.id DESC")
    Page<Employee> findAll(Pageable of);


    @Query("SELECT e FROM Employee e WHERE e.nic= :nic")
    Employee findByNIC(@Param("nic") String nic);

    @Query("SELECT e FROM Employee e WHERE e.number= :number")
    Employee findByNumber(@Param("number") String number);

    @Query(value="SELECT new Employee(e.id,e.callingname) FROM Employee e WHERE e.designationId=2")
    List<Employee> listByDesignation();



   @Query(value="SELECT new Employee (e.id,e.callingname) FROM Employee e WHERE e.designationId=2 AND e IN (SELECT t.employeeTeacherId FROM Teachersubject t WHERE t.subjectId.id= :subjectid)")
    List<Employee> listBySubject(@Param("subjectid") Integer subjectid);*/





}

