package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.*;
import spring.model.*;
import spring.other.Interval;
import spring.services.StudentService;

import javax.persistence.NoResultException;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    GroupDAOImpl groupDAO;
    @Autowired
    FlowDAOImpl flowDAO;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherCourseDAO tcDAO;
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    ClassroomDAO classroomDAO;

    @RequestMapping(value = "/")
    public ModelAndView main(){
        ModelAndView modelAndView =  new ModelAndView("index");
        modelAndView.getModelMap().addAttribute("group", new SgroupEntity());
        modelAndView.getModelMap().addAttribute("flow", new FlowEntity());
        modelAndView.getModelMap().addAttribute("course", new CourseEntity());
        modelAndView.getModelMap().addAttribute("courseList", courseDAO.getAllNames());
        modelAndView.getModelMap().addAttribute("classroom", new ClassroomEntity());
        modelAndView.getModelMap().addAttribute("interval", new Interval());
        return modelAndView;
    }

    @RequestMapping(value = "/stud_show")
    public ModelAndView studShow(@ModelAttribute SgroupEntity group, @ModelAttribute FlowEntity flow) {
        ModelAndView modelAndView = new ModelAndView("stud_show");
        List<StudentEntity> list = null;
        if (!group.equals(new SgroupEntity())) {
            try {
                list = groupDAO.getStudents(groupDAO.getGroupByNum(group.getGroupNumber()));
            } catch (NoResultException | NullPointerException e){
                modelAndView.getModelMap().addAttribute("list", list);
                return modelAndView;
            }

        }
        if (!flow.equals(new FlowEntity())) {
            try {
                list = flowDAO.getStudents(flowDAO.getFlowByNumYear(flow.getFlowNumber(), flow.getYearOfStudy()));
            } catch (NoResultException | NullPointerException e){
                modelAndView.getModelMap().addAttribute("list", list);
                return modelAndView;
            }
        }
        modelAndView.getModelMap().addAttribute("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/stud_show_all")
    public ModelAndView studShow(){
        ModelAndView modelAndView = new ModelAndView("stud_show");
        List<StudentEntity> list = studentService.getAll();
        modelAndView.getModelMap().addAttribute("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/teacher_show")
    public ModelAndView teacherShow(@ModelAttribute CourseEntity c){
        ModelAndView modelAndView = new ModelAndView("teacher_show");
        modelAndView.getModelMap().addAttribute("list", tcDAO.getTeachersByCourse(c.getCourseName()));
        modelAndView.getModelMap().addAttribute("course", c.getCourseName());
        return modelAndView;
    }

    @RequestMapping(value = "/teacher_show_all")
    public ModelAndView teacherShow(){
        ModelAndView modelAndView = new ModelAndView("teacher_show");
        modelAndView.getModelMap().addAttribute("list", teacherDAO.getAll());
        modelAndView.getModelMap().addAttribute("course", null);
        return modelAndView;
    }

    @RequestMapping(value = "/classroom_show")
    public ModelAndView classroomShow(@ModelAttribute ClassroomEntity classroom, @ModelAttribute Interval interval){
        ModelAndView modelAndView = new ModelAndView("classroom_show");
        interval.setPairNums(interval.getPairNumsStr());
        modelAndView.getModelMap().addAttribute("list", classroomDAO.getFreeClassrooms(interval));
        return modelAndView;
    }

}

