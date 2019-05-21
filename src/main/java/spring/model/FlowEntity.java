package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flow", schema = "public", catalog = "penek")
public class FlowEntity {
    private int flowId;
    private Short flowNumber;
    private Short yearOfStudy;
    private Set<SclassEntity> sclasses = new HashSet<>();

    public FlowEntity(){}
    public FlowEntity(short flowNumber, short yearOfStudy){
        this.flowNumber = flowNumber;
        this.yearOfStudy = yearOfStudy;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "flow_id", nullable = false)
    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    @Basic
    @Column(name = "flow_number")
    public Short getFlowNumber() {
        return flowNumber;
    }

    public void setFlowNumber(Short flowNumber) {
        this.flowNumber = flowNumber;
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

        FlowEntity that = (FlowEntity) o;

        if (flowId != that.flowId) return false;
        if (flowNumber != null ? !flowNumber.equals(that.flowNumber) : that.flowNumber != null) return false;
        return yearOfStudy != null ? yearOfStudy.equals(that.yearOfStudy) : that.yearOfStudy == null;
    }

    @Override
    public int hashCode() {
        int result = flowId;
        result = 31 * result + (flowNumber != null ? flowNumber.hashCode() : 0);
        result = 31 * result + (yearOfStudy != null ? yearOfStudy.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flow_class",
            joinColumns = { @JoinColumn (name = "flow_id") },
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
