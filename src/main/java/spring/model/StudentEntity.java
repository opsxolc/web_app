package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student", schema = "public", catalog = "penek")
public class StudentEntity {
    private int studentId;
    private String patronymic;
    private String firstname;
    private String lastname;
    private SgroupEntity sgroupBySgroupId;
    private Set<SclassEntity> sclasses = new HashSet<>();

    public StudentEntity(){}

    public StudentEntity(String patronymic, String firstname, String lastname, SgroupEntity sgroupBySgroupId){
        this.patronymic = patronymic;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sgroupBySgroupId = sgroupBySgroupId;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "patronymic", length = 20)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 20)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 20)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (studentId != that.studentId) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sgroup_id", referencedColumnName = "sgroup_id", nullable = false)
    public SgroupEntity getSgroupBySgroupId() {
        return sgroupBySgroupId;
    }

    public void setSgroupBySgroupId(SgroupEntity sgroupBySgroupId) {
        this.sgroupBySgroupId = sgroupBySgroupId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_class",
            joinColumns = { @JoinColumn (name = "student_id") },
            inverseJoinColumns = { @JoinColumn (name = "sclass_id") }
    )
    public Set<SclassEntity> getSclasses () {
        return sclasses;
    }

    public void setSclasses(Set<SclassEntity> sclasses) {
        this.sclasses = sclasses;
    }

    public void addSclass(SclassEntity sclass) {
        sclasses.add(sclass);
    }
    public void removeSclass(SclassEntity sclassEntity){
        this.sclasses.remove(sclassEntity);
    }
}
