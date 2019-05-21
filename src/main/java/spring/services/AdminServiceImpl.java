package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import spring.dao.AdminDAO;
import spring.model.AdminEntity;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDAO dao;

    @Override
    public List<AdminEntity> getAll() {
        return dao.getAll();
    }

    @Override
    public AdminEntity getEntityById(int id) {
        return dao.getEntityById(id);
    }

    @Override
    public void save(AdminEntity entity) {
        dao.save(entity);
    }

    @Override
    public void update(AdminEntity entity) {
        dao.update(entity);
    }

    @Override
    public void delete(AdminEntity entity) {
        dao.delete(entity);
    }

    @Override
    public AdminEntity getAdminByLogin(String login) {
        return dao.getAdminByLogin(login);
    }
}
