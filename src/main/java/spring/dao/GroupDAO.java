package spring.dao;

import spring.model.SclassEntity;
import spring.model.SgroupEntity;
import spring.model.StudentEntity;

import java.util.List;

public interface GroupDAO {
    public List<SgroupEntity> getAll();
    public SgroupEntity getEntityById(int id);
    public void save(SgroupEntity entity);
    public void update(SgroupEntity entity);
    public void delete(SgroupEntity entity);

    public SgroupEntity getGroupByNum(int number);
    public List<StudentEntity> getStudents(SgroupEntity sgroup);
    public List<SclassEntity> getClasses(SgroupEntity sgroup);
    public List<SclassEntity> getClasses(SgroupEntity sgroup, int wday);
}
