/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.akura.util.RegexPattern;

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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {

    @Column(name = "docreation")

    private LocalDate docreation;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Invalid Username")
    private String username;
    @Column(name = "password")
    @RegexPattern(regexp = "^[a-zA-Z0-9]{3}$", message = "Invalid Password")
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Lob
    @Column(name = "description")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid Description")
    private String description;
    @Column(name = "salt")
    private String salt;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Sessionlog> sessionlogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Userrole> userroleList;
    @JoinColumn(name = "userstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Userstatus userstatusId;
    @JoinColumn(name = "employee_created_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeCreatedId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id,String username ) {
        this.id = id;
        this.username=username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @XmlTransient
    public List<Sessionlog> getSessionlogList() {
        return sessionlogList;
    }

    public void setSessionlogList(List<Sessionlog> sessionlogList) {
        this.sessionlogList = sessionlogList;
    }

    @XmlTransient
    public List<Userrole> getUserroleList() {
        return userroleList;
    }

    public void setUserroleList(List<Userrole> userroleList) {
        this.userroleList = userroleList;
    }

    public Userstatus getUserstatusId() {
        return userstatusId;
    }

    public void setUserstatusId(Userstatus userstatusId) {
        this.userstatusId = userstatusId;
    }

    public Employee getEmployeeCreatedId() {
        return employeeCreatedId;
    }

    public void setEmployeeCreatedId(Employee employeeCreatedId) {
        this.employeeCreatedId = employeeCreatedId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User[ id=" + id + " ]";
    }

    public LocalDate getDocreation() {
        return docreation;
    }

    public void setDocreation(LocalDate docreation) {
        this.docreation = docreation;
    }

}
