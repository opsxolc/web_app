package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.GroupDAO;
import spring.model.StudentEntity;
import spring.other.UpdateDeleteInput;
import spring.services.StudentService;

import javax.persistence.PersistenceException;

@Controller
public class UpdateDeleteStudController {

    @Autowired
    StudentService studentService;
    @Autowired
    GroupDAO groupDAO;

    @RequestMapping(value = "/update_stud_form")
    public ModelAndView updateStudForm(@RequestParam int studId){
        ModelAndView modelAndView = new ModelAndView("update_stud_form");
        StudentEntity student = studentService.getEntityById(studId);
        modelAndView.getModelMap().addAttribute("udInput", new UpdateDeleteInput(student));
        modelAndView.getModelMap().addAttribute("groups", groupDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update_stud_show")
    public ModelAndView updateStudShow(){
        ModelAndView modelAndView = new ModelAndView("update_stud_show");
        modelAndView.getModelMap().addAttribute("list", studentService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update_stud")
    public ModelAndView updateStud(@ModelAttribute UpdateDeleteInput udInput){
        if(udInput.getFirstname().equals("") || udInput.getLastname().equals("") || udInput.getPatronymic().equals(""))
            return new ModelAndView("redirect:/update_stud_show");
        StudentEntity student = studentService.getEntityById(udInput.getStudentId());
        student.setFirstname(udInput.getFirstname());
        student.setLastname(udInput.getLastname());
        student.setPatronymic(udInput.getPatronymic());
        student.setSgroupBySgroupId(groupDAO.getEntityById(udInput.getGroupId()));
        try {
            studentService.update(student);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_stud_show");
        }
        return new ModelAndView("redirect:/update_stud_show");
    }

    @RequestMapping(value = "/delete_stud")
    public ModelAndView deleteStud(@RequestParam int studId){
        studentService.delete(studentService.getEntityById(studId));
        return new ModelAndView("redirect:/update_stud_show");
    }

    @RequestMapping(value = "/add_stud_form")
    public ModelAndView addStudForm(){
        ModelAndView modelAndView = new ModelAndView("add_stud_form");
        modelAndView.getModelMap().addAttribute("udInput", new UpdateDeleteInput());
        modelAndView.getModelMap().addAttribute("groups", groupDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add_stud")
    public ModelAndView addStud(@ModelAttribute UpdateDeleteInput udInput){
        if(udInput.getFirstname().equals("") || udInput.getLastname().equals("") || udInput.getPatronymic().equals(""))
            return new ModelAndView("redirect:/update_stud_show");
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setSgroupBySgroupId(groupDAO.getEntityById(udInput.getGroupId()));
        studentEntity.setPatronymic(udInput.getPatronymic());
        studentEntity.setLastname(udInput.getLastname());
        studentEntity.setFirstname(udInput.getFirstname());
        try {
            studentService.save(studentEntity);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_stud_show");
        }
        return new ModelAndView("redirect:/update_stud_show");
    }

}
