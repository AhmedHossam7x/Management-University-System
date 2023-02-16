package com.example.universtyregister;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane p_Main= new Pane();
        Label l1 = new Label("Welcome in DTU System");
        l1.setLayoutX(450);
        l1.setLayoutY(150);
        l1.setScaleX(3);
        l1.setScaleY(3);
        l1.setTextFill(Color.GRAY);
        ////////////////////////////////////////////////////
        Button AdminLogin = new Button("Admin Login");
        AdminLogin.setLayoutX(256);
        AdminLogin.setScaleX(2);
        AdminLogin.setScaleY(2);
        AdminLogin.setLayoutY(400);
        ////////////////////////////////////////////////////
        Button TeacherLogin = new Button("Teacher Login");
        TeacherLogin.setLayoutX(512);
        TeacherLogin.setScaleX(2);
        TeacherLogin.setScaleY(2);
        TeacherLogin.setLayoutY(400);
        ////////////////////////////////////////////////////
        Button student = new Button("Student");
        student.setLayoutX(768);
        student.setScaleX(2);
        student.setScaleY(2);
        student.setLayoutY(400);
        ////////////////////////////////////////////////////
        p_Main.getChildren().addAll(l1, AdminLogin, TeacherLogin, student);
        Scene s_Main = new Scene(p_Main, 1024, 720);

        stage.setTitle("ProjectDTU");
        stage.setScene(s_Main);
        stage.show();
        stage.setResizable(false);

        AdminLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pane p_Admin = new Pane();
                Label result_Name = new Label("UserName: ");
                result_Name.setLayoutX(350);
                result_Name.setLayoutY(150);
                result_Name.setScaleX(2);
                result_Name.setScaleY(2);
                final TextField field_Name = new TextField();
                field_Name.setLayoutX(550);
                field_Name.setLayoutY(150);
                field_Name.setScaleX(2);
                field_Name.setScaleY(1);

                Label result_Pass = new Label("Password: ");
                result_Pass.setLayoutX(350);
                result_Pass.setLayoutY(200);
                result_Pass.setScaleX(2);
                result_Pass.setScaleY(2);
                final PasswordField field_Pass = new PasswordField();
                field_Pass.setLayoutX(550);
                field_Pass.setLayoutY(200);
                field_Pass.setScaleX(2);
                field_Pass.setScaleY(1);

                Button btn_cancel= new Button("Cancel");
                btn_cancel.setLayoutX(695);
                btn_cancel.setLayoutY(250);
                btn_cancel.setScaleX(2);
                btn_cancel.setScaleY(1);
                btn_cancel.setTextFill(Color.WHEAT);
                btn_cancel.setBackground(Background.fill(Color.RED));

                Button btn_Admin = new Button("Login");
                btn_Admin.setLayoutX(500);
                btn_Admin.setLayoutY(250);
                btn_Admin.setScaleX(2);
                btn_Admin.setScaleY(1);
                btn_Admin.setBackground(Background.fill(Color.GRAY));

                p_Admin.getChildren().addAll(result_Name, field_Name, result_Pass, field_Pass, btn_Admin, btn_cancel);
                Scene s_Admin = new Scene(p_Admin, 1024, 720);
                stage.setScene(s_Admin);
                stage.show();

                btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stage.setScene(s_Main);
                        stage.show();
                    }
                });
                btn_Admin.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Admin.login("adminDatabase.txt", field_Name.getText(), field_Pass.getText());
                            Pane p_login = new Pane();
                            Button b1 = new Button("Upgrade");
                            b1.setLayoutX(250);
                            b1.setLayoutY(250);
                            b1.setScaleX(2);
                            b1.setScaleY(2);

                            Button b2 = new Button("Create Teacher");
                            b2.setLayoutX(500);
                            b2.setLayoutY(250);
                            b2.setScaleX(2);
                            b2.setScaleY(2);

                            Button b3 = new Button("ListData");
                            b3.setLayoutX(750);
                            b3.setLayoutY(250);
                            b3.setScaleX(2);
                            b3.setScaleY(2);

                            Button b5 = new Button("Delete Teacher");
                            b5.setLayoutX(350);
                            b5.setLayoutY(400);
                            b5.setScaleX(2);
                            b5.setScaleY(2);

                            Button b6 = new Button("Go Back");
                            b6.setLayoutX(690);
                            b6.setLayoutY(400);
                            b6.setScaleX(2);
                            b6.setScaleY(2);
                            b6.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    stage.setScene(s_Main);
                                    stage.show();
                                }
                            });
                            p_login.getChildren().addAll(b1, b2, b3, b5, b6);
                            Scene s_thr = new Scene(p_login, 1024, 720);
                            stage.setTitle("Admin Page");
                            stage.setScene(s_thr);
                            stage.show();

                            b1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) { try { adminInsert(); } catch (Exception e) { e.printStackTrace(); } }
                            });

                            b2.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    adminCreateTeacher();
                                }
                            });

                            b3.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) { try { adminList(); }catch (Exception e){ e.printStackTrace(); } }
                            });
                            b5.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    adminDeleteTeacherAccount();
                                }
                            });
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
            }
        });
        student.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Welcome In System. ");
                Pane p_thr = new Pane();
                Button b1 = new Button("View My Courses");
                b1.setLayoutX(150);
                b1.setLayoutY(250);
                b1.setScaleX(2);
                b1.setScaleY(2);

                Button b2 = new Button("View My Grades");
                b2.setLayoutX(450);
                b2.setLayoutY(250);
                b2.setScaleX(2);
                b2.setScaleY(2);

                Button b6 = new Button("Go Back");
                b6.setLayoutX(690);
                b6.setLayoutY(250);
                b6.setScaleX(2);
                b6.setScaleY(2);
                b6.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stage.setScene(s_Main);
                        stage.show();
                    }
                });

                p_thr.getChildren().addAll(b1,b2,b6);
                Scene s_thr = new Scene(p_thr, 1024, 720);
                stage.setTitle("Student Page");
                stage.setScene(s_thr);
                stage.show();

                b1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        studentCourses();
                    }
                });
                b2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            studentGrades();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        TeacherLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pane p_Teacher= new Pane();
                Label result_Name = new Label("UserName: ");
                result_Name.setLayoutX(350);
                result_Name.setLayoutY(150);
                result_Name.setScaleX(2);
                result_Name.setScaleY(2);
                final TextField field_Name = new TextField();
                field_Name.setLayoutX(550);
                field_Name.setLayoutY(150);
                field_Name.setScaleX(2);
                field_Name.setScaleY(1);

                Label result_Pass = new Label("Password: ");
                result_Pass.setLayoutX(350);
                result_Pass.setLayoutY(200);
                result_Pass.setScaleX(2);
                result_Pass.setScaleY(2);
                final PasswordField field_Pass = new PasswordField();
                field_Pass.setLayoutX(550);
                field_Pass.setLayoutY(200);
                field_Pass.setScaleX(2);
                field_Pass.setScaleY(1);

                Button btn_Teacher = new Button("Login");
                btn_Teacher.setLayoutX(705);
                btn_Teacher.setLayoutY(250);
                btn_Teacher.setScaleX(2);

                p_Teacher.getChildren().addAll(result_Name, field_Name, result_Pass, field_Pass, btn_Teacher);
                Scene s_Admin = new Scene(p_Teacher, 1024, 720);
                stage.setScene(s_Admin);
                stage.show();

                btn_Teacher.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Admin.login("teacherLogin.txt", field_Name.getText(), field_Pass.getText());
                            Pane t_Login= new Pane();
                            Button b1 = new Button("Attendance");
                            b1.setLayoutX(150);
                            b1.setLayoutY(250);
                            b1.setScaleX(2);
                            b1.setScaleY(2);

                            Button b2 = new Button("Quiz");
                            b2.setLayoutX(450);
                            b2.setLayoutY(250);
                            b2.setScaleX(2);
                            b2.setScaleY(2);

                            Button b3 = new Button("MidTerm");
                            b3.setLayoutX(690);
                            b3.setLayoutY(250);
                            b3.setScaleX(2);
                            b3.setScaleY(2);

                            Button b4 = new Button("Final");
                            b4.setLayoutX(350);
                            b4.setLayoutY(350);
                            b4.setScaleX(4);
                            b4.setScaleY(2);

                            Button b5 = new Button("Go Back");
                            b5.setLayoutX(650);
                            b5.setLayoutY(350);
                            b5.setScaleX(2);
                            b5.setScaleY(2);

                            t_Login.getChildren().addAll(b1, b2, b3, b4, b5);
                            Scene s_Login= new Scene(t_Login, 1024, 720);
                            stage.setTitle("Teacher Page");
                            stage.setScene(s_Login);
                            stage.show();

                            b1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    teacherAttendanceGrade();
                                }
                            });
                            b2.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    teacherQuizGrade();
                                }
                            });
                            b3.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    teacherMidTermGrade();
                                }
                            });
                            b4.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent){
                                    try {teacherFinalGrade();}catch (Exception e){e.printStackTrace();}
                                }
                            });
                            b5.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    stage.setScene(s_Main);
                                    stage.show();
                                }
                            });
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                });
            }
        });
    }
    public static void main(String[] args) throws Exception {launch();}
    public static void adminInsert() throws Exception {
        int coise = Integer.parseInt(JOptionPane.showInputDialog(
                        "1)-  insert a student\t 2)-  update a student\n"+
                        "3)-  delete a student\t 4)- insert a teacher\n"+
                        "5)-  update a teacher\t 6)- delete a teacher\n"+
                        "7)-  insert an admin\t  8)- update an admin\n" +
                        "9)-  delete an admin\t 10)- insert a course\n" +
                        "11)- update a course\t 12)- delete a course\n" +
                                "\t\t\t0)-Exit" ));
        switch (coise) {
            case 0:
                break;
            case 1:
                adminInsertStd();
                break;
            case 2:
                adminUpdateStd();
                break;
            case 3:
                adminDeleteStd();
                break;
            case 4:
                adminInsertTeacher();
                break;
            case 5:
                adminUpdateTeacher();
                break;
            case 6:
                adminDeleteTeacher();
                break;
            case 7:
                adminInsertAdmin();
                break;
            case 8:
                adminUpdateAdmin();
                break;
            case 9:
                adminDeleteAdmin();
                break;
            case 10:
                adminInsertCourse();
                break;
            case 11:
                adminUpdateCourse();
                break;
            case 12:
                adminDeleteCourse();
                break;
            default:
                JOptionPane.showMessageDialog(null,"enter one of available choices");
        }
    }
    public static void adminInsertStd() {
        Student student = new Student();
        try {
            int id = 0, age = 0, year = 0;
            long natId = 0;
            String fName = null, lName = null, phone = null, email = null, gender = null, department = null, address = null;

            boolean continueLoop = true;
            while (continueLoop) {
                try {
                    id=Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
                    student.setId(id);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    natId=Long.parseLong(JOptionPane.showInputDialog("Enter National ID: "));
                    student.setNatId(natId);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                fName=JOptionPane.showInputDialog("Enter First Name: ");
                try {
                    student.setFName(fName);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                lName=JOptionPane.showInputDialog("Enter Last Name: ");
                try {
                    student.setLName(lName);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                age=Integer.parseInt(JOptionPane.showInputDialog("Enter Age: "));
                try {
                    student.setAge(age);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                year=Integer.parseInt(JOptionPane.showInputDialog("Enter your Year in college: "));
                try {
                    student.setYear(year);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                phone=JOptionPane.showInputDialog("Enter Number Phone: ");
                try {
                    student.setPhone(phone);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                email=JOptionPane.showInputDialog("Enter Email: ");
                try {
                    student.setEmail(email);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                gender=JOptionPane.showInputDialog("Enter Gender: ");
                try {
                    student.setGender(gender);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                department=JOptionPane.showInputDialog("Enter Department: ");
                try {
                    student.setDepartment(department);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                address=JOptionPane.showInputDialog("Enter Address: ");
                try {
                    student.setAddress(address);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            Admin.insertStdData("studentDatabase.txt", id, natId, age, year, phone,
                    email, fName, lName, gender, department, address);
        } catch (InputMismatchException e) {
            System.out.println("\nenter one of available choices\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void adminUpdateStd() {
        int searchId=0;
        String field = null, value = null;
        while (true) {
            try {
                searchId=Integer.parseInt(JOptionPane.showInputDialog("enter id of the student to update his record: "));
                field = JOptionPane.showInputDialog("enter field you want to update: ");
                value = JOptionPane.showInputDialog("enter value: ");
                Admin.updateStdData("studentDatabase.txt", searchId, field, value);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminDeleteStd() {
        int searchId=0;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("enter id of the student to delete his record: ",searchId));
                Admin.deleteStdData("studentDatabase.txt", searchId);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminInsertTeacher() {
        Teacher teacher = new Teacher();
        int id = 0, age = 0;
        float salary = 0;
        long natId = 0;
        String fName = null, lName = null, phone = null, email = null, gender = null, address = null;
        try {
            boolean continueLoop = true;
            while (continueLoop) {
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
                    teacher.setId(id);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    natId = Long.parseLong(JOptionPane.showInputDialog("enter national id: "));
                    teacher.setNatId(natId);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    fName = JOptionPane.showInputDialog("Enter first name: ");
                    teacher.setFName(fName);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    lName = JOptionPane.showInputDialog("Enter last name: ");
                    teacher.setLName(lName);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    age = Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
                    teacher.setAge(age);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    phone = JOptionPane.showInputDialog("Enter phone number: ");
                    teacher.setPhone(phone);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    email = JOptionPane.showInputDialog("Enter email: ");
                    teacher.setEmail(email);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    gender = JOptionPane.showInputDialog("Enter gender: ");
                    teacher.setGender(gender);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    salary = Float.parseFloat(JOptionPane.showInputDialog("Enter salary: "));
                    teacher.setSalary(salary);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    address = JOptionPane.showInputDialog("Enter address: ");
                    teacher.setAddress(address);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            Admin.insertTeacherData("teacherDatabase.txt", id, natId, age, phone,
                    email, fName, lName, gender, salary, address);
        } catch (InputMismatchException e) {
            System.out.println("\nenter one of available choices\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void adminUpdateTeacher() {
        int searchId=0;
        String field, value;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the teacher to update his record: "));
                field = JOptionPane.showInputDialog("Enter field you want to update: ");
                value = JOptionPane.showInputDialog("Enter value: ");
                Admin.updateTeacherData("teacherDatabase.txt", searchId, field, value);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminDeleteTeacher() {
        int searchId;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the teacher to delete his record: "));
                Admin.deleteTeacherData("teacherDatabase.txt", searchId);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminInsertAdmin() {
        Admin admin = new Admin();
        int id = 0;
        String userName = null, password = null;
        try {
            boolean continueLoop = true;
            while (continueLoop) {
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
                    admin.setId(id);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    userName = JOptionPane.showInputDialog("Enter UserName: ");
                    admin.setUserName(userName);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    password = JOptionPane.showInputDialog("Password: ");
                    admin.setPassword(password);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            Admin.insertAdminData("adminDatabase.txt", id, userName, password);
        } catch (InputMismatchException e) {
            System.out.println("\nenter one of available choices\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void adminUpdateAdmin() {
        int searchId;
        String field, value;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the admin to update his record: "));
                field = JOptionPane.showInputDialog("Enter field you want to update: ");
                value = JOptionPane.showInputDialog("Enter Value: ");
                Admin.updateAdminData("adminDatabase.txt", searchId, field, value);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminDeleteAdmin() {
        int searchId;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the admin to delete his record: "));
                Admin.deleteAdminData("adminDatabase.txt", searchId);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminInsertCourse() {
        Course course = new Course();
        int id = 0, year = 0;
        String name = null, department = null;
        float grade = 0;
        try {
            boolean continueLoop = true;
            while (continueLoop) {
                try {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Enter course id: "));
                    course.setId(id);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    name = JOptionPane.showInputDialog("Enter Course Name: ");
                    course.setName(name);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    department = JOptionPane.showInputDialog("Enter Course Department: ");
                    course.setDepartment(department);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    grade = Float.parseFloat(JOptionPane.showInputDialog("Enter course final grade: "));
                    course.setGrade(grade);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            continueLoop = true;
            while (continueLoop) {
                try {
                    year = Integer.parseInt(JOptionPane.showInputDialog("Enter Course Year: "));
                    course.setYear(year);
                    continueLoop = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            Admin.insertCourseData("courseDatabase.txt", id, name, department, grade, year);
        } catch (InputMismatchException e) {
            System.out.println("\nenter one of available choices\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void adminUpdateCourse() {
        int searchId;
        String field, value;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the course to update his record: "));
                field = JOptionPane.showInputDialog("Enter field you want to update: ");
                value = JOptionPane.showInputDialog("Enter Value: ");
                Admin.updateCourseData("courseDatabase.txt", searchId, field, value);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminDeleteCourse() {
        int searchId;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the course to delete his record: "));
                Admin.deleteCourseData("courseDatabase.txt", searchId);
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    public void adminList() throws Exception {
        int choice = 0;
        boolean loop= true;
        while (loop) {
            try {
                choice = Integer.parseInt(JOptionPane.showInputDialog("1- list all students\t\t 2- list a specific student\n" +
                        "3- list all teachers\t\t 4- list a specific teacher\n" +
                        "5- list all courses\t\t 6- go back"));
            } catch (InputMismatchException e) {
                System.out.println("\nenter one of available choices\n");
                adminList();
            }
            switch (choice) {
                case 1:
                    Admin.listAllStudents("studentDatabase.txt");
                    break;
                case 2: {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the student id: "));
                        Admin.listStd("studentDatabase.txt", id);
                        break;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
                case 3:
                    Admin.listAllTeachers("teacherDatabase.txt");
                    break;
                case 4: {
                    try {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the teacher id: "));
                        Admin.listTeacher("teacherDatabase.txt", id);
                        break;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,e);
                    }
                    break;
                }
                case 5:
                    try {
                        String department = JOptionPane.showInputDialog("Enter department: ");
                        Admin.listAllCourses("courseDatabase.txt", department);
                        break;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,e);
                    }
                case 6:
                    loop= false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Enter one of available choices");
            }
        }
    }
    public static void adminCreateTeacher() {
        while (true) {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter teacher id: "));
                String userName = JOptionPane.showInputDialog("Enter teacher userName: ");
                System.out.print ("enter teacher password: ");
                String password = JOptionPane.showInputDialog("Enter teacher password: ");
                Admin.createTeacherAccount(id, userName, password);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void adminDeleteTeacherAccount() {
        int searchId;
        while (true) {
            try {
                searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter id of the teacher to delete his record: "));
                Admin.deleteAdminData("teacherLogin.txt", searchId);
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void teacherAttendanceGrade() {
        boolean isFound = false;
        int id;
        String course;
        float grade;
        while (true) {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter student id: "));
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        File file = new File("studentDatabase.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(id).equals(read.next())) {
                    isFound = true;
                    break;
                }
                read.nextLine();
            }
            if (!isFound)
                throw new Exception("id was not found in student database file");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        isFound = false; // resetting
        while (true) {
            try {
                course = JOptionPane.showInputDialog("Enter course: ");
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        File file2 = new File("courseDatabase.txt");
        Scanner read2;
        try {
            read2 = new Scanner(file2);
            read2.useDelimiter(",");
            while (read2.hasNext()) {
                String field = read2.next();
                if (course.equals(read2.next())) {
                    isFound = true;
                    break;
                }
                read2.nextLine();
            }
            if (!isFound)
                throw new Exception("course was not found in course database file");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        while (true) {
            try {
                grade = Float.parseFloat(JOptionPane.showInputDialog("Enter student grade: "));
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        try {
            Teacher.markAttendance(id, course, grade);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void teacherQuizGrade() {
        boolean isFound = false;
        int id;
        String course;
        float grade;
        while (true) {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter student id: "));
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);                        }
        }
        File file = new File("studentDatabase.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(id).equals(read.next())) {
                    isFound = true;
                    break;
                }
                read.nextLine();
            }
            if (!isFound)
                throw new Exception("id was not found in student database file");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }// end
        isFound = false; // resetting
        while (true) {// taking student course and checking its existence in courses database file
            try {
                course = JOptionPane.showInputDialog("Enter course: ");
                break;
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        File file2 = new File("courseDatabase.txt");
        Scanner read2;
        try {
            read2 = new Scanner(file2);
            read2.useDelimiter(",");
            while (read2.hasNext()) {
                String field = read2.next();
                if (course.equals(read2.next())) {
                    isFound = true;
                    break;
                }
                read2.nextLine();
            }
            if (!isFound)
                throw new Exception("course was not found in course database file");
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                grade = Float.parseFloat(JOptionPane.showInputDialog("Enter student grade: "));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        try {
            Teacher.markQuiz(id, course, grade);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void teacherMidTermGrade() {
        boolean isFound = false;
        int id;
        String course;
        float grade;
        while (true) {// taking student id and checking its existence in student database file
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter student id: "));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        File file = new File("studentDatabase.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(id).equals(read.next())) {
                    isFound = true;
                    break;
                }
                read.nextLine();
            }
            if (!isFound)
                throw new Exception("id was not found in student database file");
        } catch (Exception e) {
            System.out.println(e);
        }// end
        isFound = false; // resetting
        while (true) {
            try {
                course = JOptionPane.showInputDialog("Enter course: ");
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        File file2 = new File("courseDatabase.txt");
        Scanner read2;
        try {
            read2 = new Scanner(file2);
            read2.useDelimiter(",");
            while (read2.hasNext()) {
                String field = read2.next();
                if (course.equals(read2.next())) {
                    isFound = true;
                    break;
                }
                read2.nextLine();
            }
            if (!isFound)
                throw new Exception("course was not found in course database file");
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                grade = Float.parseFloat(JOptionPane.showInputDialog("Enter student grade: "));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        try {
            Teacher.markMidTerm(id, course, grade);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void teacherFinalGrade() throws Exception {
        boolean isFound = false;
        int id;
        String course;
        float grade;
        while (true) {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Enter student id: "));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        File file = new File("studentDatabase.txt");
        Scanner read;
        try {
            read = new Scanner(file);
            read.useDelimiter(",");
            while (read.hasNext()) {
                if (String.valueOf(id).equals(read.next())) {
                    isFound = true;
                    break;
                }
                read.nextLine();
            }
            if (!isFound)
                throw new Exception("id was not found in student database file");
        } catch (Exception e) {
            System.out.println(e);
        }// end
        isFound = false; // resetting
        while (true) {
            try {
                course = JOptionPane.showInputDialog("Enter course: ");
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        File file2 = new File("courseDatabase.txt");
        Scanner read2;
        try {
            read2 = new Scanner(file2);
            read2.useDelimiter(",");
            while (read2.hasNext()) {
                String field = read2.next();
                if (course.equals(read2.next())) {
                    isFound = true;
                    break;
                }
                read2.nextLine();
            }
            if (!isFound)
                throw new Exception("course was not found in course database file");
        } catch (Exception e) {
            System.out.println(e);
        }
        while (true) {
            try {
                grade = Float.parseFloat(JOptionPane.showInputDialog("Enter student grade: "));
                break;
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
        try {
            Teacher.markFinal(id, course, grade);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void studentCourses() {
        int year = 0;
        String department = null;
        try {
            department = JOptionPane.showInputDialog("Enter department: ");
            year = Integer.parseInt(JOptionPane.showInputDialog("Enter Current Year: "));
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try {
            Student.myCourses(department, year);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void studentGrades() throws Exception {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(JOptionPane.showInputDialog("1- view my attendance grade\t2- view my quiz grade" +
                        "\n3- view my midTerm grade\t4- view my final grade"));
                switch (choice) {
                    case 1 -> viewAttendanceGrade();
                    case 2 -> viewQuizGrade();
                    case 3 -> viewMidTermGrade();
                    case 4 -> viewFinalGrade();
                    default -> JOptionPane.showMessageDialog(null,"Enter one of the available choices");
                }
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    public static void viewAttendanceGrade() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter Your ID: "));
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try {
            Student.myGrades("AttendanceGrades", id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void viewQuizGrade() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter Your ID: "));
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try {
            Student.myGrades("QuizGrades", id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void viewMidTermGrade() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter Your ID: "));
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try {
            Student.myGrades("MidTermGrades", id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public static void viewFinalGrade() {
        int id = 0;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter Your ID: "));
        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        try {
            Student.myGrades("FinalGrades", id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}