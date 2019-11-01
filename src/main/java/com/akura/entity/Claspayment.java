/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akura.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "claspayment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Claspayment.findAll", query = "SELECT c FROM Claspayment c")})
public class Claspayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dopaid")

    private LocalDate dopaid;
    @Column(name = "paidtime")

    private LocalTime paidtime;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @JoinColumn(name = "month_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Month monthId;
    @JoinColumn(name = "studentclas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Studentclas studentclasId;

    public Claspayment() {
    }

    public Claspayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDopaid() {
        return dopaid;
    }

    public void setDopaid(LocalDate dopaid) {
        this.dopaid = dopaid;
    }

    public LocalTime getPaidtime() {
        return paidtime;
    }

    public void setPaidtime(LocalTime paidtime) {
        this.paidtime = paidtime;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Month getMonthId() {
        return monthId;
    }

    public void setMonthId(Month monthId) {
        this.monthId = monthId;
    }

    public Studentclas getStudentclasId() {
        return studentclasId;
    }

    public void setStudentclasId(Studentclas studentclasId) {
        this.studentclasId = studentclasId;
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
        if (!(object instanceof Claspayment)) {
            return false;
        }
        Claspayment other = (Claspayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Claspayment[ id=" + id + " ]";
    }
    
}
