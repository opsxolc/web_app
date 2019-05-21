package spring.dao;

import spring.model.CourseEntity;
import spring.model.TeacherCourseEntity;
import spring.model.TeacherEntity;

import java.util.List;

public interface TeacherCourseDAO {
    public List<TeacherCourseEntity> getAll();
    public TeacherCourseEntity getEntityById(int id);
    public void save(TeacherCourseEntity entity);
    public void update(TeacherCourseEntity entity);
    public void delete(TeacherCourseEntity entity);

    public List<TeacherCourseEntity> getTCsByYear(int year);
    public List<TeacherEntity> getTeachersByCourse(CourseEntity course);
    public List<TeacherEntity> getTeachersByCourse(String courseName);
    public List<CourseEntity> getCoursesByTeacher(TeacherEntity teacher);

    public List<TeacherCourseEntity> getTcByCourseCover(String cover, int wday, int pairNum);
    public List<TeacherCourseEntity> getTcByCourseCover(String cover, int yearOfStudy, int wday, int pairNum);
    public List<TeacherCourseEntity> getTCsByCourseName(String courseName);
    public TeacherCourseEntity getTcByTeacherCourse(TeacherEntity teacherEntity, CourseEntity courseEntity);
    public TeacherCourseEntity getTcByTeacherCourse(TeacherEntity teacherEntity, CourseEntity courseEntity, int year);
}
