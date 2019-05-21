package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import spring.dao.StudentDAO;
import spring.model.SclassEntity;
import spring.model.StudentEntity;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDAO dao;

    @Override
    public List<SclassEntity> getSpeccourses(StudentEntity student) {
        return dao.getSpeccourses(student);
    }

    @Override
    public List<StudentEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public StudentEntity getEntityById(int id) {
        return dao.getEntityById(id);
    }

    @Override
    public void save(StudentEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(StudentEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(StudentEntity entity) {
        dao.delete(entity);
    }

    @Override
    public StudentEntity getStudentByFIO(String lastname, String firstname, String patronymic) {
        return dao.getStudentByFIO(lastname, firstname, patronymic);
    }

    @Override
    public List<SclassEntity> getClasses(StudentEntity student) {
        return dao.getClasses(student);
    }

    @Override
    public List<SclassEntity> getClasses(StudentEntity student, int wday) {
        return dao.getClasses(student, wday);
    }

    @Override
    public List<SclassEntity> getAllClasses(StudentEntity student) {
        return dao.getAllClasses(student);
    }

    @Override
    public List<SclassEntity> getAllClasses(StudentEntity student, int wday) {
        return dao.getAllClasses(student, wday);
    }
}
