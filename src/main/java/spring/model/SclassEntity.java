package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sclass", schema = "public", catalog = "penek")
public class SclassEntity {
    private int sclassId;
    private Short pairNumber;
    private Short wday;
    private TeacherCourseEntity tcByTcId;
    private ClassroomEntity classroomByClassroomId;
    private Set<StudentEntity> students = new HashSet<>();
    private Set<SgroupEntity> groups = new HashSet<>();
    private Set<FlowEntity> flows = new HashSet<>();

    public SclassEntity(){}
    public SclassEntity(int pairNumber, int wday, TeacherCourseEntity tcByTcId,
                        ClassroomEntity classroomByClassroomId){
        this.pairNumber = (short) pairNumber;
        this.wday = (short) wday;
        this.tcByTcId = tcByTcId;
        this.classroomByClassroomId = classroomByClassroomId;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "sclass_id", nullable = false)
    public int getSclassId() {
        return sclassId;
    }

    public void setSclassId(int sclassId) {
        this.sclassId = sclassId;
    }

    @Basic
    @Column(name = "pair_number")
    public Short getPairNumber() {
        return pairNumber;
    }

    public void setPairNumber(Short pairNumber) {
        this.pairNumber = pairNumber;
    }

    @Basic
    @Column(name = "wday")
    public Short getWday() {
        return wday;
    }

    public void setWday(Short wday) {
        this.wday = wday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SclassEntity that = (SclassEntity) o;

        if (sclassId != that.sclassId) return false;
        if (pairNumber != null ? !pairNumber.equals(that.pairNumber) : that.pairNumber != null) return false;
        if (wday != null ? !wday.equals(that.wday) : that.wday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sclassId;
        result = 31 * result + (pairNumber != null ? pairNumber.hashCode() : 0);
        result = 31 * result + (wday != null ? wday.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "classroom_id")
    public ClassroomEntity getClassroomByClassroomId() {
        return classroomByClassroomId;
    }

    public void setClassroomByClassroomId(ClassroomEntity classroomByClassroomId) {
        this.classroomByClassroomId = classroomByClassroomId;
    }

    @ManyToOne
    @JoinColumn(name = "tc_id", referencedColumnName = "tc_id")
    public TeacherCourseEntity getTcByTcId() {
        return tcByTcId;
    }

    public void setTcByTcId(TeacherCourseEntity tcByTcId){
        this.tcByTcId = tcByTcId;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "sclasses")
    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> studentsSet) {
        this.students = studentsSet;
    }

    public void addStudent(StudentEntity student){
        this.students.add(student);
    }
    public void removeStudent(StudentEntity studentEntity) {
        this.students.remove(studentEntity);
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "sclasses")
    public Set<SgroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<SgroupEntity> groups) {
        this.groups = groups;
    }

    public void addGroup(SgroupEntity group){
        this.groups.add(group);
    }
    public void removeGroup(SgroupEntity sgroupEntity) {
        this.groups.remove(sgroupEntity);
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "sclasses")
    public Set<FlowEntity> getFlows() {
        return flows;
    }

    public void setFlows(Set<FlowEntity> flows) {
        this.flows = flows;
    }

    public void addFlow(FlowEntity flow){
        this.flows.add(flow);
    }
    public void removeFlow(FlowEntity flowEntity){
        this.flows.remove(flowEntity);
    }
}
