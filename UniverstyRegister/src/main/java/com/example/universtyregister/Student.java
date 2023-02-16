package com.example.universtyregister;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Student extends Person{
    private int year;
    private String department;
    private boolean studyFees = false;
    private String[][] examTable;
    private float attendanceGrade;
    private float quizGrade;
    private float midTermGrade;
    private float finalGrade;

    public Student() {}
    public Student(int id) throws Exception {
        setId(id);
    }
    public Student(int id,
                   long natId,
                   String fName,
                   String lName,
                   int age,
                   String gender,
                   String phone,
                   String email,
                   int year,
                   String department,
                   String address) throws Exception {

        super(id, natId, fName, lName, age, gender, phone, email, address);
        boolean assignYear = false;// booleans to check elements before assigning them
        boolean assignDepartment = false;

        if (year >= 1 && year <= 4) {
            assignYear = true;
        } else {
            throw new Exception("year input must be between 1 and 4");
        }
        if (
                department.equals("ICT") ||
                        department.equals("Autotronics") ||
                        department.equals("Energy") ||
                        department.equals("Mechatronics") ||
                        department.equals("Prosthetics")) {
            assignDepartment = true;
        } else{
            throw new Exception("department input is not valid");
        }
        // if all of them are true then assign the values
        if (assignYear && assignDepartment) {
            this.year = year;
            this.department = department;
        }
    }

    public int getYear() {
        return year;
    }
    public String getDepartment() {
        return department;
    }
    public boolean getStudyFees() {
        return studyFees;
    }
    public String[][] getExamTable() {
        return examTable;
    }
    public float getAttendanceGrade() {
        return attendanceGrade;
    }
    public float getQuizGrade() {
        return quizGrade;
    }
    public float getMidTermGrade() {
        return midTermGrade;
    }
    public float getFinalGrade() {
        return finalGrade;
    }
    public float getSemesterGrade() {
        return getAttendanceGrade() + getQuizGrade() + getMidTermGrade() + getFinalGrade();
    }

    public void setYear(int year) throws Exception {
        if (year >= 1 && year <= 4)
            this.year = year;
        else
            throw new Exception("year input must be between 1 and 4");
    }
    public void setDepartment(String department) throws Exception {
        if (
                department.equals("ICT") ||
                        department.equals("Autotronics") ||
                        department.equals("Energy") ||
                        department.equals("Mechatronics") ||
                        department.equals("Prosthetics"))
            this.department = department;
        else
            throw new Exception("department input is not valid\navailable departments are " +
                    "(ICT - Autotronics - Energy - Mechatronics - Prosthetics)");
    }
    public void setStudyFees(boolean studyFees) {
        this.studyFees = studyFees;
    }
    public void setExamTable(String[][] examTable) throws Exception {
        if (tableValidation(examTable))
            this.examTable = examTable;
        else
            throw new Exception("exam table input is not valid");
    }
    public void setAttendanceGrade(float attendanceGrade) throws Exception {
        if (attendanceGrade >= 0 && attendanceGrade <= 20)
            this.attendanceGrade = attendanceGrade;
        else
            throw new Exception("attendance grade must be between 0 and 20");
    }
    public void setQuizGrade(float quizGrade) throws Exception {
        if (quizGrade >= 0 && quizGrade <= 20)
            this.quizGrade = quizGrade;
        else
            throw new Exception("quiz grade must be between 0 and 20");
    }
    public void setMidTermGrade(float midTermGrade) throws Exception {
        if (midTermGrade >= 0 && midTermGrade <= 40)
            this.midTermGrade = midTermGrade;
        else
            throw new Exception("midTerm grade must be between 0 and 40");
    }
    public void setFinalGrade(float finalGrade) throws Exception {
        if (finalGrade >= 0 && finalGrade <= 70)
            this.finalGrade = finalGrade;
        else
            throw new Exception("final grade must be between 0 and 70");
    }

    public static void myCourses(String myDepartment, int myYear) throws Exception {
        Student student = new Student();
        student.setDepartment(myDepartment);
        student.setYear(myYear);
        File file = new File("courseDatabase.txt");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        String id, name, dept, grade, year;
        boolean found = false;
        while (scanner.hasNext()) {
            id = scanner.next();
            name = scanner.next();
            dept = scanner.next();
            grade = scanner.next();
            year = scanner.next();
            if (dept.toUpperCase().equals(myDepartment) && year.trim().equals(String.valueOf(myYear))) {
                found = true;
                JOptionPane.showMessageDialog(null,name);
            }
        }
        System.out.println("\n");
        if (!found)
            throw new Exception("no courses found");
    }
    public static void myGrades(String kind, int id) throws Exception {
        Student student = new Student();
        student.setId(id);
        boolean found = false;
        File file = null;
        if (kind.equals("AttendanceGrades"))
            file = new File("studentAttendanceGrades.txt");
        else if (kind.equals("QuizGrades"))
            file = new File("studentQuizGrades.txt");
        else if (kind.equals("MidTermGrades"))
            file = new File("studentMidTermGrades.txt");
        else if (kind.equals("FinalGrades"))
            file = new File("studentFinalGrades.txt");
        else
            throw new Exception("this kind is not exist\navailable kind of grades are: " +
                    "(AttendanceGrades - QuizGrades - MidTermGrades - FinalGrades)");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        String readId, course, grade;
        while (scanner.hasNext()) {
            readId = scanner.next();
            course = scanner.next();
            grade = scanner.next();
            if (readId.equals(String.valueOf(id))) {
                found = true;
                System.out.println(course + ":\t" + grade);
            }
        }
        if (!found)
            throw new Exception("id was not found");
    }
}
