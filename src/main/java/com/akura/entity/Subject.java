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
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id; @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Subject Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Subjectmedium> subjectmediumList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Gradesubject> gradesubjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Clas> clasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Teachersubject> teachersubjectList;

    public Subject() {
    }

    public Subject(Integer id,String name) {
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
    public List<Subjectmedium> getSubjectmediumList() {
        return subjectmediumList;
    }

    public void setSubjectmediumList(List<Subjectmedium> subjectmediumList) {
        this.subjectmediumList = subjectmediumList;
    }

    @XmlTransient
    public List<Gradesubject> getGradesubjectList() {
        return gradesubjectList;
    }

    public void setGradesubjectList(List<Gradesubject> gradesubjectList) {
        this.gradesubjectList = gradesubjectList;
    }

    @XmlTransient
    public List<Clas> getClasList() {
        return clasList;
    }

    public void setClasList(List<Clas> clasList) {
        this.clasList = clasList;
    }

    @XmlTransient
    public List<Teachersubject> getTeachersubjectList() {
        return teachersubjectList;
    }

    public void setTeachersubjectList(List<Teachersubject> teachersubjectList) {
        this.teachersubjectList = teachersubjectList;
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
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Subject[ id=" + id + " ]";
    }
    
}
