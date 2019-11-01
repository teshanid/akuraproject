/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    @Pattern(regexp = "^\\d{6}$", message = "Invalid Number")
    private String number;

    @Column(name = "fullname")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Fullname")
    private String fullname;

    @Column(name = "callingname")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Calligname")
    private String callingname;

    @Column(name = "dobirth")
    private LocalDate dobirth;

    @Lob
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{5,})$", message = "Invalid Address")
    private String address;

    @Column(name = "guardianname")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Fullname")
    private String guardianname;

    @Column(name = "guardianmobile")
    @Pattern(regexp = "^(07[0125678]\\d{7})$", message = "Invalid Telephone Number(Mobile)")
    private String guardianmobile;

    @Column(name = "guardianresidence")
    @Pattern(regexp = "^(((0\\d{9})){0,1})$", message = "Invalid Telephone Number(Residence)")
    private String guardianresidence;

    @Column(name = "guardianworking")
    @Pattern(regexp = "^(((0\\d{9})){0,1})$", message = "Invalid Landphone Number(Working)")
    private String guardianworking;

    @Column(name = "doregistered")
    private LocalDate doregistered;

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;

    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Gender genderId;

    @JoinColumn(name = "guardiantype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Guardiantype guardiantypeId;

    @JoinColumn(name = "studentstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Studentstatus studentstatusId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Placementtestpayment> placementtestpaymentList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Studentclas> studentclasList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Result> resultList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexampayment> semesterexampaymentList;

    public Student() {
    }

    public Student(Integer id,String number,String callingname) {
        this.id = id;
        this.number=number;
        this.callingname=callingname;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCallingname() {
        return callingname;
    }

    public void setCallingname(String callingname) {
        this.callingname = callingname;
    }

    public LocalDate getDobirth() {
        return dobirth;
    }

    public void setDobirth(LocalDate dobirth) {
        this.dobirth = dobirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuardianname() {
        return guardianname;
    }

    public void setGuardianname(String guardianname) {
        this.guardianname = guardianname;
    }

    public String getGuardianmobile() {
        return guardianmobile;
    }

    public void setGuardianmobile(String guardianmobile) {
        this.guardianmobile = guardianmobile;
    }

    public String getGuardianresidence() {
        return guardianresidence;
    }

    public void setGuardianresidence(String guardianresidence) {
        this.guardianresidence = guardianresidence;
    }

    public String getGuardianworking() {
        return guardianworking;
    }

    public void setGuardianworking(String guardianworking) {
        this.guardianworking = guardianworking;
    }

    public LocalDate getDoregistered() {
        return doregistered;
    }

    public void setDoregistered(LocalDate doregistered) {
        this.doregistered = doregistered;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Gender getGenderId() {
        return genderId;
    }

    public void setGenderId(Gender genderId) {
        this.genderId = genderId;
    }

    public Guardiantype getGuardiantypeId() {
        return guardiantypeId;
    }

    public void setGuardiantypeId(Guardiantype guardiantypeId) {
        this.guardiantypeId = guardiantypeId;
    }

    public Studentstatus getStudentstatusId() {
        return studentstatusId;
    }

    public void setStudentstatusId(Studentstatus studentstatusId) {
        this.studentstatusId = studentstatusId;
    }

    @XmlTransient
    public List<Placementtestpayment> getPlacementtestpaymentList() {
        return placementtestpaymentList;
    }

    public void setPlacementtestpaymentList(List<Placementtestpayment> placementtestpaymentList) {
        this.placementtestpaymentList = placementtestpaymentList;
    }

    @XmlTransient
    public List<Studentclas> getStudentclasList() {
        return studentclasList;
    }

    public void setStudentclasList(List<Studentclas> studentclasList) {
        this.studentclasList = studentclasList;
    }

    @XmlTransient
    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @XmlTransient
    public List<Semesterexampayment> getSemesterexampaymentList() {
        return semesterexampaymentList;
    }

    public void setSemesterexampaymentList(List<Semesterexampayment> semesterexampaymentList) {
        this.semesterexampaymentList = semesterexampaymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Student[ id=" + id + " ]";
    }
    
}
