package com.stillcrowdfunding.entity;

/**
 * @author Administrator
 * @date 2020/6/15 16:42
 * @description
 **/
public class Subject {
    private String subjectName;
    private String subjectScore;

    public Subject() {
    }

    public Subject(String subjectName, String subjectScore) {
        this.subjectName = subjectName;
        this.subjectScore = subjectScore;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(String subjectScore) {
        this.subjectScore = subjectScore;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", subjectScore='" + subjectScore + '\'' +
                '}';
    }
}
