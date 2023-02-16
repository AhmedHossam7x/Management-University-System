package com.example.universtyregister;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Admin extends Person implements Serializable{

    public Admin(){}
    public Admin(String userName, String password)throws Exception{
        super(userName, password);
    }
    public Admin(int id, String userName, String password)throws Exception{
        super(id, userName, password);
    }
    public Admin(int searchId)throws Exception{
        super.setId(searchId);
    }

    public static void createFile(String fileName){
        try {
            File f = new File(fileName + ".txt");
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.out.println("error occurred while trying to create a file");
        }
    }
    public static void deleteFile(String fileName) {
        try {
            File f = new File(fileName);
            if (f.delete())
                System.out.println("File deleted");
            else
                System.out.println("File not found");
        }
        catch (Exception e) {
            System.out.println("error occurred while trying to delete a file");
        }
    }

    public static void insertAdminData(String filePath, int id, String userName, String password) throws Exception {
        Admin admin = new Admin(id, userName, password);
        File file = new File(filePath);
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(admin.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                } else if (admin.getUserName().equals(read.next())) {
                    throw new Exception("userName is already exist!");
                } else {
                    read.nextLine();//puts the cursor at the beginning of each line always reading first 2 fields of the records (rows)
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.write( "," + id + "," + userName + "," + password);
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
    public static void updateAdminData(String filePath, int searchId, String field, String value) throws Exception {
        Admin admin = new Admin(searchId);
        boolean idSearchExistence = false;// to check if the search id is already exist in the file or not
        boolean fieldExistence = false;// to check if the field is exist in the file or not
        int fieldNum = 0;// to specify field number
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String idCheck = scanner.next();
                if (idCheck.equals(String.valueOf(searchId))) {
                    idSearchExistence = true;
                    break;
                }
                scanner.nextLine();
            }
            scanner.close();
            if (!idSearchExistence)
                throw new Exception("entered id was not found");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return; // means there are file not found exception
        }
        try {// checks field existence
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            switch (field) {// reading the file
                case "id" -> {
                    fieldExistence = true;
                    fieldNum = 1;
                    while (scanner.hasNext()) {
                        String idCheck = scanner.next();
                        if (idCheck.equals(value))
                            throw new Exception("entered id is already found in the admin database file");
                        scanner.nextLine();
                    }
                }
                case "userName" -> {
                    fieldExistence = true;
                    fieldNum = 2;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String userName = scanner.next();
                        if (userName.equals(value))
                            throw new Exception("entered userName is already found in the admin database file");
                        scanner.nextLine();
                    }
                }
                case "password" -> {
                    fieldExistence = true;
                    fieldNum = 3;
                }
            }
            scanner.close();
            if (!fieldExistence)
                throw new Exception("entered field is not exist\nAdmin fields are: (id - userName - password)");
            else {
                String tempFile = "temp.txt";
                File oldFile = new File(filePath);
                File newFile = new File(tempFile);// data record will be updated
                String id, userName, password;
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
                    Scanner read = new Scanner(oldFile);
                    read.useDelimiter(",");
                    while (read.hasNext()) {
                        id = read.next();
                        userName = read.next();
                        password = read.next();
                        if (id.equals(String.valueOf(searchId))) {
                            // all the coming conditions means insert the new value to its field
                            // else means id was not found so write the data as it (don't update)
                            if (fieldNum == 1) {
                                admin.setId(Integer.parseInt(value));
                                writer.write("," + value + "," + userName + "," + password);
                                System.out.println("record updated successfully");
                            } else if (fieldNum == 2) {
                                admin.setUserName(value);
                                writer.write( "," + id + "," + value + "," + password);
                                System.out.println("record updated successfully");
                            } else if (fieldNum == 3) {
                                admin.setPassword(value);
                                writer.write("," + id + "," + userName + "," + value);
                                writer.newLine();
                                System.out.println("record updated successfully");
                            }
                        } else {// no update happened (id was not found) so write the data as it
                            writer.write("," + id + "," + userName + "," + password);
                        }
                    }
                    read.close();
                    writer.close();
                    oldFile.delete();
                    File myFile = new File(filePath);
                    newFile.renameTo(myFile);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file was not found");
        }
    }
    public static void deleteAdminData(String filePath, int searchId) {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String id, userName, password;// data record will be deleted
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            Scanner read = new Scanner(oldFile);
            read.useDelimiter(",");
            boolean checkRecordExistence = false;// to check if the record is exist to delete it
            while (read.hasNext()) {
                id = read.next();
                userName = read.next();
                password = read.next();
                if (id.equals(String.valueOf(searchId))) {
                    System.out.println("field deleted successfully");
                    checkRecordExistence = true;
                } else
                    writer.write("," + id + "," + userName + "," + password);
            }
            read.close();
            writer.close();
            oldFile.delete();
            File file = new File(filePath);
            newFile.renameTo(file);
            if (!checkRecordExistence)
                throw new Exception("record was not found");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertCourseData(String filePath, int id, String name, String department, float grade, int year) throws Exception {
        // taking object of teacher to do validations

        Course course = new Course(id, name, department, grade, year);

        // reading the file and see if inserted unique data is found in the database file

        File file = new File(filePath);
        Scanner read;

        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {

                if (String.valueOf(course.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                }

                else if (course.getName().equals(read.next()) && course.getDepartment().equals(read.next())) {
                    throw new Exception("this course is already exist in the same department!");
                }

                else {
                    read.nextLine(); // puts the cursor at the beginning of each line
                    // always reading first 3 fields of the records (rows)
                }
            }
            read.close();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.write("," + id + "," + name + "," + department + "," + grade + "," + year);
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
    public static void updateCourseData(String filePath, int searchId, String field, String value) throws Exception {
        Course course = new Course(searchId);

        // to check if the search id is already exist in the file or not
        boolean idSearchExistence = false;
        // to check if the field is exist in the file or not
        boolean fieldExistence = false;
        // to check if the name and department are exist in the file or not
        boolean sameNameAndDepartment = false;


        // to specify field number
        int fieldNum = 0;

        File file = new File(filePath);

        // checks searching id existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");

            // reading the file
            while (scanner.hasNext()) {
                String idCheck = scanner.next();
                if (idCheck.equals(String.valueOf(searchId))) {
                    idSearchExistence = true;
                    break;
                }
                scanner.nextLine();
            }
            scanner.close();
            if (!idSearchExistence)
                throw new Exception("entered id was not found");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return; // means there are file not found exception
        }

        // checks field existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");

            // reading the file
            switch (field) {
                case "id" -> {
                    fieldExistence = true;
                    fieldNum = 1;
                    while (scanner.hasNext()) {
                        String idCheck = scanner.next();
                        if (idCheck.equals(value))
                            throw new Exception("entered id is already found in the courses database file");
                        scanner.nextLine();
                    }
                }
                case "name" -> {
                    fieldExistence = true;
                    fieldNum = 2;
                }
                case "department" -> {
                    fieldExistence = true;
                    fieldNum = 3;
                }
                case "grade" -> {
                    fieldExistence = true;
                    fieldNum = 4;
                }
                case "year" -> {
                    fieldExistence = true;
                    fieldNum = 5;
                }
            }

            scanner.close();


            if (!fieldExistence)
                throw new Exception("entered field is not exist\n" +
                        "course fields are: " +
                        "(id - name - department - grade - year)");
            else {
                String tempFile = "temp.txt";
                File oldFile = new File(filePath);
                File newFile = new File(tempFile);

                // data record will be updated
                String id, name, department, grade, year;

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
                    Scanner read = new Scanner(oldFile);
                    read.useDelimiter(",");
                    while (read.hasNext()) {
                        id = read.next();
                        name = read.next();
                        department = read.next();
                        grade = read.next();
                        year = read.next();

                        if (id.equals(String.valueOf(searchId))) {
                            // all the coming conditions means insert the new value to its field
                            // else means id was not found so write the data as it (don't update)

                            if (fieldNum == 1) {
                                course.setId(Integer.parseInt(value));
                                writer.write("," + value + "," + name + "," + department + "," + grade + "," + year);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 2) {
                                course.setName(value);
                                writer.write("," + id + "," + value + "," + department + "," + grade + "," + year);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 3) {
                                course.setDepartment(value);
                                writer.write("," + id + "," + name + "," + value + "," + grade + "," + year);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 4) {
                                course.setGrade(Float.parseFloat(grade));
                                writer.write("," + id + "," + name + "," + department + "," + value + "," + year);
                                System.out.println("record updated successfully");
                            }
                            else {
                                course.setYear(Integer.parseInt(value));
                                writer.write("," + id + "," + name + "," + department + "," + grade + "," + value);
                                writer.newLine();
                                System.out.println("record updated successfully");
                            }

                        } else {
                            // no update happened (id was not found) so write the data as it
                            writer.write("," + id + "," + name + "," + department + "," + grade + "," + year);
                        }
                    }
                    read.close();
                    writer.close();
                    oldFile.delete();
                    File myFile = new File(filePath);
                    newFile.renameTo(myFile);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file was not found");
        }
    }
    public static void deleteCourseData(String filePath, int searchId) {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        // data record will be deleted
        String id, name, department, grade, year;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            Scanner read = new Scanner(oldFile);
            read.useDelimiter(",");

            // to check if the record is exist to delete it
            boolean checkRecordExistence = false;

            while (read.hasNext()) {
                id = read.next();
                name = read.next();
                department = read.next();
                grade = read.next();
                year = read.next();

                if (id.equals(String.valueOf(searchId))) {
                    System.out.println("field deleted successfully");
                    checkRecordExistence = true;
                }

                else
                    writer.write("," + id + "," + name + "," + department + "," + grade + "," + year);
            }

            read.close();
            writer.close();
            oldFile.delete();
            File file = new File(filePath);
            newFile.renameTo(file);

            if (!checkRecordExistence)
                throw new Exception("record was not found");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void listAllCourses (String filePath, String department) throws Exception {
        Course course = new Course();
        course.setDepartment(department);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");

        boolean deptContents = false;
        String id, name, dept, grade, year;
        while (scanner.hasNext()) {
            id = scanner.next();
            name = scanner.next();
            dept = scanner.next();
            grade = scanner.next();
            year = scanner.next();
            if (dept.equals(department)) {
                deptContents = true;
                JTextArea fild = new JTextArea(id + "\t\t" + name + "\t\t" + grade + "\t\t"  + year);
                JOptionPane.showMessageDialog(null,fild);
            }
        }
        if (!deptContents)
            throw new Exception("this department doesn't has courses yet\n");

        scanner.close();
    }

    public static boolean insertStdData(String filePath,
                                     int id,
                                     long nationalId,
                                     int age,
                                     int currentYear,
                                     String phone,
                                     String email,
                                     String fName,
                                     String lName,
                                     String gender,
                                     String department,
                                     String address) throws Exception {
        File file = new File(filePath);
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(id).equals(read.next())) {
                    JOptionPane.showMessageDialog(null,"id is already exist!");
                    throw new Exception("id is already exist!");
                } else if (String.valueOf(nationalId).equals(read.next())) {
                    JOptionPane.showMessageDialog(null,"national id is already exist!");
                    throw new Exception("national id is already exist!");
                } else if (phone.equals(read.next())) {
                    JOptionPane.showMessageDialog(null,"Phone is already exist!");
                    throw new Exception("phone is already exist!");
                } else if (email.equals(read.next())) {
                    JOptionPane.showMessageDialog(null,"Email is already exist!");
                    throw new Exception("email is already exist!");
                } else {
                    read.nextLine();//puts the cursor at the beginning of each line always reading first 4 fields of the records (rows)
                }
            }
            read.close();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.write("," + id + "," + nationalId + "," + phone + "," + email + "," + fName + "," +
                        lName + "," + gender + "," + age + "," + department + "," + currentYear + "," + address);
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Record added successfully");
                writer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }



//    public static void insertStdData(String filePath,
//                                     int id,
//                                     long nationalId,
//                                     int age,
//                                     int currentYear,
//                                     String phone,
//                                     String email,
//                                     String fName,
//                                     String lName,
//                                     String gender,
//                                     String department,
//                                     String address) throws Exception {
//        File file = new File(filePath);
//        Scanner read;
//        try {
//            read = new Scanner(file);
//            read.useDelimiter(",");
//            while (read.hasNext()) {
//                if (String.valueOf(id).equals(read.next())) {
//                    JOptionPane.showMessageDialog(null,"id is already exist!");
//                    throw new Exception("id is already exist!");
//                } else if (String.valueOf(nationalId).equals(read.next())) {
//                    JOptionPane.showMessageDialog(null,"national id is already exist!");
//                    throw new Exception("national id is already exist!");
//                } else if (phone.equals(read.next())) {
//                    JOptionPane.showMessageDialog(null,"Phone is already exist!");
//                    throw new Exception("phone is already exist!");
//                } else if (email.equals(read.next())) {
//                    JOptionPane.showMessageDialog(null,"Email is already exist!");
//                    throw new Exception("email is already exist!");
//                } else {
//                    read.nextLine();//puts the cursor at the beginning of each line always reading first 4 fields of the records (rows)
//                }
//            }
//            read.close();
//            try {
//                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
//                writer.write("," + id + "," + nationalId + "," + phone + "," + email + "," + fName + "," +
//                        lName + "," + gender + "," + age + "," + department + "," + currentYear + "," + address);
//                writer.newLine();
//                JOptionPane.showMessageDialog(null, "Record added successfully");
//                writer.close();
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public static void updateStdData(String filePath, int searchId, String field, String value) throws Exception {
        Student student = new Student(searchId);// to check if the search id is already exist in the file or not
        boolean idSearchExistence = false;// to check if the field is exist in the file or not
        boolean fieldExistence = false;
        int fieldNum = 0;// to specify field number
        File file = new File(filePath);// checks searching id existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {// reading the file
                String idCheck = scanner.next();
                if (idCheck.equals(String.valueOf(searchId))) {
                    idSearchExistence = true;
                    break;
                }
                scanner.nextLine();
            }
            scanner.close();
            if (!idSearchExistence)
                throw new Exception("entered id was not found");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return; // means there are file not found exception
        }
        // checks field existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            switch (field) {// reading the file
                case "id" -> {
                    fieldExistence = true;
                    fieldNum = 1;
                    while (scanner.hasNext()) {
                        String idCheck = scanner.next();
                        if (idCheck.equals(value))
                            throw new Exception("entered id is already found in the student database file");
                        scanner.nextLine();
                    }
                }
                case "natId" -> {
                    fieldExistence = true;
                    fieldNum = 2;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String natIdCheck = scanner.next();
                        if (natIdCheck.equals(value))
                            throw new Exception("entered national id is already found in the student database file");
                        scanner.nextLine();
                    }
                }
                case "phone" -> {
                    fieldExistence = true;
                    fieldNum = 3;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String field2 = scanner.next();
                        String phone = scanner.next();
                        if (phone.equals(value))
                            throw new Exception("entered phone is already found in the student database file");
                        scanner.nextLine();
                    }
                }
                case "email" -> {
                    fieldExistence = true;
                    fieldNum = 4;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String field2 = scanner.next();
                        String field3 = scanner.next();
                        String email = scanner.next();
                        if (email.equals(value))
                            throw new Exception("entered email is already found in the student database file");
                        scanner.nextLine();
                    }
                }
                case "fName" -> {
                    fieldExistence = true;
                    fieldNum = 5;
                }
                case "lName" -> {
                    fieldExistence = true;
                    fieldNum = 6;
                }
                case "gender" -> {
                    fieldExistence = true;
                    fieldNum = 7;
                }
                case "age" -> {
                    fieldExistence = true;
                    fieldNum = 8;
                }
                case "department" -> {
                    fieldExistence = true;
                    fieldNum = 9;
                }
                case "currentYear" -> {
                    fieldExistence = true;
                    fieldNum = 10;
                }
                case "address" -> {
                    fieldExistence = true;
                    fieldNum = 11;
                }
            }
            scanner.close();
            if (!fieldExistence)
                throw new Exception("entered field is not exist\n" +
                        "student fields are: " +
                        "(id - natId - phone - email - fName - lName - gender - age - department - currentYear - address)");
            else {
                String tempFile = "temp.txt";
                File oldFile = new File(filePath);
                File newFile = new File(tempFile);
                // data record will be updated
                String id, nationalId, fName, lName, gender, age, phone, department, currentYear, email, address;
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
                    Scanner read = new Scanner(oldFile);
                    read.useDelimiter(",");
                    while (read.hasNext()) {
                        id = read.next();
                        nationalId = read.next();
                        phone = read.next();
                        email = read.next();
                        fName = read.next();
                        lName = read.next();
                        gender = read.next();
                        age = read.next();
                        department = read.next();
                        currentYear = read.next();
                        address = read.next();

                        if (id.equals(String.valueOf(searchId))) {
                            // all the coming conditions means insert the new value to its field
                            // else means id was not found so write the data as it (don't update)
                            if (fieldNum == 1) {
                                student.setId(Integer.parseInt(value));
                                writer.write("," + value + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 2) {
                                student.setNatId(Integer.parseInt(value));
                                writer.write("," + id + "," + value + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 3) {
                                student.setPhone(value);
                                writer.write("," + id + "," + nationalId + "," + value + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 4) {
                                student.setEmail(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + value +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 5) {
                                student.setFName(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + value + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 6) {
                                student.setLName(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + value + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 7) {
                                student.setGender(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + value + "," + age +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 8) {
                                student.setAge(Integer.parseInt(value));
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + value +
                                        "," + department + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 9) {
                                student.setDepartment(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + value + "," + currentYear + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 10) {
                                student.setYear(Integer.parseInt(value));
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + value + "," + address);
                                System.out.println("record updated successfully");
                            }
                            else {
                                student.setAddress(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + department + "," + currentYear + "," + value);
                                writer.newLine();
                                System.out.println("record updated successfully");
                            }
                        } else {
                            // no update happened (id was not found) so write the data as it
                            writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                    "," + fName + "," + lName + "," + gender + "," + age +
                                    "," + department + "," + currentYear + "," + address);
                        }
                    }
                    read.close();
                    writer.close();
                    oldFile.delete();
                    File myFile = new File(filePath);
                    newFile.renameTo(myFile);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file was not found");
        }
    }
    public static void deleteStdData(String filePath, int searchId) {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        // data record will be deleted
        String id, nationalId, fName, lName, gender, age, phone, department, currentYear, email, address;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            Scanner read = new Scanner(oldFile);
            read.useDelimiter(",");
            // to check if the record is exist to delete it
            boolean checkRecordExistence = false;
            while (read.hasNext()) {
                id = read.next();
                nationalId = read.next();
                phone = read.next();
                email = read.next();
                fName = read.next();
                lName = read.next();
                gender = read.next();
                age = read.next();
                department = read.next();
                currentYear = read.next();
                address = read.next();

                if (id.equals(String.valueOf(searchId))) {
                    System.out.println("field deleted successfully");
                    checkRecordExistence = true;
                }

                else
                    writer.write("," + id + "," + nationalId + "," + phone + "," + email + "," + fName + "," +
                            lName + "," + gender + "," + age + "," + department + "," + currentYear + "," + address);
            }

            read.close();
            writer.close();
            oldFile.delete();
            File file = new File(filePath);
            newFile.renameTo(file);

            if (!checkRecordExistence)
                throw new Exception("record was not found");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void listStd (String filePath, int searchId) throws Exception {
        if (searchId < 1){
            JOptionPane.showMessageDialog(null,"Entered id is not valid");
            return;
        }

        boolean idExistance = false;
        String id, nationalId, fName, lName, gender, age, phone, department, currentYear, email, address;

        File file = new File(filePath);
        Scanner read = new Scanner(file);
        read.useDelimiter(",");

        while (read.hasNext()) {
            id = read.next();
            nationalId = read.next();
            phone = read.next();
            email = read.next();
            fName = read.next();
            lName = read.next();
            gender = read.next();
            age = read.next();
            department = read.next();
            currentYear = read.next();
            address = read.next();

            if (id.equals(String.valueOf(searchId))) {
                idExistance = true;
                JTextArea fild =new JTextArea(id + "\t\t" + nationalId + "\t\t" + phone + "\t\t" + email + "\t\t"  + fName + "\t\t" + lName +
                        "\t\t" + gender + "\t\t" + age + "\t\t" + department + "\t\t" + currentYear + "\t\t" + address);
                JOptionPane.showMessageDialog(null,fild);
            }
        }
        if (!idExistance)
            throw new Exception("id was not found");
    }
    public static void listAllStudents (String filePath) throws Exception {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        String id, nationalId, fName, lName, gender, age, phone, department, currentYear, email, address;
        while (scanner.hasNext()) {
            id = scanner.next();
            nationalId = scanner.next();
            phone = scanner.next();
            email = scanner.next();
            fName = scanner.next();
            lName = scanner.next();
            gender = scanner.next();
            age = scanner.next();
            department = scanner.next();
            currentYear = scanner.next();
            address = scanner.next();


            JTextArea fild = new JTextArea(id+"\t"+nationalId+"\t"+phone+"\t"+email+"\t"+fName+"\t"+lName+"\t"+gender+"\t"+age+"\t"+department+"\t"+currentYear+"\t"+address);
            JOptionPane.showMessageDialog(null,fild);
        }
        scanner.close();
    }

    public static void insertTeacherData(String filePath,
                                         int id,
                                         long nationalId,
                                         int age,
                                         String phone,
                                         String email,
                                         String fName,
                                         String lName,
                                         String gender,
                                         float salary,
                                         String address) throws Exception {
        // taking object of teacher to do validations

        Teacher teacher = new Teacher(id, nationalId, fName, lName, age, gender,
                phone, email, address, salary);

        // reading the file and see if inserted unique data is found in the database file

        File file = new File(filePath);
        Scanner read;

        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {

                if (String.valueOf(teacher.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                }

                else if (String.valueOf(teacher.getNatId()).equals(read.next())) {
                    throw new Exception("national id is already exist!");
                }

                else if (teacher.getPhone().equals(read.next())) {
                    throw new Exception("phone is already exist!");
                }

                else if (teacher.getEmail().equals(read.next())) {
                    throw new Exception("email is already exist!");
                }

                else {
                    read.nextLine(); // puts the cursor at the beginning of each line
                    // always reading first 4 fields of the records (rows)
                }
            }
            read.close();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.write("," + id + "," + nationalId + "," + phone + "," + email + "," + fName + "," +
                        lName + "," + gender + "," + age + "," + address + "," + salary);
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
    public static void updateTeacherData(String filePath, int searchId, String field, String value) throws Exception {
        Teacher teacher = new Teacher(searchId);

        // to check if the search id is already exist in the file or not
        boolean idSearchExistence = false;
        // to check if the field is exist in the file or not
        boolean fieldExistence = false;

        // to specify field number
        int fieldNum = 0;
        File file = new File(filePath);

        // checks searching id existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");

            // reading the file
            while (scanner.hasNext()) {
                String idCheck = scanner.next();
                if (idCheck.equals(String.valueOf(searchId))) {
                    idSearchExistence = true;
                    break;
                }
                scanner.nextLine();
            }
            scanner.close();
            if (!idSearchExistence)
                throw new Exception("entered id was not found");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return; // means there are file not found exception
        }

        // checks field existence
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");

            // reading the file
            switch (field) {
                case "id" -> {
                    fieldExistence = true;
                    fieldNum = 1;
                    while (scanner.hasNext()) {
                        String idCheck = scanner.next();
                        if (idCheck.equals(value))
                            throw new Exception("entered id is already found in the teacher database file");
                        scanner.nextLine();
                    }
                }
                case "natId" -> {
                    fieldExistence = true;
                    fieldNum = 2;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String natIdCheck = scanner.next();
                        if (natIdCheck.equals(value))
                            throw new Exception("entered national id is already found in the teacher database file");
                        scanner.nextLine();
                    }
                }
                case "phone" -> {
                    fieldExistence = true;
                    fieldNum = 3;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String field2 = scanner.next();
                        String phone = scanner.next();
                        if (phone.equals(value))
                            throw new Exception("entered phone is already found in the teacher database file");
                        scanner.nextLine();
                    }
                }
                case "email" -> {
                    fieldExistence = true;
                    fieldNum = 4;
                    while (scanner.hasNext()) {
                        String field1 = scanner.next();
                        String field2 = scanner.next();
                        String field3 = scanner.next();
                        String email = scanner.next();
                        if (email.equals(value))
                            throw new Exception("entered email is already found in the teacher database file");
                        scanner.nextLine();
                    }
                }
                case "fName" -> {
                    fieldExistence = true;
                    fieldNum = 5;
                }
                case "lName" -> {
                    fieldExistence = true;
                    fieldNum = 6;
                }
                case "gender" -> {
                    fieldExistence = true;
                    fieldNum = 7;
                }
                case "age" -> {
                    fieldExistence = true;
                    fieldNum = 8;
                }
                case "address" -> {
                    fieldExistence = true;
                    fieldNum = 9;
                }
                case "salary" -> {
                    fieldExistence = true;
                    fieldNum = 10;
                }
            }

            scanner.close();


            if (!fieldExistence)
                throw new Exception("entered field is not exist\n" +
                        "teacher fields are: " +
                        "(id - natId - phone - email - fName - lName - gender - age - address - salary)");
            else {
                String tempFile = "temp.txt";
                File oldFile = new File(filePath);
                File newFile = new File(tempFile);

                // data record will be updated
                String id, nationalId, fName, lName, gender, age, phone, email, address, salary;

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
                    Scanner read = new Scanner(oldFile);
                    read.useDelimiter(",");
                    while (read.hasNext()) {
                        id = read.next();
                        nationalId = read.next();
                        phone = read.next();
                        email = read.next();
                        fName = read.next();
                        lName = read.next();
                        gender = read.next();
                        age = read.next();
                        address = read.next();
                        salary = read.next();

                        if (id.equals(String.valueOf(searchId))) {
                            // all the coming conditions means insert the new value to its field
                            // else means id was not found so write the data as it (don't update)

                            if (fieldNum == 1) {
                                teacher.setId(Integer.parseInt(value));
                                writer.write("," + value + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 2) {
                                teacher.setNatId(Integer.parseInt(value));
                                writer.write("," + id + "," + value + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 3) {
                                teacher.setPhone(value);
                                writer.write("," + id + "," + nationalId + "," + value + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 4) {
                                teacher.setEmail(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + value +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 5) {
                                teacher.setFName(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + value + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 6) {
                                teacher.setLName(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + value + "," + gender + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 7) {
                                teacher.setGender(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + value + "," + age +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 8) {
                                teacher.setAge(Integer.parseInt(value));
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + value +
                                        "," + address + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else if (fieldNum == 9) {
                                teacher.setAddress(value);
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + value + "," + salary);
                                System.out.println("record updated successfully");
                            }
                            else {
                                teacher.setSalary(Integer.parseInt(value));
                                writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                        "," + fName + "," + lName + "," + gender + "," + age +
                                        "," + address + "," + value);
                                writer.newLine();
                                System.out.println("record updated successfully");
                            }

                        } else {
                            // no update happened (id was not found) so write the data as it
                            writer.write("," + id + "," + nationalId + "," + phone + "," + email +
                                    "," + fName + "," + lName + "," + gender + "," + age +
                                    "," + address + "," + salary);
                        }
                    }
                    read.close();
                    writer.close();
                    oldFile.delete();
                    File myFile = new File(filePath);
                    newFile.renameTo(myFile);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file was not found");
        }
    }
    public static void deleteTeacherData(String filePath, int searchId) {
        String tempFile = "temp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        // data record will be deleted
        String id, nationalId, fName, lName, gender, age, phone, email, address, salary;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            Scanner read = new Scanner(oldFile);
            read.useDelimiter(",");

            // to check if the record is exist to delete it
            boolean checkRecordExistence = false;

            while (read.hasNext()) {
                id = read.next();
                nationalId = read.next();
                phone = read.next();
                email = read.next();
                fName = read.next();
                lName = read.next();
                gender = read.next();
                age = read.next();
                address = read.next();
                salary = read.next();

                if (id.equals(String.valueOf(searchId))) {
                    System.out.println("field deleted successfully");
                    checkRecordExistence = true;
                }

                else
                    writer.write("," + id + "," + nationalId + "," + phone + "," + email + "," + fName + "," +
                            lName + "," + gender + "," + age + "," + address + "," + salary);
            }

            read.close();
            writer.close();
            oldFile.delete();
            File file = new File(filePath);
            newFile.renameTo(file);

            if (!checkRecordExistence)
                throw new Exception("record was not found");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public static void createTeacherAccount(int id, String userName, String password) throws Exception {
        Teacher teacher = new Teacher(userName, password);

        // validating entered data
        teacher.setId(id);
        teacher.setUserName(userName);
        teacher.setPassword(password);
        File file = new File("teacherLogin.txt");
        Scanner read;
        // reading the file and see if inserted unique data is found in the database file
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {

                if (String.valueOf(teacher.getId()).equals(read.next())) {
                    throw new Exception("id is already exist!");
                }

                if (teacher.getUserName().equals(read.next())) {
                    throw new Exception("username is already exist!");
                }

                else {
                    read.nextLine(); // puts the cursor at the beginning of each line
                    // always reading first field of the records (rows)
                }
            }
            read.close();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("," + teacher.getId() + "," + teacher.getUserName() + "," + teacher.getPassword());
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
    public static void listTeacher (String filePath, int searchId) throws Exception {
        if (searchId < 1)
            throw new Exception("entered id is not valid");
        boolean idExistance = false;
        String id, nationalId, fName, lName, gender, age, phone, email, address, salary;

        File file = new File(filePath);
        Scanner read = new Scanner(file);

        read.useDelimiter(",");
        while (read.hasNext()) {
            id = read.next();
            nationalId = read.next();
            phone = read.next();
            email = read.next();
            fName = read.next();
            lName = read.next();
            gender = read.next();
            age = read.next();
            salary = read.next();
            address = read.next();

            if (id.equals(String.valueOf(searchId))) {
                idExistance = true;
                JTextArea fild = new JTextArea(id + "\t\t" + nationalId + "\t\t" + phone  + "\t\t" + email  + "\t\t" + fName  + "\t\t" +
                        lName  + "\t\t" +  gender + "\t\t" + age  + "\t\t" + salary + "\t\t" + address);
                JOptionPane.showMessageDialog(null,fild);
            }
        }
        if (!idExistance)
            throw new Exception("entered id was not found");
    }
    public static void listAllTeachers (String filePath) throws Exception {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        String id, nationalId, fName, lName, gender, age, phone, email, salary, address;

        while (scanner.hasNext()) {
            id = scanner.next();
            nationalId = scanner.next();
            phone = scanner.next();
            email = scanner.next();
            fName = scanner.next();
            lName = scanner.next();
            gender = scanner.next();
            age = scanner.next();
            salary = scanner.next();
            address = scanner.next();
            System.out.println();

            JTextArea field = new JTextArea(id+"\t"+nationalId+"\t"+phone+"\t"+email+"\t"+fName+"\t"+lName+"\t"+gender+"\t"+age+"\t"+salary+"\t"+address);
            JOptionPane.showMessageDialog(null,field);
        }
        scanner.close();
    }
}