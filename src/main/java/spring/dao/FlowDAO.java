package spring.dao;

import spring.model.FlowEntity;
import spring.model.SclassEntity;
import spring.model.SgroupEntity;
import spring.model.StudentEntity;

import java.util.List;

public interface FlowDAO {
    public List<FlowEntity> getAll();
    public FlowEntity getEntityById(int id);
    public void save(FlowEntity entity);
    public void update(FlowEntity entity);
    public void delete(FlowEntity entity);

    public List<SgroupEntity> getGroups(FlowEntity flow);
    public FlowEntity getFlowByNumYear(int number, int year);
    public List<StudentEntity> getStudents(FlowEntity flow);
    public List<SclassEntity> getClasses(FlowEntity flow);
    public List<SclassEntity> getClasses(FlowEntity flow, int wday);
}
