package spring.services;

import spring.model.AdminEntity;

import java.util.List;

public interface AdminService {
    public List<AdminEntity> getAll();
    public AdminEntity getEntityById(int id);
    public void save(AdminEntity entity);
    public void update(AdminEntity entity);
    public void delete(AdminEntity entity);

    public AdminEntity getAdminByLogin(String login);
}
