package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "classroom", schema = "public", catalog = "penek")
public class ClassroomEntity {
    private int classroomId;
    private Short capacity;
    private String type;
    private String name;

    public ClassroomEntity(){}

    public ClassroomEntity(int capacity, String type, String name){
        if (!type.equals("group") && !type.equals("flow")){
            throw new ExceptionInInitializerError("Bad type of classroom in constructor");
        }
        this.type = type;
        this.name = name;
        this.capacity = (short) capacity;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "classroom_id", nullable = false)
    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Basic
    @Column(name = "capacity")
    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "type", length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name", length = 4)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassroomEntity that = (ClassroomEntity) o;

        if (classroomId != that.classroomId) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classroomId;
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
