package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.CourseEntity;
import spring.model.TeacherCourseEntity;
import spring.model.TeacherEntity;

import java.util.Calendar;
import java.util.List;

public class TeacherCourseDAOImpl implements TeacherCourseDAO {

    @Autowired
    SessionFactory factory;

    public List<TeacherCourseEntity> getAll(){
        Session session = factory.openSession();
        List<TeacherCourseEntity> list = (List<TeacherCourseEntity>) session
                .createQuery("from TeacherCourseEntity TC " +
                        "order by TC.year desc, TC.teacherByTeacherId.lastname, TC.teacherByTeacherId.firstname, " +
                        "TC.teacherByTeacherId.patronymic, TC.courseByCourseId.courseName").list();
        session.close();
        return list;
    }

    public TeacherCourseEntity getEntityById(int id){
        Session session = factory.openSession();
        TeacherCourseEntity entity = session.get(TeacherCourseEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(TeacherCourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(TeacherCourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(TeacherCourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    public List<TeacherCourseEntity> getTCsByYear(int year){
        Session session = factory.openSession();
        Query query = session.createQuery("from TeacherCourseEntity TC where TC.year = :year");
        query.setParameter("year", (short)year);
        List<TeacherCourseEntity> teacherCourseEntities = query.list();
        session.close();
        return teacherCourseEntities;
    }

    public List<TeacherEntity> getTeachersByCourse(CourseEntity course){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC.teacherByTeacherId " +
                "from TeacherCourseEntity TC " +
                "where TC.courseByCourseId = :course");
        query.setParameter("course", course);
        List<TeacherEntity> teachers = (List<TeacherEntity>) query.list();
        session.close();
        return teachers;
    }

    public List<TeacherEntity> getTeachersByCourse(String courseName){
        Session session = factory.openSession();
        Query query = session.createQuery("select distinct T from TeacherEntity T " +
                "where T in ( select T " +
                "from TeacherEntity T inner join TeacherCourseEntity TC on TC.teacherByTeacherId = T " +
                "where TC.courseByCourseId.courseName = :courseName " +
                "order by T.lastname, T.firstname, " +
                "T.patronymic )");
        query.setParameter("courseName", courseName);
        List<TeacherEntity> teachers = (List<TeacherEntity>) query.list();
        session.close();
        return teachers;
    }

    public List<TeacherCourseEntity> getTCsByCourseName(String courseName){
        Session session = factory.openSession();
        Query query = session.createQuery("from TeacherCourseEntity TC " +
                "where TC.courseByCourseId.courseName = :courseName " +
                "order by TC.year desc ");
        query.setParameter("courseName", courseName);
        List<TeacherCourseEntity> tcs = (List<TeacherCourseEntity>) query.list();
        session.close();
        return tcs;
    }

    public List<CourseEntity> getCoursesByTeacher(TeacherEntity teacher){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC.courseByCourseId " +
                "from TeacherCourseEntity TC " +
                "where TC.teacherByTeacherId = :teacher");
        query.setParameter("teacher", teacher);
        List<CourseEntity> teachers = (List<CourseEntity>) query.list();
        session.close();
        return teachers;
    }

    public TeacherCourseEntity getTcByTeacherCourse(TeacherEntity teacherEntity, CourseEntity courseEntity){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC from TeacherCourseEntity TC where " +
                "TC.teacherByTeacherId = :teacher and TC.courseByCourseId = :course and TC.year = :year");
        Calendar instance = Calendar.getInstance();
        Integer year = instance.getWeekYear();
        int month = instance.get(Calendar.MONTH);
        if (month < 9) --year;
        query.setParameter("year", year.shortValue());
        return getTeacherCourseEntity(teacherEntity, courseEntity, session, query);
    }

    public TeacherCourseEntity getTcByTeacherCourse(TeacherEntity teacherEntity, CourseEntity courseEntity, int year){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC from TeacherCourseEntity TC where " +
                "TC.teacherByTeacherId = :teacher and TC.courseByCourseId = :course and TC.year = :year");
        query.setParameter("year", (short)year);
        return getTeacherCourseEntity(teacherEntity, courseEntity, session, query);
    }

    private TeacherCourseEntity getTeacherCourseEntity(TeacherEntity teacherEntity,
                                                       CourseEntity courseEntity, Session session, Query query) {
        query.setParameter("teacher", teacherEntity);
        query.setParameter("course", courseEntity);
        TeacherCourseEntity teacherCourseEntity = (TeacherCourseEntity) query.getSingleResult();
        session.close();
        return teacherCourseEntity;
    }

    public List<TeacherCourseEntity> getTcByCourseCover(String cover, int yearOfStudy, int wday, int pairNum){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC from TeacherCourseEntity TC " +
                "where TC.courseByCourseId.cover = :cover and (TC.courseByCourseId.yearOfStudy = :year " +
                "or TC.courseByCourseId.yearOfStudy is null) and TC.teacherByTeacherId not in " +
                "(select T from TeacherEntity T inner join TeacherCourseEntity TC  on TC.teacherByTeacherId = T " +
                "inner join SclassEntity C on C.tcByTcId = TC where C.wday = " + wday + " and C.pairNumber = " +
                pairNum + ")" +
                "order by TC.courseByCourseId.courseName, TC.teacherByTeacherId.lastname, " +
                "TC.teacherByTeacherId.firstname, TC.teacherByTeacherId.patronymic");
        query.setParameter("cover", cover);
        query.setParameter("year", (short) yearOfStudy);
        List<TeacherCourseEntity> teachers = (List<TeacherCourseEntity>) query.list();
        session.close();
        return teachers;
    }

    public List<TeacherCourseEntity> getTcByCourseCover(String cover, int wday, int pairNum){
        Session session = factory.openSession();
        Query query = session.createQuery("select TC from TeacherCourseEntity TC " +
                "where TC.courseByCourseId.cover = :cover and " +
                "TC.teacherByTeacherId not in " +
                "(select T from TeacherEntity T inner join TeacherCourseEntity TC  on TC.teacherByTeacherId = T " +
                "inner join SclassEntity C on C.tcByTcId = TC where C.wday = " + wday + " and C.pairNumber = " +
                pairNum + ")" +
                "order by TC.courseByCourseId.courseName, TC.teacherByTeacherId.lastname, " +
                "TC.teacherByTeacherId.firstname, TC.teacherByTeacherId.patronymic");
        query.setParameter("cover", cover);
        List<TeacherCourseEntity> teachers = (List<TeacherCourseEntity>) query.list();
        session.close();
        return teachers;
    }

}
