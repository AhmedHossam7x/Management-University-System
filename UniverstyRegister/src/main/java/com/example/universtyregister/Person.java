package com.example.universtyregister;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private int id;
    private long natId;
    private String fName;
    private String lName;
    private int age;
    private String address;
    private String gender;
    private String phone;
    private String email;
    private String[] lectures;
    private String[][] table;
    private String userName;
    private String password;

    public Person(){}
    public Person(int id, String userName, String password) throws Exception{
        if (id > 0){
            this.id= id;
        }else {
            throw new Exception("ID input is not valid");
        }
        if(userNameValidation(userName)){
            this.userName= userName;
        }else {
            throw new Exception("UserName must be between 8 and 20 characters");
        }
        if (passwordValidation(password)){
            this.password= password;
        }else {
            throw new Exception("Password must contain at least one digit [0-9].\n "+
                    "Password must contain at least one lowercase Latin character [a-z].\n"+
                    "Password must contain at least one uppercase Latin character [A-Z].\n"+
                    "Password must contain at least one special character like ! @ # & ( ).\n"+
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        }
    }
    public Person(int id,
                  long natId,
                  String fName,
                  String lName,
                  int age,
                  String gender,
                  String phone,
                  String email,
                  String address)throws Exception{

        boolean assignId = false;
        boolean assignNatId = false;
        boolean assignFName = false;
        boolean assignLName = false;
        boolean assignAge = false;
        boolean assignGender = false;
        boolean assignPhone = false;
        boolean assignEmail = false;
        boolean assignAddress = false;

        if (id > 0){
            assignId= true;
        }else {
            throw new Exception("ID input is not valid");
        }
        if(natIdValidation(natId)){
            assignNatId= true;
        }else {
            throw new Exception("national id must have 14 digits");
        }
        if(nameValidation(fName)){
            assignFName= true;
        }else {
            throw new Exception("first name input is not valid\nfirst name accepts only letters");
        }
        if (nameValidation(lName)){
            assignLName = true;
        } else{
            throw new Exception("last name input is not valid\nlast name accepts only letters");
        }
        if (age > 16 && age < 50){
            assignAge= true;
        }else {
            throw new Exception("age input must be between 17 and 50");
        }
        if (gender.equals("male") || gender.equals("female")){
            assignGender = true;
        }else {
            throw new Exception("gender input accepts only \"male\" or \"female\"");
        }
        if (phoneValidation(phone)){
            assignPhone = true;
        }else {
            throw new Exception("phone input must have 11 digits starts with (010 - 011 - 012 - 015)");
        }
        if (emailValidation(email)){
            assignEmail = true;
        }else {
            throw new Exception("""
                    email input is not valid
                    it must have '@' sign and '.' sign
                    for example: mohamed@yahoo.com""");
        }
        if (nameValidation(address)){
            assignAddress = true;
        }else {
            throw new Exception("address input is not valid\naddress accepts only letters");
        }

        if (assignId && assignNatId && assignFName && assignLName &&
                assignAge && assignGender && assignPhone && assignEmail && assignAddress) {
            this.id = id;
            this.natId = natId;
            this.fName = fName;
            this.lName = lName;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
            this.email = email;
            this.address = address;
        }
    }
    public Person(String userName, String password) throws Exception {
        if (userNameValidation(userName)){
            this.userName = userName;
        }else {
            throw new Exception("userName must be between 8 and 20 characters");
        }
        if (passwordValidation(password)){
            this.password= password;
        }else {
            throw new Exception(
                    """
                            Password must contain at least one digit [0-9].\n
                            Password must contain at least one lowercase Latin character [a-z].\n
                            Password must contain at least one uppercase Latin character [A-Z].\n
                            Password must contain at least one special character like ! @ # & ( ).\n
                            Password must contain a length of at least 8 characters and a maximum of 20 characters.""");
        }
    }

    public int getId() {return id;}
    public long getNatId() {return natId;}
    public String getfName() {return fName;}
    public String getlName() {return lName;}
    public int getAge() {return age;}
    public String getAddress() {return address;}
    public String getGender() {return gender;}
    public String getPhone() {return phone;}
    public String getEmail() {return email;}
    public String[] getLectures() {return lectures;}
    public String[][] getTable() {return table;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}

    public void setId(int id)throws Exception {
        if(id > 0){
            this.id= id;
        }else {
            throw new Exception("ID input is not valid");
        }
    }
    public void setNatId(long natId) throws Exception{
        if (natIdValidation(natId)){
            this.natId = natId;
        }else {
            throw new Exception("national id must have 14 digits");
        }

    }
    public void setFName(String fName) throws Exception {
        if(nameValidation(fName)){
            this.fName = fName;
        }else {
            throw new Exception("first name input is not valid\nfirst name accepts only letters");
        }
    }
    public void setLName(String lName) throws Exception{
        if(nameValidation(lName)){
            this.lName = lName;
        }else {
            throw new Exception("first name input is not valid\nfirst name accepts only letters");
        }
    }
    public void setAge(int age) throws Exception{
        if (age > 16 && age <= 58){
            this.age = age;
        }else {
            throw new Exception("age input must be between 17 and 58");
        }
    }
    public void setAddress(String address) throws Exception{
        if(nameValidation(address)){
            this.address = address;
        }else {
            throw new Exception("address input is not valid\naddress accepts only letters");
        }
    }
    public void setGender(String gender) throws Exception{
        if (gender.equals("male") || gender.equals("female")){
            this.gender = gender;
        }else {
            throw new Exception("gender input accepts only \"male\" or \"female\"");
        }
    }
    public void setPhone(String phone) throws Exception{
        if(phoneValidation(phone)){
            this.phone = phone;
        }else {
            throw new Exception("phone input must have 11 digits starts with (010 - 011 - 012 - 015)");
        }

    }
    public void setEmail(String email) throws Exception{
        if (emailValidation(email)){
            this.email = email;
        }else {
            throw new Exception("""
                    email input is not valid
                    it must have '@' sign and '.' sign
                    for example: mohamed@yahoo.com""");
        }
    }
    public void setLectures(String[] lectures) throws Exception{
        if(lecturesValidation(lectures)){
            this.lectures = lectures;
        }else {
            throw new Exception("lectures input is not valid\nlectures input accepts only letters");
        }

    }
    public void setTable(String[][] table) throws Exception{
        if(tableValidation(table)){
            this.table = table;
        }else {
            throw new Exception("table input is not valid\ntable input accepts only letters");
        }

    }
    public void setUserName(String userName) throws Exception{
        if (userNameValidation(userName)) {
            this.userName = userName;
        }else {
            throw new Exception("""
                    \nuserName must be between 8 and 20 characters
                    The first character of the username must be an alphabetic character
                    The username can only contain alphanumeric characters and underscores (_)""");
        }
    }
    public void setPassword(String password) throws Exception{
        if (passwordValidation(password)) {
            this.password = password;
        }else {
            throw new Exception(
                    """
                            Password must contain at least one digit [0-9].
                            Password must contain at least one lowercase Latin character [a-z].
                            Password must contain at least one uppercase Latin character [A-Z].
                            Password must contain at least one special character like ! @ # & ( ).
                            Password must contain a length of at least 8 characters and a maximum of 20 characters.""");
        }
    }

    private boolean emailValidation(String email){
        String emailRegex= "^[A-za-z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern= Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher= emailPattern.matcher(email);
        return matcher.find();
    }
    static boolean natIdValidation(long id) {
        if (id <= 0)
            return false;
        int countIdDigits= 0;
        while (id != 0) {
            id /= 10;
            ++countIdDigits;
        }
        return countIdDigits == 14;
    }
    private static boolean phoneValidation(String phone) {
        int countPhoneDigits = 0;
        long intPhone = Long.parseLong(phone);
        while (intPhone != 0) {
            intPhone /= 10;
            ++countPhoneDigits;
        }
        return Long.parseLong(phone) > 0 && ++countPhoneDigits == 11 &&
                phone.charAt(0) == '0' && phone.charAt(1) == '1' && (
                phone.charAt(2) == '0' || phone.charAt(2) == '1' ||
                        phone.charAt(2) == '2' || phone.charAt(2) == '5');
    }
    public static boolean nameValidation(String name) {
        return name.matches("(?i)(^[A-Za-z])((?![ .,'-]$)[A-Za-z .,'-]){0,24}$");
    }
    public static boolean lecturesValidation(String[] lectures) {
        for (String value : lectures)
            if (!value.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$"))
                return false;
        return true;
    }
    public static boolean tableValidation(String[][] table) {
        for (String[] innerArray : table)
            for (String value : innerArray)
                if (!value.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$"))
                    return false;
        return true;
    }
    public static boolean userNameValidation(String userName) {
        String regex = "^[A-Za-z]\\w{7,19}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
    public static boolean passwordValidation(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static void login(String databasePath, String userName, String password) throws Exception {
        File file = new File(databasePath);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        boolean login = false;
        while (scanner.hasNext()) {
            String id = scanner.next();
            String userNameScan = scanner.next();
            String passwordScan = scanner.next();
            if (userNameScan.equals(userName)) {
                if (passwordScan.trim().equals(password)) {
                    JOptionPane.showMessageDialog(null, "Welcome In System. ");
                    login = true;
                    scanner.close();
                    break;
                }
            }
        }
        if (!login)
            throw new Exception("username or password are not valid");
    }
}