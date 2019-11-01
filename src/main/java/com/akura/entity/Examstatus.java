/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "examstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examstatus.findAll", query = "SELECT e FROM Examstatus e")})
public class Examstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examstatusId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexam> semesterexamList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examstatusId", fetch = FetchType.LAZY)
    private List<Placementtest> placementtestList;

    public Examstatus() {
    }

    public Examstatus(Integer id,String name) {
        this.id = id;
        this.name=name;
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

    @XmlTransient
    public List<Semesterexam> getSemesterexamList() {
        return semesterexamList;
    }

    public void setSemesterexamList(List<Semesterexam> semesterexamList) {
        this.semesterexamList = semesterexamList;
    }

    @XmlTransient
    public List<Placementtest> getPlacementtestList() {
        return placementtestList;
    }

    public void setPlacementtestList(List<Placementtest> placementtestList) {
        this.placementtestList = placementtestList;
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
        if (!(object instanceof Examstatus)) {
            return false;
        }
        Examstatus other = (Examstatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Examstatus[ id=" + id + " ]";
    }
    
}
