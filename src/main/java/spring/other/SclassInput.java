package spring.other;

public class SclassInput {
    private short tcId;
    private short classroomId;
    private int wday;
    private int pairNum;
    private int sclassId;

    public int getSclassId() {
        return sclassId;
    }

    public void setSclassId(int sclassId) {
        this.sclassId = sclassId;
    }

    public int getWday() {
        return wday;
    }

    public void setWday(int wday) {
        this.wday = wday;
    }

    public int getPairNum() {
        return pairNum;
    }

    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    public short getTcId() {
        return tcId;
    }

    public void setTcId(short tcId) {
        this.tcId = tcId;
    }

    public short getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(short classroomId) {
        this.classroomId = classroomId;
    }
}
