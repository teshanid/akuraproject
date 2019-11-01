package com.akura.dao;


import com.akura.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StudentDao extends JpaRepository<Student, Integer> {

   Student findByNumber(String number);

   Student findByFullname(String fullname);

   Optional<Student> findTopByNumberStartsWithOrderByNumberDesc(String number);

   /* @Query("SELECT s FROM Student s ORDER BY s.id DESC")
    Page<Student> findAll(Pageable of);*/

    @Query("SELECT s FROM Student s ORDER BY s.number DESC")
    Page<Student> findAll(Pageable of);

    @Query(value="SELECT new Student (s.id,s.number,s.callingname) FROM Student s")
    List<Student> list();

    @Query(value="SELECT new Student (s.id,s.number,s.callingname) FROM Student s WHERE s.fullname =fullname")
    List<Student> listByFullname(@Param("fullname") String fullname);

    @Query(value="SELECT new Student (s.id,s.number,s.callingname) FROM Student s WHERE s IN (SELECT s.studentId FROM Studentclas s WHERE s.clasId.id= :clasid)")
    List<Student> listByClas(@Param("clasid") Integer clasid);



    /*@Query("SELECT s FROM Student s WHERE s.number= :number")
    Student findByNumber(@Param("number")String number);*/


/*
    @Query("SELECT e FROM Employee e WHERE e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByEmployeestatus(Pageable of, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.designationId.id= :designationid")
    Page<Employee> findAllByDesignation(Pageable of, @Param("designationid")Integer designationid);

    @Query("SELECT e FROM Employee e WHERE e.designationId.id= :designationid AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByDesignationEmployeestaus(Pageable of, @Param("designationid")Integer designationid, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic")
    Page<Employee> findAllByNameNIC(Pageable of, @Param("name")String name, @Param("nic")String nic);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.designationId.id= :designationid")
    Page<Employee> findAllByNameNICDesignation(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("designationid")Integer designationid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByNameNICEmployeestatus(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("employeestatusid")Integer employeestatusid);

    @Query("SELECT e FROM Employee e WHERE e.fullname like :name AND e.nic like :nic AND e.designationId.id= :designationid AND e.employeestatusId.id= :employeestatusid")
    Page<Employee> findAllByNameNICDesignationEmployeestatus(Pageable of, @Param("name")String name, @Param("nic")String nic, @Param("designationid")Integer designationid, @Param("employeestatusid")Integer employeestatusid);


*/



}

