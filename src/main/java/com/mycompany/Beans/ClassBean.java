package com.mycompany.Beans;

import com.mycompany.Services.ClassService;
import com.mycompany.Entities.ClassEntity;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class ClassBean implements Serializable {

    @EJB
    private ClassService classService;

    private List<ClassEntity> classList;
    private ClassEntity selectedClass;

    @PostConstruct
    public void init() {
        this.classList = classService.findAll();
        this.selectedClass = new ClassEntity();
    }
    public void save() {
        if (selectedClass != null) {
            classService.save(selectedClass);
            classList = classService.findAll();
            selectedClass = new ClassEntity();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Class saved successfully!"));
        }
    }

    public void delete() {
        try {
            if (selectedClass != null && selectedClass.getClassID() != null) {
                classService.delete(selectedClass);
                classList = classService.findAll();
                selectedClass = new ClassEntity();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Class deleted successfully"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete class"));
            e.printStackTrace();
        }
    }

    public String openStudent() {
        return "Student.xhtml?faces-redirect=true";
    }

    public List<ClassEntity> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassEntity> classList) {
        this.classList = classList;
    }

    public ClassEntity getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(ClassEntity selectedClass) {
        this.selectedClass = selectedClass;
    }

    
}
