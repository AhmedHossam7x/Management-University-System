package com.example.universtyregister;

public class Course {
    private int id;
    private String name;
    private String department;
    private float grade;
    private int year;

    Course() {}
    public Course(int searchId) throws Exception {
        if (searchId > 0)
            this.id = id;
        else
            throw new Exception("ID input is not valid");
    }
    public Course(int id, String name, String department, float grade, int year) throws Exception {
        boolean assignId = false;// booleans to check elements before assigning them
        boolean assignDepartment = false;
        boolean assignGrade = false;
        boolean assignYear = false;

        if (id > 0) {
            assignId = true;
        }else {
            throw new Exception("id input is not valid");
        }
        if (department.equals("ICT") ||
                        department.equals("Autotronics") ||
                        department.equals("Energy") ||
                        department.equals("Mechatronics") ||
                        department.equals("Prosthetics")) {
            assignDepartment = true;
        }else {
            throw new Exception("department input is not valid\navailable departments are:\n" +
                    "(ICT - Autotronics - Energy - Mechatronics - Prosthetics)");
        }
        if (grade >= 50 && grade <= 200) {
            assignGrade = true;
        }else {
            throw new Exception("grade must be between 50 and 200");
        }
        if (year >= 1 && year <= 4) {
            assignYear = true;
        }else {
            throw new Exception("year must be between 1 and 4");
        }// if all of them are true then assign the values
        if (assignId && assignDepartment && assignGrade && assignYear) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.grade = grade;
            this.year = year;
        }
    }
    // setters and getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public float getGrade() { return grade; }
    public int getYear() { return year; }

    public void setId(int id) throws Exception {
        if (id > 0)
            this.id = id;
        else
            throw new Exception("id input is not valid");
    }
    public void setName(String name) throws Exception { this.name = name; }
    public void setDepartment(String department) throws Exception {
        if (
                department.equals("ICT") ||
                        department.equals("Autotronics") ||
                        department.equals("Energy") ||
                        department.equals("Mechatronics") ||
                        department.equals("Prosthetics"))
            this.department = department;
        else
            throw new Exception("department input is not valid\navailable departments are:\n" +
                    "(ICT - Autotronics - Energy - Mechatronics - Prosthetics)");
    }
    public void setGrade(float grade) throws Exception {
        if (grade >= 50 && grade <= 200)
            this.grade = grade;
        else
            throw new Exception("grade must be between 50 and 200");
    }
    public void setYear(int year) throws Exception {
        if (year >= 1 && year <= 4)
            this.year = year;
        else
            throw new Exception("year must be between 1 and 4");
    }
}