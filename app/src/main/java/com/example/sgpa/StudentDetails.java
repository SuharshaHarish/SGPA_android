package com.example.sgpa;

import java.io.Serializable;

public class StudentDetails implements Serializable {

    String name;
    String reg_no;
    String clg;

    public StudentDetails (String name,String reg_no, String clg){
        this.name=name;
        this.reg_no=reg_no;
        this.clg=clg;

    }
}
