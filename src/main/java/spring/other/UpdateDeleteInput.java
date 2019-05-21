package spring.other;

import spring.model.StudentEntity;

public class UpdateDeleteInput {
    private int studentId;
    private String lastname, firstname, patronymic;
    private int groupId;

    public UpdateDeleteInput(){
        this.studentId = 0;
        this.lastname = "";
        this.patronymic = "";
        this.firstname = "";
        this.groupId = 0;
    };

    public UpdateDeleteInput(StudentEntity student){
        this.studentId = student.getStudentId();
        this.firstname = student.getFirstname();
        this.lastname = student.getLastname();
        this.patronymic = student.getPatronymic();
        this.groupId = student.getSgroupBySgroupId().getSgroupId();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
