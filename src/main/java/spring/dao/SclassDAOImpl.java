package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.SclassEntity;
import spring.model.StudentEntity;
import spring.services.StudentService;

import java.util.LinkedList;
import java.util.List;

public class SclassDAOImpl implements SclassDAO {

    @Autowired
    SessionFactory factory;
    @Autowired
    StudentService studentService;

    public List<SclassEntity> getAll(){
        Session session = factory.openSession();
        List<SclassEntity> list = (List<SclassEntity>) session.createQuery("from SclassEntity ").list();
        session.close();
        return list;
    }

    public SclassEntity getEntityById(int id){
        Session session = factory.openSession();
        SclassEntity entity = session.get(SclassEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public int save(SclassEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        int id = (int) session.save(entity);
        tc.commit();
        session.close();
        return id;
    }

    @Override
    public void update(SclassEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(SclassEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    @Override
    public List<SclassEntity> getAvailableSpeccourses(StudentEntity studentEntity){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from SclassEntity C where C.tcByTcId.courseByCourseId.cover = " +
                ":cover");
        query.setParameter("cover", "student");
        List<SclassEntity> listSpec = (List<SclassEntity>) query.list();
        List<SclassEntity> list = new LinkedList<>();
        List<SclassEntity> listStudSclass = studentService.getAllClasses(studentEntity);
        for(SclassEntity sclass : listSpec){
            int wday = sclass.getWday();
            int pairNum = sclass.getPairNumber();
            int i = listStudSclass.size() - 1;
            while (i >= 0 && !(listStudSclass.get(i).getWday() == wday && listStudSclass
                    .get(i).getPairNumber() == pairNum))
                --i;
            if(i < 0)
                list.add(sclass);
        }
        session.close();
        return list;
    }

}
