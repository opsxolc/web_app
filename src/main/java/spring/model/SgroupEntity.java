package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sgroup", schema = "public", catalog = "penek")
public class SgroupEntity {
    private int sgroupId;
    private Short groupNumber;
    private FlowEntity flowByFlowId;
    private Set<SclassEntity> sclasses = new HashSet<>();

    public SgroupEntity(){}
    public SgroupEntity(int groupNumber, FlowEntity flowByFlowId){
        this.groupNumber = (short) groupNumber;
        this.flowByFlowId = flowByFlowId;
    }

    @Id
    @GenericGenerator(name="keygen" , strategy="increment")
    @GeneratedValue(generator="keygen")
    @Column(name = "sgroup_id", nullable = false)
    public int getSgroupId() {
        return sgroupId;
    }

    public void setSgroupId(int sgroupId) {
        this.sgroupId = sgroupId;
    }

    @Basic
    @Column(name = "group_number")
    public Short getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Short groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SgroupEntity that = (SgroupEntity) o;

        if (sgroupId != that.sgroupId) return false;
        return groupNumber != null ? groupNumber.equals(that.groupNumber) : that.groupNumber == null;
    }

    @Override
    public int hashCode() {
        int result = sgroupId;
        result = 31 * result + (groupNumber != null ? groupNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "flow_id", referencedColumnName = "flow_id", nullable = false)
    public FlowEntity getFlowByFlowId() {
        return flowByFlowId;
    }

    public void setFlowByFlowId(FlowEntity flowByFlowId) {
        this.flowByFlowId = flowByFlowId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_class",
            joinColumns = { @JoinColumn (name = "sgroup_id") },
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
