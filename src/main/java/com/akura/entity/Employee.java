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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Number")
    private String number;
    @Column(name = "fullname")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Fullname")
    private String fullname;
    @Column(name = "callingname")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Calligname")
    private String callingname;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "dobirth")

    private LocalDate dobirth;
    @Column(name = "nic")
    @Pattern(regexp = "^(([\\d]{9}[vVxX])|([\\d]{12}))$", message = "Invalid NIC")
    private String nic;
    @Lob
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address")
    private String address;
    @Column(name = "mobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobilephone Number")
    private String mobile;
    @Column(name = "land")
    @Pattern(regexp = "^(((0\\d{9})){0,1})$", message = "Invalid Landphone Number")
    private String land;
    @Lob
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;
    @Column(name = "doassignment")

    private LocalDate doassignment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexam> semesterexamList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Placementtestpayment> placementtestpaymentList;
    @JoinColumn(name = "civilstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Civilstatus civilstatusId;
    @JoinColumn(name = "designation_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Designation designationId;
    @JoinColumn(name = "employeestatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employeestatus employeestatusId;
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Gender genderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Studentclas> studentclasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Result> resultList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Operationlog> operationlogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
   @JsonIgnore
    private List<Claspayment> claspaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Placementtest> placementtestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Semesterexampayment> semesterexampaymentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeTeacherId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Clas> clasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Clas> clasList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeTeacherId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Teachersubject> teachersubjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeCreatedId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> userList1;

    public Employee() {
    }

    public Employee(Integer id,String callingname) {
        this.id = id;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDate getDobirth() {
        return dobirth;
    }

    public void setDobirth(LocalDate dobirth) {
        this.dobirth = dobirth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDoassignment() {
        return doassignment;
    }

    public void setDoassignment(LocalDate doassignment) {
        this.doassignment = doassignment;
    }

    @XmlTransient
    public List<Semesterexam> getSemesterexamList() {
        return semesterexamList;
    }

    public void setSemesterexamList(List<Semesterexam> semesterexamList) {
        this.semesterexamList = semesterexamList;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Placementtestpayment> getPlacementtestpaymentList() {
        return placementtestpaymentList;
    }

    public void setPlacementtestpaymentList(List<Placementtestpayment> placementtestpaymentList) {
        this.placementtestpaymentList = placementtestpaymentList;
    }

    public Civilstatus getCivilstatusId() {
        return civilstatusId;
    }

    public void setCivilstatusId(Civilstatus civilstatusId) {
        this.civilstatusId = civilstatusId;
    }

    public Designation getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Designation designationId) {
        this.designationId = designationId;
    }

    public Employeestatus getEmployeestatusId() {
        return employeestatusId;
    }

    public void setEmployeestatusId(Employeestatus employeestatusId) {
        this.employeestatusId = employeestatusId;
    }

    public Gender getGenderId() {
        return genderId;
    }

    public void setGenderId(Gender genderId) {
        this.genderId = genderId;
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
    public List<Operationlog> getOperationlogList() {
        return operationlogList;
    }

    public void setOperationlogList(List<Operationlog> operationlogList) {
        this.operationlogList = operationlogList;
    }

    @XmlTransient
    public List<Claspayment> getClaspaymentList() {
        return claspaymentList;
    }

    public void setClaspaymentList(List<Claspayment> claspaymentList) {
        this.claspaymentList = claspaymentList;
    }

    @XmlTransient
    public List<Placementtest> getPlacementtestList() {
        return placementtestList;
    }

    public void setPlacementtestList(List<Placementtest> placementtestList) {
        this.placementtestList = placementtestList;
    }

    @XmlTransient
    public List<Semesterexampayment> getSemesterexampaymentList() {
        return semesterexampaymentList;
    }

    public void setSemesterexampaymentList(List<Semesterexampayment> semesterexampaymentList) {
        this.semesterexampaymentList = semesterexampaymentList;
    }

    @XmlTransient
    public List<Clas> getClasList() {
        return clasList;
    }

    public void setClasList(List<Clas> clasList) {
        this.clasList = clasList;
    }

    @XmlTransient
    public List<Clas> getClasList1() {
        return clasList1;
    }

    public void setClasList1(List<Clas> clasList1) {
        this.clasList1 = clasList1;
    }

    @XmlTransient
    public List<Teachersubject> getTeachersubjectList() {
        return teachersubjectList;
    }

    public void setTeachersubjectList(List<Teachersubject> teachersubjectList) {
        this.teachersubjectList = teachersubjectList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<User> getUserList1() {
        return userList1;
    }

    public void setUserList1(List<User> userList1) {
        this.userList1 = userList1;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akura.entity.Employee[ id=" + id + " ]";
    }
    
}
