package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.ClassroomEntity;
import spring.model.SclassEntity;
import spring.other.Interval;

import java.lang.reflect.Array;
import java.util.List;

public class ClassroomDAOImpl implements ClassroomDAO {

    @Autowired
    SessionFactory factory;

    public List<ClassroomEntity> getAll(){
        Session session = factory.openSession();
        List<ClassroomEntity> list = (List<ClassroomEntity>) session.createQuery("from ClassroomEntity ").list();
        session.close();
        return list;
    }

    public ClassroomEntity getEntityById(int id){
        Session session = factory.openSession();
        ClassroomEntity entity = session.get(ClassroomEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(ClassroomEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(ClassroomEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(ClassroomEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    public ClassroomEntity getClassroomByName(String name){
        Session session = factory.openSession();
        Query query = session.createQuery("FROM ClassroomEntity C " +
                "WHERE C.name = :name");
        query.setParameter("name", name);
        ClassroomEntity classroom = (ClassroomEntity) query.getSingleResult();
        session.close();
        return classroom;
    }

    public List<ClassroomEntity> getClassroomsByCapacity(int capacity){
        Session session = factory.openSession();
        List<ClassroomEntity> classrooms = (List<ClassroomEntity>) session.createQuery("FROM ClassroomEntity C " +
                "WHERE C.capacity >= " + capacity).list();
        session.close();
        return classrooms;
    }

    public List<ClassroomEntity> getFreeClassrooms(Interval interval, String type){

        Object[] array = interval.getPairNums().toArray();
        Session session = factory.openSession();
        if(array.length == 0 || interval.getDay() == 0)
            return null;
        StringBuilder query = new StringBuilder("from ClassroomEntity C " +
                "where C not in " +
                "(select S.classroomByClassroomId " +
                "from SclassEntity S " +
                "where S.wday = " + interval.getDay() + " and C.type = :type " +
                "and (S.pairNumber = " + array[0]);
        for(int i = 1; i < array.length; ++i){
            query.append(" or S.pairNumber = ").append(array[i]);
        }
        query.append("))");
        Query query1 = session.createQuery(query.toString());
        query1.setParameter("type", type);
        List<ClassroomEntity> classrooms = (List<ClassroomEntity>) query1.list();
        session.close();
        return classrooms;
    }

    public List<ClassroomEntity> getFreeClassrooms(Interval interval){
        Object[] array = interval.getPairNums().toArray();
        Session session = factory.openSession();
        if(array.length == 0 || interval.getDay() == 0)
            return null;
        StringBuilder query = new StringBuilder("from ClassroomEntity C " +
                "where C not in " +
                "(select S.classroomByClassroomId " +
                "from SclassEntity S " +
                "where S.wday = " + interval.getDay() + " and (S.pairNumber = " + array[0]);
        for(int i = 1; i < array.length; ++i){
            query.append(" or S.pairNumber = ").append(array[i]);
        }
        query.append("))");
        List<ClassroomEntity> classrooms = (List<ClassroomEntity>) session.createQuery(query.toString()).list();
        session.close();
        return classrooms;
    }

    public boolean checkIfFree(ClassroomEntity classroom, int wday, int pairnum){
        Session session = factory.openSession();
        Query query = session.createQuery("select count(S) from SclassEntity S " +
                "where S.classroomByClassroomId = :classroom and " +
                "S.wday = " + wday + " and S.pairNumber = " + pairnum);
        query.setParameter("classroom", classroom);
        Long count = (Long) query.getSingleResult();
        session.close();
        return (count == 0);
    }

    public List<SclassEntity> getClasses(ClassroomEntity classroom){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from SclassEntity C inner join ClassroomEntity CL on " +
                "CL = C.classroomByClassroomId where CL = :classroom");
        query.setParameter("classroom", classroom);
        List<SclassEntity> list = (List<SclassEntity>) query.list();
        session.close();
        return list;
    }

    public List<SclassEntity> getClasses(ClassroomEntity classroom, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from SclassEntity C inner join ClassroomEntity CL on " +
                "CL = C.classroomByClassroomId where CL = :classroom and C.wday = " + wday);
        query.setParameter("classroom", classroom);
        List<SclassEntity> list = (List<SclassEntity>) query.list();
        session.close();
        return list;
    }

}
