package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.*;
import spring.model.*;
import spring.other.Interval;
import spring.other.SclassInput;

import java.util.*;

@Controller
public class ScheduleCreateController {

    @Autowired
    FlowDAO flowDAO;
    @Autowired
    GroupDAO groupDAO;
    @Autowired
    TeacherCourseDAO tcDAO;
    @Autowired
    ClassroomDAO classroomDAO;
    @Autowired
    SclassDAO sclassDAO;

    @RequestMapping(value = "/schedule_create")
    public ModelAndView main(@ModelAttribute FlowEntity input){
        ModelAndView modelAndView = new ModelAndView("schedule_create");
        FlowEntity flowEntity;
        try {
            flowEntity = flowDAO.getFlowByNumYear(input.getFlowNumber(), input.getYearOfStudy());
        } catch (javax.persistence.NoResultException | NullPointerException c) {
            modelAndView.getModelMap().addAttribute("ok", 0);
            return modelAndView;
        }

        List<SgroupEntity> groups = flowDAO.getGroups(flowEntity);
        modelAndView.getModelMap().addAttribute("flow", flowEntity);
        modelAndView.getModelMap().addAttribute("groups", groups);

        List<SclassEntity> groupsClasses = new LinkedList<>();
        for(SgroupEntity group : groups){
            groupsClasses.addAll(groupDAO.getClasses(group));
        }
        Collections.sort(groupsClasses, new Comparator<SclassEntity>() {
            @Override
            public int compare(SclassEntity o1, SclassEntity o2) {
                if (!o1.getWday().equals(o2.getWday()))
                    return o1.getWday() - o2.getWday();
                if (!o1.getPairNumber().equals(o2.getPairNumber()))
                    return o1.getPairNumber() - o2.getPairNumber();
                return ((SgroupEntity)o1.getGroups().toArray()[0]).getGroupNumber() -
                        ((SgroupEntity)o2.getGroups().toArray()[0]).getGroupNumber();
            }
        });
        modelAndView.getModelMap().addAttribute("ok", 1);
        modelAndView.getModelMap().addAttribute("groupsClasses", groupsClasses);
        modelAndView.getModelMap().addAttribute("flowClasses", flowDAO.getClasses(flowEntity));
        return modelAndView;
    }

    @RequestMapping(value = "/add_pair_form")
    public ModelAndView addPairForm(@RequestParam int wday, @RequestParam int pairNum, @RequestParam int groupId,
                                @RequestParam int flowId){
        ModelAndView modelAndView = new ModelAndView("add_pair");
        modelAndView.getModelMap().addAttribute("flowId", flowId);
        modelAndView.getModelMap().addAttribute("groupId", groupId);
        List<TeacherCourseEntity> tcs;
        List<ClassroomEntity> classrooms;
        Interval interval = new Interval();
        interval.setDay(wday);
        Set<Integer> s = new HashSet<>();
        s.add(pairNum);
        interval.setPairNums(s);
        classrooms = classroomDAO.getFreeClassrooms(interval);
        if(groupId != 0)
            tcs = tcDAO.getTcByCourseCover("group", flowDAO.getEntityById(flowId).getYearOfStudy(), wday, pairNum);
        else
            tcs = tcDAO.getTcByCourseCover("flow", flowDAO.getEntityById(flowId).getYearOfStudy(), wday, pairNum);
        SclassInput input = new SclassInput();
        input.setWday(wday);
        input.setPairNum(pairNum);
        modelAndView.getModelMap().addAttribute("tcs", tcs);
        modelAndView.getModelMap().addAttribute("classrooms", classrooms);
        modelAndView.getModelMap().addAttribute("sclassCreateInput", input);
        return modelAndView;
    }

    @RequestMapping(value = "/add_pair")
    public ModelAndView addPair(@ModelAttribute SclassInput input, @RequestParam int flowId,
                                @RequestParam int groupId){
        FlowEntity output = new FlowEntity();
        output.setFlowNumber(flowDAO.getEntityById(flowId).getFlowNumber());
        output.setYearOfStudy(flowDAO.getEntityById(flowId).getYearOfStudy());
        if(input.getClassroomId() == 0 || input.getTcId() == 0){
            return main(output);
        }
        SclassEntity sclass = new SclassEntity();
        sclass.setClassroomByClassroomId(classroomDAO.getEntityById(input.getClassroomId()));
        sclass.setTcByTcId(tcDAO.getEntityById(input.getTcId()));
        sclass.setPairNumber((short)input.getPairNum());
        sclass.setWday((short)input.getWday());
        sclassDAO.save(sclass);
        if(groupId != 0) {
            SgroupEntity group = groupDAO.getEntityById(groupId);
            group.addSclass(sclass);
            groupDAO.update(group);
        } else {
            FlowEntity flow = flowDAO.getEntityById(flowId);
            flow.addSclass(sclass);
            flowDAO.update(flow);
        }

        return main(output);
    }

