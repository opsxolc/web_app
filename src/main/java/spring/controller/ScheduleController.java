package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.ClassroomDAO;
import spring.dao.TeacherDAO;
import spring.dao.TeacherDAOImpl;
import spring.model.ClassroomEntity;
import spring.model.SclassEntity;
import spring.model.StudentEntity;
import spring.model.TeacherEntity;
import spring.other.Interval;
import spring.other.ScheduleInput;
import spring.services.StudentService;

import java.util.*;

@Controller
public class ScheduleController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    ClassroomDAO classroomDAO;

    @RequestMapping(value = "/schedule_form")
    public ModelAndView scheduleForm(){
        ModelAndView modelAndView = new ModelAndView("schedule_form");
        modelAndView.getModelMap().addAttribute("scheduleInput", new ScheduleInput());
        return modelAndView;
    }

    private void sortClasses(List<SclassEntity> list){
        if (list.isEmpty()) return;
        Collections.sort(list, new Comparator<SclassEntity>() {
            @Override
            public int compare(SclassEntity o1, SclassEntity o2) {
                if (o1.getPairNumber().equals(o2.getPairNumber()))
                    return o1.getWday() - o2.getWday();
                return o1.getPairNumber() - o2.getPairNumber();
            }
        });
    }

    @RequestMapping(value = "/schedule_show_stud")
    public ModelAndView showSchedStud(@ModelAttribute ScheduleInput scheduleInput){
        ModelAndView modelAndView = new ModelAndView("schedule_show");
        StudentEntity studentEntity;
        try {
             studentEntity = studentService.getStudentByFIO(scheduleInput.getLastname(),
                    scheduleInput.getFirstname(), scheduleInput.getPatronymic());
        } catch (javax.persistence.NoResultException c){
            modelAndView.getModelMap().addAttribute("list", null);
            modelAndView.getModelMap().addAttribute("days", null);
            return modelAndView;
        }
        scheduleInput.setDays();
        ArrayList<Integer> days;
        List<SclassEntity> list = new LinkedList<>();
        if (scheduleInput.getDays().size() != 0) {
            for (Integer day : scheduleInput.getDays()) {
                list.addAll(studentService.getAllClasses(studentEntity, day));
            }
            sortClasses(list);
            days = new ArrayList<>(scheduleInput.getDays());
            Collections.sort(days);
        } else {
            list = studentService.getAllClasses(studentEntity);
            days = new ArrayList<>();
            for(int i = 1; i < 7; ++i)
                days.add(i);
        }
        modelAndView.getModelMap().addAttribute("list", list);
        modelAndView.getModelMap().addAttribute("days", days);
        return modelAndView;
    }

    @RequestMapping(value = "/schedule_show_teacher")
    public ModelAndView showSchedTeacher(@ModelAttribute ScheduleInput scheduleInput){
        ModelAndView modelAndView = new ModelAndView("schedule_show");
        TeacherEntity teacherEntity;
        try {
            teacherEntity = teacherDAO.getTeacherByFIO(scheduleInput.getLastname(),
                    scheduleInput.getFirstname(), scheduleInput.getPatronymic());
        } catch (javax.persistence.NoResultException c){
            modelAndView.getModelMap().addAttribute("list", null);
            modelAndView.getModelMap().addAttribute("days", null);
            return modelAndView;
        }
        scheduleInput.setDays();
        ArrayList<Integer> days;
        List<SclassEntity> list = new LinkedList<>();
        if (scheduleInput.getDays().size() != 0) {
            for (Integer day : scheduleInput.getDays()) {
                list.addAll(teacherDAO.getClasses(teacherEntity, day));
            }
            sortClasses(list);
            days = new ArrayList<>(scheduleInput.getDays());
            Collections.sort(days);
        } else {
            list = teacherDAO.getClasses(teacherEntity);
            days = new ArrayList<>();
            for(int i = 1; i < 7; ++i)
                days.add(i);
        }
        modelAndView.getModelMap().addAttribute("list", list);
        modelAndView.getModelMap().addAttribute("days", days);
        return modelAndView;
    }

    @RequestMapping(value = "/schedule_show_classroom")
    public ModelAndView showSchedClass(@ModelAttribute ScheduleInput scheduleInput){
        ModelAndView modelAndView = new ModelAndView("schedule_show");
        ClassroomEntity classroomEntity;
        try {
            classroomEntity = classroomDAO.getClassroomByName(scheduleInput.getClassroom());
        } catch (javax.persistence.NoResultException c){
            modelAndView.getModelMap().addAttribute("list", null);
            modelAndView.getModelMap().addAttribute("days", null);
            return modelAndView;
        }
        scheduleInput.setDays();
        ArrayList<Integer> days;
        List<SclassEntity> list = new LinkedList<>();
        if (scheduleInput.getDays().size() != 0) {
            for (Integer day : scheduleInput.getDays()) {
                list.addAll(classroomDAO.getClasses(classroomEntity, day));
            }
            sortClasses(list);
            days = new ArrayList<>(scheduleInput.getDays());
            Collections.sort(days);
        } else {
            list = classroomDAO.getClasses(classroomEntity);
            sortClasses(list);
            days = new ArrayList<>();
            for(int i = 1; i < 7; ++i)
                days.add(i);
        }
        modelAndView.getModelMap().addAttribute("list", list);
        modelAndView.getModelMap().addAttribute("days", days);
        return modelAndView;
    }

}
