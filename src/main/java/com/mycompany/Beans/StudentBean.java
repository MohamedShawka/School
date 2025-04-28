package com.mycompany.Beans;

import com.mycompany.Services.ClassService;
import com.mycompany.Services.StudentService;
import com.mycompany.Entities.ClassEntity;
import com.mycompany.Entities.Student;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

@Named
@ViewScoped
public class StudentBean implements Serializable {

    @EJB
    private StudentService studentService;
    @EJB
    private ClassService classService;

    private List<Student> studentList;
    private List<ClassEntity> classList;
    private Student selectedStudent;

    @PostConstruct
    public void init() {
        studentList = studentService.findAll();
        classList = classService.findAll();
        selectedStudent = new Student();
    }

    public void save() {
        try {
            if (selectedStudent != null) {
                if (selectedStudent.getStudentName() != null && !selectedStudent.getStudentName().isEmpty()) {
                    studentService.save(selectedStudent);
                    studentList = studentService.findAll();
                    selectedStudent = new Student();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Student added successfully!"));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please enter a student name!"));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add student!"));
        }
    }

    public void delete() {
        try {
            studentService.delete(selectedStudent);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Student deleted successfully!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete student"));
            e.printStackTrace();
        }
        studentList = studentService.findAll();
        selectedStudent = new Student();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<ClassEntity> getClassList() {
        return classList;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

}
