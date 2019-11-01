package com.akura.dao;

import com.akura.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    @Query(value="SELECT new Employee(e.id,e.callingname) FROM Employee e")
    List<Employee> list();

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
    List<Employee> listBySubject(@Param("subjectid") Integer subjectid);




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

