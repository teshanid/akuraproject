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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "semesterexam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semesterexam.findAll", query = "SELECT s FROM Semesterexam s")})
public class Semesterexam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "doexam")

    private LocalDate doexam;
    @JoinColumn(name = "clas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Clas clasId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "examstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Examstatus examstatusId;
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Semester semesterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterexamId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Result> resultList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterexamId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexampayment> semesterexampaymentList;

    public Semesterexam() {
    }

    public Semesterexam(Integer id,String name) {
        this.id = id;
        this.name = name;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDoexam() {
        return doexam;
    }

    public void setDoexam(LocalDate doexam) {
        this.doexam = doexam;
    }

    public Clas getClasId() {
        return clasId;
    }

    public void setClasId(Clas clasId) {
        this.clasId = clasId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Examstatus getExamstatusId() {
        return examstatusId;
    }

    public void setExamstatusId(Examstatus examstatusId) {
        this.examstatusId = examstatusId;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
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
        if (!(object instanceof Semesterexam)) {
            return false;
        }
        Semesterexam other = (Semesterexam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Semesterexam[ id=" + id + " ]";
    }
    
}
