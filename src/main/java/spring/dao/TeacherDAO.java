package spring.dao;

import spring.model.SclassEntity;
import spring.model.TeacherEntity;

import java.util.List;

public interface TeacherDAO {
    public List<TeacherEntity> getAll();
    public TeacherEntity getEntityById(int id);
    public void save(TeacherEntity entity);
    public void update(TeacherEntity entity);
    public void delete(TeacherEntity entity);

    public TeacherEntity getTeacherByFIO(String lastname, String firstname, String patronymic);
    public List<SclassEntity> getClasses(TeacherEntity teacher);
    public List<SclassEntity> getClasses(TeacherEntity teacher, int wday);
}
