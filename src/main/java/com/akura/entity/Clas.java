/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.akura.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
@Table(name = "clas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clas.findAll", query = "SELECT c FROM Clas c")})
public class Clas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "starttime")

    private LocalTime starttime;
    @Column(name = "endtime")

    private LocalTime endtime;
    @Column(name = "dostart")

    private LocalDate dostart;
    @Column(name = "dofinish")

    private LocalDate dofinish;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fee")
    @RegexPattern(regexp = "^([\\d]+(.[\\d]{2})?)$", message = "Invalid fee")
    private BigDecimal fee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clasId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexam> semesterexamList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clasId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Studentclas> studentclasList;
    @JoinColumn(name = "classtatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Classtatus classtatusId;
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Day dayId;
    @JoinColumn(name = "employee_teacher_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeTeacherId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Grade gradeId;
    @JoinColumn(name = "medium_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Medium mediumId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Room roomId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Subject subjectId;

    public Clas() {
    }

    public Clas(Integer id,String name,BigDecimal fee) {
        this.id = id;
        this.name=name;
        this.fee=fee;
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

    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    public LocalDate getDostart() {
        return dostart;
    }

    public void setDostart(LocalDate dostart) {
        this.dostart = dostart;
    }

    public LocalDate getDofinish() {
        return dofinish;
    }

    public void setDofinish(LocalDate dofinish) {
        this.dofinish = dofinish;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @XmlTransient
    public List<Semesterexam> getSemesterexamList() {
        return semesterexamList;
    }

    public void setSemesterexamList(List<Semesterexam> semesterexamList) {
        this.semesterexamList = semesterexamList;
    }

    @XmlTransient
    public List<Studentclas> getStudentclasList() {
        return studentclasList;
    }

    public void setStudentclasList(List<Studentclas> studentclasList) {
        this.studentclasList = studentclasList;
    }

    public Classtatus getClasstatusId() {
        return classtatusId;
    }

    public void setClasstatusId(Classtatus classtatusId) {
        this.classtatusId = classtatusId;
    }

    public Day getDayId() {
        return dayId;
    }

    public void setDayId(Day dayId) {
        this.dayId = dayId;
    }

    public Employee getEmployeeTeacherId() {
        return employeeTeacherId;
    }

    public void setEmployeeTeacherId(Employee employeeTeacherId) {
        this.employeeTeacherId = employeeTeacherId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Grade getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grade gradeId) {
        this.gradeId = gradeId;
    }

    public Medium getMediumId() {
        return mediumId;
    }

    public void setMediumId(Medium mediumId) {
        this.mediumId = mediumId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
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
        if (!(object instanceof Clas)) {
            return false;
        }
        Clas other = (Clas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Clas[ id=" + id + " ]";
    }
    
}
