package spring.dao;

import spring.model.CourseEntity;

import java.util.List;

public interface CourseDAO{
    public List<CourseEntity> getAll();
    public CourseEntity getEntityById(int id);
    public void save(CourseEntity entity);
    public void update(CourseEntity entity);
    public void delete(CourseEntity entity);

    public List<String> getAllNames();

    public List<CourseEntity> getCoursesByName(String name);
    public CourseEntity getCourseByNameCover(String name, String cover);

}
