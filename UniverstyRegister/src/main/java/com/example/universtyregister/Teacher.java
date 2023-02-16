package com.example.universtyregister;

import java.io.*;
import java.util.Scanner;

public class Teacher extends Person{
    float salary;

    Teacher(){}
    Teacher(int id) throws Exception {
        setId(id);
    }
    Teacher(String userName, String password) throws Exception {
        super(userName, password);
    }
    Teacher(int id,
            long natId,
            String fName,
            String lName,
            int age,
            String gender,
            String phone,
            String email,
            String address,
            float salary) throws Exception {
        super(id, natId, fName, lName, age, gender, phone, email, address);

        if (salary > 1000)
            this.salary = salary;
        else
            throw new Exception("salary input is not valid");

    }

    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) throws Exception {
        if (salary > 1000)
            this.salary = salary;
        else
            throw new Exception("salary input is not valid\nnote that minimum salary is 1000");
    }

    public static void markAttendance(int id, String course, float grade) throws Exception {
        Student student = new Student();
        try {
            student.setId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        Course obj = new Course();
        try {
            obj.setName(course);
        } catch (Exception e) {
            System.out.println(e);
        }
        student.setAttendanceGrade(grade);
        File file = new File("studentAttendanceGrades.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(student.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                } else {
                    read.nextLine(); // puts the cursor at the beginning of each line always reading first 1st field of the records (rows)
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("," + id /*id means student id*/ + "," + course + "," + grade);
                writer.newLine();
                System.out.println("record added successfully");
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
    public static void markQuiz(int id, String course, float grade) throws Exception {
        Student student = new Student(id);
        Course obj = new Course();
        obj.setName(course);
        student.setAttendanceGrade(grade);
        File file = new File("studentQuizGrades.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(student.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                } else {
                    read.nextLine();
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("," + id /*id means student id*/ + "," + course + "," + grade);
                writer.newLine();
                System.out.println("record added successfully");
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
    public static void markMidTerm(int id, String course, float grade) throws Exception {
        Student student = new Student(id);
        Course obj = new Course();
        obj.setName(course);
        student.setAttendanceGrade(grade);
        File file = new File("studentMidTermGrades.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(student.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                } else {
                    read.nextLine(); // puts the cursor at the beginning of each line
                    // always reading first 1st field of the records (rows)
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("," + id /*id means student id*/ + "," + course + "," + grade);
                writer.newLine();
                System.out.println("record added successfully");
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
    public static void markFinal(int id, String course, float grade) throws Exception {
        Student student = new Student(id);
        Course obj = new Course();
        obj.setName(course);
        student.setFinalGrade(grade);
        File file = new File("studentFinalGrades.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(student.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                } else {
                    read.nextLine(); // puts the cursor at the beginning of each line
                    // always reading first 1st field of the records (rows)
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("," + id /*id means student id*/ + "," + course + "," + grade);
                writer.newLine();
                System.out.println("record added successfully");
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
}
