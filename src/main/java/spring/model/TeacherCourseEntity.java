package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "teacher_course", schema = "public", catalog = "penek")
public class TeacherCourseEntity {
    private int tcId;
    private Short year;
    private TeacherEntity teacherByTeacherId;
    private CourseEntity courseByCourseId;

    public TeacherCourseEntity(){}

    public TeacherCourseEntity(TeacherEntity teacherByTeacherId, CourseEntity courseByCourseId, int year){
        this.teacherByTeacherId = teacherByTeacherId;
        this.courseByCourseId = courseByCourseId;
        this.year = (short) year;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "tc_id", nullable = false)
    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    @Basic
    @Column(name = "year")
    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherCourseEntity that = (TeacherCourseEntity) o;

        if (tcId != that.tcId) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tcId;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    public TeacherEntity getTeacherByTeacherId() {
        return teacherByTeacherId;
    }

    public void setTeacherByTeacherId(TeacherEntity teacherByTeacherId) {
        this.teacherByTeacherId = teacherByTeacherId;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
