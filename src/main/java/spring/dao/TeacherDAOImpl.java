package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.SclassEntity;
import spring.model.TeacherEntity;

import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {

    @Autowired
    SessionFactory factory;

    public List<TeacherEntity> getAll(){
        Session session = factory.openSession();
        List<TeacherEntity> list = (List<TeacherEntity>) session.createQuery("select T from TeacherEntity T " +
                "order by T.lastname, T.firstname, T.patronymic").list();
        session.close();
        return list;
    }

    public TeacherEntity getEntityById(int id){
        Session session = factory.openSession();
        TeacherEntity entity = session.get(TeacherEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(TeacherEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(TeacherEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(TeacherEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    public TeacherEntity getTeacherByFIO(String lastname, String firstname, String patronymic){
        Session session = factory.openSession();
        Query query = session.createQuery("from TeacherEntity T " +
                "WHERE T.patronymic = :patronymic" +
                " AND T.firstname = :firstname" +
                " AND T.lastname = :lastname");
        query.setParameter("patronymic", patronymic);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        TeacherEntity teacherEntity = (TeacherEntity) query.getSingleResult();
        session.close();
        return teacherEntity;
    }

    public List<SclassEntity> getClasses(TeacherEntity teacher){
        Session session = factory.openSession();
        Query query = session.createQuery("select SC from SclassEntity SC inner join TeacherCourseEntity TC " +
                "on SC.tcByTcId = TC " +
                "where TC.teacherByTeacherId = :teacher");
        query.setParameter("teacher", teacher);
        List<SclassEntity> sclasses = (List<SclassEntity>) query.list();
        session.close();
        return sclasses;
    }

    public List<SclassEntity> getClasses(TeacherEntity teacher, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select SC from TeacherCourseEntity TC inner join SclassEntity SC " +
                "on SC.tcByTcId = TC " +
                "where TC.teacherByTeacherId = :teacher and " +
                "SC.wday = " + wday);
        query.setParameter("teacher", teacher);
        List<SclassEntity> sclasses = (List<SclassEntity>) query.list();
        session.close();
        return sclasses;
    }

}
