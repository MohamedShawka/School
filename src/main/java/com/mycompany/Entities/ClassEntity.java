package com.mycompany.Entities;

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


@Entity
@Table(name = "ClassEntity")
@NamedQueries({
    @NamedQuery(name = "ClassEntity.findAll", query = "SELECT c FROM ClassEntity c"),
    @NamedQuery(name = "ClassEntity.findByClassID", query = "SELECT c FROM ClassEntity c WHERE c.classID = :classID"),
    @NamedQuery(name = "ClassEntity.findByClassName", query = "SELECT c FROM ClassEntity c WHERE c.className = :className")
})
public class ClassEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassID")
    private Integer classID;
    @Basic(optional = false)
    @Column(name = "ClassName")
    private String className;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classEntity", fetch = FetchType.LAZY)
    private List<Student> studentList;

    public ClassEntity() { }

    public ClassEntity(Integer classID) {
        this.classID = classID;
    }

    public ClassEntity(Integer classID, String className) {
        this.classID = classID;
        this.className = className;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
