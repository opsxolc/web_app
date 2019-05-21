package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "course", schema = "public", catalog = "penek")
public class CourseEntity {
    private int courseId;
    private String courseName;
    private String cover;
    private Short intensity;
    private Short yearOfStudy;

    public CourseEntity(){}
    public CourseEntity(String courseName, String cover, Short intensity, Short yearOfStudy){
        this.courseName = courseName;
        if(cover.equals("flow") || cover.equals("group") || cover.equals("student")) {
            this.cover = cover;
        } else throw new ExceptionInInitializerError("Bad cover in course constructor");
        this.intensity = intensity;
        this.yearOfStudy = yearOfStudy;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name", length = 50)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "cover", length = 10)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "intensity")
    public Short getIntensity() {
        return intensity;
    }

    public void setIntensity(Short intensity) {
        this.intensity = intensity;
    }

    @Basic
    @Column(name = "year_of_study")
    public Short getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Short yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (courseId != that.courseId) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (intensity != null ? !intensity.equals(that.intensity) : that.intensity != null) return false;
        if (yearOfStudy != null ? !yearOfStudy.equals(that.yearOfStudy) : that.yearOfStudy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (intensity != null ? intensity.hashCode() : 0);
        result = 31 * result + (yearOfStudy != null ? yearOfStudy.hashCode() : 0);
        return result;
    }
}
