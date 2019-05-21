package spring.dao;

import spring.model.SclassEntity;
import spring.model.StudentEntity;

import java.util.List;

public interface StudentDAO {
    public List<StudentEntity> getAll();
    public StudentEntity getEntityById(int id);
    public void save(StudentEntity entity);
    public void update(StudentEntity entity);
    public void delete(StudentEntity entity);

    public List<SclassEntity> getSpeccourses(StudentEntity student);
    public StudentEntity getStudentByFIO(String lastname, String firstname, String patronymic);
    public List<SclassEntity> getClasses(StudentEntity student);
    public List<SclassEntity> getAllClasses(StudentEntity student);
    public List<SclassEntity> getClasses(StudentEntity student, int wday);
    public List<SclassEntity> getAllClasses(StudentEntity student, int wday);
}
