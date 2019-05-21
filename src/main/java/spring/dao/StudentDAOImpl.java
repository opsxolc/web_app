package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.SclassEntity;
import spring.model.StudentEntity;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Autowired
    SessionFactory factory;

    public List<StudentEntity> getAll(){
        Session session = factory.openSession();
        List<StudentEntity> list = (List<StudentEntity>) session.createQuery("from StudentEntity S " +
                "order by S.sgroupBySgroupId.flowByFlowId.yearOfStudy, S.sgroupBySgroupId.flowByFlowId.flowNumber, S.sgroupBySgroupId.groupNumber, " +
                "S.lastname, S.firstname, S.patronymic").list();
        session.close();
        return list;
    }

    public StudentEntity getEntityById(int id){
        Session session = factory.openSession();
        StudentEntity entity = session.get(StudentEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(StudentEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.save(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void update(StudentEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.update(entity);
        tc.commit();
        session.close();
    }

    @Override
    public void delete(StudentEntity entity) {
        Session session = factory.openSession();
        Transaction tc = session.beginTransaction();
        session.delete(entity);
        tc.commit();
        session.close();
    }

    public StudentEntity getStudentByFIO(String lastname, String firstname, String patronymic){
        Session session = factory.openSession();
        Query query = session.createQuery("FROM StudentEntity S " +
                "WHERE S.patronymic = :patronymic" +
                " AND S.firstname = :firstname" +
                " AND S.lastname = :lastname");
        query.setParameter("patronymic", patronymic);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        StudentEntity studentEntity = (StudentEntity) query.getSingleResult();
        session.close();
        return studentEntity;
    }

    public List<SclassEntity> getClasses(StudentEntity student){
        Session session = factory.openSession();
        Query query = session.createQuery("select S.sclasses " +
                "from StudentEntity S " +
                "where S = :student");
        query.setParameter("student", student);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getClasses(StudentEntity student, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select SC from StudentEntity S " +
                "inner join S.sclasses SC " +
                "where S = :student and SC.wday = " + wday);
        query.setParameter("student", student);
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getAllClasses(StudentEntity student, int wday){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from SclassEntity C " +
                "where (C in " +
                "(select C from SclassEntity C inner join C.students S " +
                "where S = :student) or C in " +
                "(select C from SclassEntity C inner join C.groups G " +
                "where G = :sgroup) or C in " +
                "(select C from SclassEntity C inner join C.flows F " +
                "where F = :flow)) and C.wday = " + wday +
                " order by C.pairNumber, C.wday");
        query.setParameter("student", student);
        query.setParameter("sgroup", student.getSgroupBySgroupId());
        query.setParameter("flow", student.getSgroupBySgroupId().getFlowByFlowId());
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getAllClasses(StudentEntity student){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from SclassEntity C " +
                "where C in " +
                "(select C from SclassEntity C inner join C.students S " +
                "where S = :student) or C in " +
                "(select C from SclassEntity C inner join C.groups G " +
                "where G = :sgroup) or C in " +
                "(select C from SclassEntity C inner join C.flows F " +
                "where F = :flow)" +
                "order by C.pairNumber, C.wday");
        query.setParameter("student", student);
        query.setParameter("sgroup", student.getSgroupBySgroupId());
        query.setParameter("flow", student.getSgroupBySgroupId().getFlowByFlowId());
        List<SclassEntity> classes = (List<SclassEntity>) query.list();
        session.close();
        return classes;
    }

    public List<SclassEntity> getSpeccourses(StudentEntity student){
        Session session = factory.openSession();
        Query query = session.createQuery("select C from StudentEntity S inner join S.sclasses C " +
                "where C.tcByTcId.courseByCourseId.cover = :cover and S = :student");
        query.setParameter("student", student);
        query.setParameter("cover", "student");
        List<SclassEntity> list = (List<SclassEntity>) query.list();
        session.close();
        return list;
    }

}
