package spring.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.CourseDAO;
import spring.dao.TeacherCourseDAO;
import spring.dao.TeacherDAO;
import spring.model.CourseEntity;
import spring.model.TeacherCourseEntity;
import spring.other.SclassInput;
import spring.other.TCInput;

import javax.persistence.PersistenceException;

@Controller
public class CoursesController {

    @Autowired
    CourseDAO courseDAO;
    @Autowired
    TeacherCourseDAO tcDAO;
    @Autowired
    TeacherDAO teacherDAO;

    @RequestMapping(value = "/update_courses_show")
    public ModelAndView updateCoursesShow(){
        ModelAndView modelAndView = new ModelAndView("update_courses_show");
        modelAndView.getModelMap().addAttribute("courses", courseDAO.getAll());
        modelAndView.getModelMap().addAttribute("tcs", tcDAO.getAll());
        modelAndView.getModelMap().addAttribute("sclass", new SclassInput());
        return modelAndView;
    }

    @RequestMapping(value = "/update_tc_form")
    public ModelAndView updateTCForm(@RequestParam int tcId){
        ModelAndView modelAndView = new ModelAndView("update_tc_form");
        TeacherCourseEntity tc = tcDAO.getEntityById(tcId);
        TCInput tcInput = new TCInput();
        tcInput.setTcId(tcId);
        tcInput.setCourseId(tc.getCourseByCourseId().getCourseId());
        tcInput.setTeacherId(tc.getTeacherByTeacherId().getTeacherId());
        tcInput.setYear(tc.getYear());
        modelAndView.getModelMap().addAttribute("tc", tcInput);
        modelAndView.getModelMap().addAttribute("courses", courseDAO.getAll());
        modelAndView.getModelMap().addAttribute("teachers", teacherDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update_tc")
    public ModelAndView updateTC(@ModelAttribute TCInput tcInput){
        if(tcInput.getYear() < 1900)
            return new ModelAndView("redirect:/update_courses_show");
        TeacherCourseEntity tc = tcDAO.getEntityById(tcInput.getTcId());
        tc.setCourseByCourseId(courseDAO.getEntityById(tcInput.getCourseId()));
        tc.setTeacherByTeacherId(teacherDAO.getEntityById(tcInput.getTeacherId()));
        tc.setYear((short)tcInput.getYear());
        try {
            tcDAO.update(tc);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_courses_show");
        }
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/add_tc_form")
    public ModelAndView addTCForm(){
        ModelAndView  modelAndView = new ModelAndView("add_tc_form");
        modelAndView.getModelMap().addAttribute("tc", new TCInput());
        modelAndView.getModelMap().addAttribute("courses", courseDAO.getAll());
        modelAndView.getModelMap().addAttribute("teachers", teacherDAO.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add_tc")
    public ModelAndView addTC(@ModelAttribute TCInput tcInput){
        if(tcInput.getYear() < 1900)
            return new ModelAndView("redirect:/update_courses_show");
        TeacherCourseEntity tc = new TeacherCourseEntity();
        tc.setYear((short) tcInput.getYear());
        tc.setCourseByCourseId(courseDAO.getEntityById(tcInput.getCourseId()));
        tc.setTeacherByTeacherId(teacherDAO.getEntityById(tcInput.getTeacherId()));
        try {
            tcDAO.save(tc);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_courses_show");
        }
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/delete_tc")
    public ModelAndView deleteTC(@RequestParam int tcId){
        TeacherCourseEntity tc = tcDAO.getEntityById(tcId);
        tcDAO.delete(tc);
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/add_course_form")
    public ModelAndView addCourseForm(){
        ModelAndView modelAndView = new ModelAndView("add_course_form");
        modelAndView.getModelMap().addAttribute("course", new CourseEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add_course")
    public ModelAndView addCourse(@ModelAttribute CourseEntity courseInput){
        if (courseInput.getCourseName().equals(""))
            return new ModelAndView("redirect:/update_courses_show");
        try {
            courseDAO.save(courseInput);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_courses_show");
        }
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/update_course_form")
    public ModelAndView updateCourseForm(@RequestParam int courseId){
        ModelAndView modelAndView = new ModelAndView("update_course_form");
        modelAndView.getModelMap().addAttribute("course", courseDAO.getEntityById(courseId));
        return modelAndView;
    }

    @RequestMapping(value = "/update_course")
    public ModelAndView updateCourse(@ModelAttribute CourseEntity courseInput){
        if (courseInput.getCourseName().equals(""))
            return new ModelAndView("redirect:/update_courses_show");
        try {
            courseDAO.update(courseInput);
        } catch (PersistenceException e) {
            return new ModelAndView("redirect:/update_courses_show");
        }
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/delete_course")
    public ModelAndView deleteCourse(@RequestParam int courseId){
        CourseEntity course = courseDAO.getEntityById(courseId);
        courseDAO.delete(course);
        return new ModelAndView("redirect:/update_courses_show");
    }

}
