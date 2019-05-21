package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.CourseEntity;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Autowired
    SessionFactory factory;

    @Override
    public List<CourseEntity> getAll(){
        Session session = factory.openSession();
        List<CourseEntity> list = (List<CourseEntity>) session.createQuery("from CourseEntity ").list();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllNames(){
        Session session = factory.openSession();
        List<String> list = (List<String>) session.createQuery("select distinct C.courseName " +
                "from CourseEntity C " +
                "order by C.courseName").list();
        session.close();
        return list;
    }

    @Override
    public CourseEntity getEntityById(int id){
        Session session = factory.openSession();
        CourseEntity entity = session.get(CourseEntity.class, id);
        return entity;
    }

    @Override
    public void save(CourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(CourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(CourseEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    @Override
    public List<CourseEntity> getCoursesByName(String name){
        Session session = factory.openSession();
        Query query = session.createQuery("from CourseEntity C " +
                "where C.courseName = :name");
        query.setParameter("name", name);
        List<CourseEntity> courses = (List<CourseEntity>) query.list();
        session.close();
        return courses;
    }

    @Override
    public CourseEntity getCourseByNameCover(String name, String cover){
        Session session = factory.openSession();
        Query query = session.createQuery("from CourseEntity C " +
                "where C.courseName = :name and C.cover = :cover");
        query.setParameter("name", name);
        query.setParameter("cover", cover);
        CourseEntity courseEntity = (CourseEntity) query.getSingleResult();
        session.close();
        return courseEntity;
    }

}
