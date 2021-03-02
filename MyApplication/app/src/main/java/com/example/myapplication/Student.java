package com.example.myapplication;

public class Student
{
    String ID;
    String name;
    String familyName;
    public Student(String ID, String name,String familyName)
    {
        this.ID =ID ;
        this.name = name;
        this.familyName = familyName;

    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }


}

