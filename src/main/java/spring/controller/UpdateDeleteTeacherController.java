package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.TeacherDAO;
import spring.model.TeacherEntity;

import javax.persistence.PersistenceException;

@Controller
public class UpdateDeleteTeacherController {

    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping(value = "/update_teacher_show")
    public ModelAndView updateTeacherShow(){
        ModelAndView modelAndView = new ModelAndView("update_teacher_show");
        modelAndView.getModelMap().addAttribute("list", teacherDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update_teacher_form")
    public ModelAndView updateTeacherForm(@RequestParam int teacherId){
        ModelAndView modelAndView = new ModelAndView("update_teacher_form");
        modelAndView.getModelMap().addAttribute("teacher", teacherDAO.getEntityById(teacherId));
        return modelAndView;
    }

    @RequestMapping(value = "/update_teacher")
    public ModelAndView updateTeacher(@ModelAttribute TeacherEntity teacherInput){
        TeacherEntity teacher = teacherDAO.getEntityById(teacherInput.getTeacherId());
        if(teacherInput.getFirstname().equals("") || teacherInput.getLastname().equals("")
                || teacherInput.getPatronymic().equals(""))
            return new ModelAndView("redirect:/update_teacher_show");
        teacher.setFirstname(teacherInput.getFirstname());
        teacher.setLastname(teacherInput.getLastname());
        teacher.setPatronymic(teacherInput.getPatronymic());
        try {
            teacherDAO.update(teacher);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_teacher_show");
        }
        return new ModelAndView("redirect:/update_teacher_show");
    }

    @RequestMapping(value = "/delete_teacher")
    public ModelAndView deleteTeacher(@RequestParam int teacherId){
        TeacherEntity teacher = teacherDAO.getEntityById(teacherId);
        teacherDAO.delete(teacher);
        return new ModelAndView("redirect:/update_teacher_show");
    }

    @RequestMapping(value = "/add_teacher_form")
    public ModelAndView addTeacherForm(){
        ModelAndView modelAndView = new ModelAndView("add_teacher_form");
        modelAndView.getModelMap().addAttribute("teacher", new TeacherEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add_teacher")
    public ModelAndView addTeacher(@ModelAttribute TeacherEntity teacher){
        if(teacher.getFirstname().equals("") || teacher.getLastname().equals("")
                || teacher.getPatronymic().equals(""))
            return new ModelAndView("redirect:/update_teacher_show");
        try {
            teacherDAO.save(teacher);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_teacher_show");
        }
        return new ModelAndView("redirect:/update_teacher_show");
    }

}
