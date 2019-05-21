package spring.other;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleInput {
    private Set<Integer> days;
    private String daysStr;
    private String lastname, firstname, patronymic;

    public Set<Integer> getDays() {
        return days;
    }

    public void setDays(Set<Integer> days) {
        this.days = days;
    }

    public String getDaysStr() {
        return daysStr;
    }

    public void setDaysStr(String daysStr) {
        this.daysStr = daysStr;
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    private String classroom;

    public void setDays(){
        this.days = new HashSet<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(this.daysStr);
        while (matcher.find()){
            this.days.add(Integer.parseInt(matcher.group()));
        }
    }
}