    @RequestMapping(value = "/update_pair_form")
    public ModelAndView updatePairForm(@RequestParam int sclassId, @RequestParam int flowId, @RequestParam int groupId){
        ModelAndView modelAndView = new ModelAndView("update_pair");
        SclassEntity sclassEntity = sclassDAO.getEntityById(sclassId);
        SclassInput sclassInput = new SclassInput();
        sclassInput.setClassroomId((short) sclassEntity.getClassroomByClassroomId().getClassroomId());
        sclassInput.setSclassId(sclassId);
        sclassInput.setTcId((short)sclassEntity.getTcByTcId().getTcId());

        List<TeacherCourseEntity> tcs;
        List<ClassroomEntity> classrooms;
        Interval interval = new Interval();
        interval.setDay((int) sclassEntity.getWday());
        Set<Integer> s = new HashSet<>();
        s.add((int) sclassEntity.getPairNumber());
        interval.setPairNums(s);
        classrooms = classroomDAO.getFreeClassrooms(interval);
        if(groupId != 0)
            tcs = tcDAO.getTcByCourseCover("group", flowDAO.getEntityById(flowId).getYearOfStudy(),
                    sclassEntity.getWday(), sclassEntity.getPairNumber());
        else
            tcs = tcDAO.getTcByCourseCover("flow", flowDAO.getEntityById(flowId).getYearOfStudy(),
                    sclassEntity.getWday(), sclassEntity.getPairNumber());

        modelAndView.getModelMap().addAttribute("classroomSel", sclassEntity.getClassroomByClassroomId());
        modelAndView.getModelMap().addAttribute("tcSel", sclassEntity.getTcByTcId());
        modelAndView.getModelMap().addAttribute("classrooms", classrooms);
        modelAndView.getModelMap().addAttribute("tcs", tcs);
        modelAndView.getModelMap().addAttribute("sclassInput", sclassInput);
        modelAndView.getModelMap().addAttribute("flowId", flowId);
        modelAndView.getModelMap().addAttribute("groupId", groupId);
        return modelAndView;
    }

    @RequestMapping(value = "/update_pair")
    public ModelAndView updatePair(@ModelAttribute SclassInput sclassInput,
                                   @RequestParam int flowId, @RequestParam int groupId){
        SclassEntity sclass = sclassDAO.getEntityById(sclassInput.getSclassId());
        sclass.setTcByTcId(tcDAO.getEntityById(sclassInput.getTcId()));
        sclass.setClassroomByClassroomId(classroomDAO.getEntityById(sclassInput.getClassroomId()));
        sclassDAO.update(sclass);
        FlowEntity input = new FlowEntity();
        input.setFlowNumber(flowDAO.getEntityById(flowId).getFlowNumber());
        input.setYearOfStudy(flowDAO.getEntityById(flowId).getYearOfStudy());
        return main(input);
    }

    @RequestMapping(value = "/delete_pair")
    public ModelAndView deletePair(@RequestParam int sclassId, @RequestParam int flowId, @RequestParam int groupId){
        SclassEntity sclassEntity = sclassDAO.getEntityById(sclassId);
        if(groupId != 0){
            SgroupEntity sgroupEntity = groupDAO.getEntityById(groupId);
            sgroupEntity.removeSclass(sclassEntity);
            groupDAO.update(sgroupEntity);
            sclassDAO.delete(sclassEntity);
        } else {
            FlowEntity flowEntity = flowDAO.getEntityById(flowId);
            flowEntity.removeSclass(sclassEntity);
            flowDAO.update(flowEntity);
            sclassDAO.delete(sclassEntity);
        }
        FlowEntity input = new FlowEntity();
        input.setFlowNumber(flowDAO.getEntityById(flowId).getFlowNumber());
        input.setYearOfStudy(flowDAO.getEntityById(flowId).getYearOfStudy());
        return main(input);
    }

}
