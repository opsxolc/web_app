package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import spring.model.AdminEntity;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Autowired
    SessionFactory factory;

    public List<AdminEntity> getAll(){
        Session session = factory.openSession();
        List<AdminEntity> list = (List<AdminEntity>) session.createQuery("from AdminEntity").list();
        session.close();
        return list;
    }

    public AdminEntity getEntityById(int id){
        Session session = factory.openSession();
        AdminEntity entity = session.get(AdminEntity.class, id);
        session.close();
        return entity;
    }

    @Override
    public void save(AdminEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(AdminEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(AdminEntity entity) {
        Session session = factory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(entity);
        tx1.commit();
        session.close();
    }

    public AdminEntity getAdminByLogin(String login){
        Session session = factory.openSession();
        Query query = session.createQuery("FROM AdminEntity A " +
                "WHERE A.login = :login");
        query.setParameter("login", login);
        AdminEntity admin = (AdminEntity) query.getSingleResult();
        session.close();
        return admin;
    }

}
