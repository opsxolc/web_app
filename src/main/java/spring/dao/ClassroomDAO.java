package spring.dao;

import spring.model.ClassroomEntity;
import spring.model.SclassEntity;
import spring.other.Interval;

import java.util.List;

public interface ClassroomDAO {
    public List<ClassroomEntity> getAll();
    public ClassroomEntity getEntityById(int id);
    public void save(ClassroomEntity entity);
    public void update(ClassroomEntity entity);
    public void delete(ClassroomEntity entity);

    public List<SclassEntity> getClasses(ClassroomEntity classroom, int wday);
    public List<SclassEntity> getClasses(ClassroomEntity classroom);
    public List<ClassroomEntity> getFreeClassrooms(Interval interval);
    public ClassroomEntity getClassroomByName(String name);
    public List<ClassroomEntity> getClassroomsByCapacity(int capacity);
    public List<ClassroomEntity> getFreeClassrooms(Interval interval, String type);
    public boolean checkIfFree(ClassroomEntity classroom, int wday, int pairnum);
}
