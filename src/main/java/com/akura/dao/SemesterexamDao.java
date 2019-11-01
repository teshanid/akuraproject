package com.akura.dao;

import com.akura.entity.Semesterexam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SemesterexamDao extends JpaRepository<Semesterexam, Integer> {


    @Query("SELECT s FROM Semesterexam s ORDER BY s.id DESC")
    Page<Semesterexam> findAll(Pageable of);

    @Query(value="SELECT new Semesterexam(s.id,s.name) FROM Semesterexam s")
    List<Semesterexam> list();

    @Query(value = "SELECT  s FROM Semesterexam s WHERE s.clasId.id = :clasid AND s.examstatusId =1 or s.examstatusId =2 ")
    List<Semesterexam> listByClas(@Param("clasid") Integer clasid);
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

