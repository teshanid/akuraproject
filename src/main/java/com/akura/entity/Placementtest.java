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
@Table(name = "placementtest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Placementtest.findAll", query = "SELECT p FROM Placementtest p")})
public class Placementtest implements Serializable {

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
    @Column(name = "starttime")

    private LocalTime starttime;
    @Column(name = "endtime")

    private LocalTime endtime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fee")
    @RegexPattern(regexp = "^([\\d]+(.[\\d]{2})?)$", message = "Invalid fee")
    private BigDecimal fee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placementtestId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Placementtestpayment> placementtestpaymentList;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Hall hallId;
    @JoinColumn(name = "examstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Examstatus examstatusId;

    public Placementtest() {
    }

    public Placementtest(Integer id,String name,BigDecimal fee) {
        this.id = id;
        this.name= name;
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


    public LocalDate getDoexam() {
        return doexam;
    }

    public void setDoexam(LocalDate doexam) {
        this.doexam = doexam;
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @XmlTransient
    public List<Placementtestpayment> getPlacementtestpaymentList() {
        return placementtestpaymentList;
    }

    public void setPlacementtestpaymentList(List<Placementtestpayment> placementtestpaymentList) {
        this.placementtestpaymentList = placementtestpaymentList;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
    }

    public Examstatus getExamstatusId() {
        return examstatusId;
    }

    public void setExamstatusId(Examstatus examstatusId) {
        this.examstatusId = examstatusId;
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
        if (!(object instanceof Placementtest)) {
            return false;
        }
        Placementtest other = (Placementtest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Placementtest[ id=" + id + " ]";
    }
    
}
