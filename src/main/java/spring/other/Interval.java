package spring.other;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interval {
    private Integer day;
    private Set<Integer> pairNums;
    private String pairNumsStr;

    public void setPairNums(Set<Integer> pairNums) {
        this.pairNums = pairNums;
    }

    public String getPairNumsStr() {
        return pairNumsStr;
    }

    public void setPairNumsStr(String pairNumsStr) {
        this.pairNumsStr = pairNumsStr;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Set<Integer> getPairNums() {
        return pairNums;
    }

    public void setPairNums(String str){
        this.pairNums = new HashSet<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            this.pairNums.add(Integer.parseInt(matcher.group()));
        }
    }

    public void addPairNum(Integer pairNum){
        this.pairNums.add(pairNum);
    }

    public void removePairNum(Integer pairNum){
        this.pairNums.remove(pairNum);
    }
}