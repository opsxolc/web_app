package spring.dao;

import spring.model.SclassEntity;
import spring.model.StudentEntity;

import java.util.List;

public interface SclassDAO {
    public List<SclassEntity> getAll();
    public SclassEntity getEntityById(int id);
    public int save(SclassEntity entity);
    public void update(SclassEntity entity);
    public void delete(SclassEntity entity);

    public List<SclassEntity> getAvailableSpeccourses(StudentEntity studentEntity);
}
