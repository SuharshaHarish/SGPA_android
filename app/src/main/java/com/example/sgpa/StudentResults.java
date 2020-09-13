package com.example.sgpa;

import java.io.Serializable;

public class StudentResults implements Serializable {
    String subject;
    String credits;
    String grade;

    public StudentResults(String subject, String credits, String grade) {
        this.subject = subject;
        this.credits = credits;
        this.grade = grade;
    }
}


