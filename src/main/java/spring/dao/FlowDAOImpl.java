package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.FlowEntity;
import spring.model.SclassEntity;
import spring.model.SgroupEntity;
import spring.model.StudentEntity;

import java.util.List;

public class FlowDAOImpl implements FlowDAO {

    @Autowired
    SessionFactory factory;

    public List<FlowEntity> getAll(){
        Session session = factory.openSession();
        List<FlowEntity> list = (List<FlowEntity>) session.createQuery("from FlowEntity").list();
        session.close();
        return list;
    }

    public FlowEntity getEntityById(int id){
        Session session = factory.openSession();
        FlowEntity entity = session.get(FlowEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(FlowEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(FlowEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(FlowEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    public FlowEntity getFlowByNumYear(int number, int year){
        Session session = factory.openSession();
        FlowEntity flow = (FlowEntity) session.createQuery("FROM FlowEntity F WHERE F.flowNumber = " + number +
                        " AND F.yearOfStudy = " + year).getSingleResult();
        session.close();
        return flow;
    }

    public List<StudentEntity> getStudents(FlowEntity flow){
        Session session = factory.openSession();
        Query query = session.createQuery("select S " +
                "from FlowEntity F inner join SgroupEntity G on G.flowByFlowId = F inner join " +
                "StudentEntity S on S.sgroupBySgroupId = G " +
                "where F = :flow " +
                "order by S.sgroupBySgroupId.groupNumber");
        query.setParameter("flow", flow);
        List<StudentEntity> students = (List<StudentEntity>) query.list();
        session.close();
        return students;
    }

    public List<SclassEntity> getClasses(FlowEntity flow){
        Session session = factory.openSession();
        Query query = session.createQuery("select C " +
                "from FlowEntity F inner join F.sclasses C " +
                "where F = :flow " +
                "order by C.wday, C.pairNumber");
        query.setParameter("flow", flow);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getClasses(FlowEntity flow, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select SC " +
                "from FlowEntity F inner join F.sclasses SC " +
                "where F = :flow and SC.wday = " + wday);
        query.setParameter("flow", flow);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SgroupEntity> getGroups(FlowEntity flow){
        Session session = factory.openSession();
        Query query = session.createQuery("select G from SgroupEntity G where G.flowByFlowId = :flow " +
                "order by G.groupNumber");
        query.setParameter("flow", flow);
        List<SgroupEntity> groups = (List<SgroupEntity>) query.list();
        session.close();
        return groups;
    }

}
