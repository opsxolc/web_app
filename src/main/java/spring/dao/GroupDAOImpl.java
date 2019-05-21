package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.SclassEntity;
import spring.model.SgroupEntity;
import spring.model.StudentEntity;

import java.util.List;

public class GroupDAOImpl implements GroupDAO {

    @Autowired
    SessionFactory factory;

    public List<SgroupEntity> getAll(){
        Session session = factory.openSession();
        List<SgroupEntity> list = (List<SgroupEntity>) session.createQuery("from SgroupEntity").list();
        session.close();
        return list;
    }

    public SgroupEntity getEntityById(int id){
        Session session = factory.openSession();
        SgroupEntity entity = session.get(SgroupEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(SgroupEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(SgroupEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(SgroupEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    public SgroupEntity getGroupByNum(int number){
        Session session = factory.openSession();
        SgroupEntity sgroup = (SgroupEntity) session.createQuery("FROM SgroupEntity G " +
                "WHERE G.groupNumber = " + number).getSingleResult();
        session.close();
        return sgroup;
    }

    public List<StudentEntity> getStudents(SgroupEntity sgroup){
        Session session = factory.openSession();
        Query query = session.createQuery("select distinct S " +
                "from SgroupEntity G " +
                "inner join StudentEntity S on " +
                "S.sgroupBySgroupId = :sgroup " +
                "order by S.lastname, S.firstname, S.patronymic");
        query.setParameter("sgroup", sgroup);
        List<StudentEntity> students = (List<StudentEntity>) query.list();
        session.close();
        return students;
    }

    public List<SclassEntity> getClasses(SgroupEntity sgroup){
        Session session = factory.openSession();
        Query query = session.createQuery("select F.sclasses " +
                "from SgroupEntity F " +
                "where F = :sgroup");
        query.setParameter("sgroup", sgroup);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getClasses(SgroupEntity sgroup, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select SC  " +
                "from SgroupEntity F inner join F.sclasses SC " +
                "where F = :sgroup and SC.wday = " + wday);
        query.setParameter("sgroup", sgroup);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }
}
