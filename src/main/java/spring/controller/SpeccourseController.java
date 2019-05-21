package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.ClassroomDAO;
import spring.dao.SclassDAO;
import spring.dao.TeacherCourseDAO;
import spring.model.ClassroomEntity;
import spring.model.SclassEntity;
import spring.model.StudentEntity;
import spring.model.TeacherCourseEntity;
import spring.other.Interval;
import spring.other.SclassInput;
import spring.services.StudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SpeccourseController {

    @Autowired
    TeacherCourseDAO tcDAO;
    @Autowired
    ClassroomDAO classroomDAO;
    @Autowired
    SclassDAO sclassDAO;
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/add_speccourse_form")
    public ModelAndView addSpecForm(@ModelAttribute SclassInput sclassInput){
        ModelAndView modelAndView = new ModelAndView("add_speccourse_form");
        modelAndView.getModelMap().addAttribute("sclass", sclassInput);
        List<TeacherCourseEntity> tcs;
        List<ClassroomEntity> classrooms;
        Interval interval = new Interval();
        interval.setDay(sclassInput.getWday());
        Set<Integer> s = new HashSet<>();
        s.add(sclassInput.getPairNum());
        interval.setPairNums(s);
        classrooms = classroomDAO.getFreeClassrooms(interval);
        tcs = tcDAO.getTcByCourseCover("student", sclassInput.getWday(), sclassInput.getPairNum());
        SclassInput input = new SclassInput();
        input.setWday(sclassInput.getWday());
        input.setPairNum(sclassInput.getPairNum());
        modelAndView.getModelMap().addAttribute("tcs", tcs);
        modelAndView.getModelMap().addAttribute("classrooms", classrooms);
        return modelAndView;
    }

    @RequestMapping(value = "/add_speccourse")
    public ModelAndView addSpec(@ModelAttribute SclassInput sclassInput) {
        SclassEntity sclass = new SclassEntity();
        sclass.setPairNumber((short) sclassInput.getPairNum());
        sclass.setWday((short) sclassInput.getWday());
        sclass.setTcByTcId(tcDAO.getEntityById(sclassInput.getTcId()));
        sclass.setClassroomByClassroomId(classroomDAO.getEntityById(sclassInput.getClassroomId()));
        sclassDAO.save(sclass);
        return new ModelAndView("redirect:/update_courses_show");
    }

    @RequestMapping(value = "/add_spec_stud_show")
    public ModelAndView addSpecStudShow(@RequestParam int studId){
        ModelAndView modelAndView = new ModelAndView("add_spec_stud_show");
        StudentEntity student = studentService.getEntityById(studId);
        modelAndView.getModelMap().addAttribute("speccourses", sclassDAO.getAvailableSpeccourses(student));
        modelAndView.getModelMap().addAttribute("studId", studId);
        modelAndView.getModelMap().addAttribute("speccoursesStud", studentService.getSpeccourses(student));
        return modelAndView;
    }

    @RequestMapping(value = "/add_spec_stud")
    public ModelAndView addSpecStud(@RequestParam int studId, @RequestParam int specId){
        StudentEntity student = studentService.getEntityById(studId);
        SclassEntity sclass = sclassDAO.getEntityById(specId);
        student.addSclass(sclass);
        studentService.update(student);
        return new ModelAndView("redirect:/update_stud_show");
    }

    @RequestMapping(value = "/delete_spec_stud")
    public ModelAndView deleteSpecStud(@RequestParam int studId, @RequestParam int specId){
        StudentEntity student = studentService.getEntityById(studId);
        SclassEntity sclass = sclassDAO.getEntityById(specId);
        student.removeSclass(sclass);
        studentService.update(student);
        return new ModelAndView("redirect:/update_stud_show");
    }

}
