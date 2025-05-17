
import java.util.Scanner;
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        boolean running = true;

        while (running) {
            System.out.println("\n~~~~~~ STUDENT MANAGEMENT MENU ~~~~~~");
            System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
            System.out.println("1.Add Student");
            System.out.println("2.View All Student");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit");
            System.out.println("Enter your Choice");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nFill the details to add student: ");
                    System.out.println("Enter ID: ");
                    int id = s.nextInt();
                    if (manager.searchStudentById(id) != null) {
                        System.out.println("Student with this ID already exists.");
                        break;
                    }
                    s.nextLine();
                    System.out.println("Enter Name: ");
                    String name = s.nextLine();
                    System.out.println("Enter Age: ");
                    int age = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter Course: ");
                    String course = s.nextLine();
                    System.out.println("Enter Marks: ");
                    double marks = s.nextDouble();
                    if (marks <= 0) {
                        System.out.println("Invalid marks. Give again");
                        return;
                    }
                    Student student = new Student(id, name, age, course, marks);
                    manager.addStudent(student);
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.println("\nYou want to search student by ID or Name?");
                    System.out.println("1.ID");
                    System.out.println("2.Name");
                    int ch = s.nextInt();
                    s.nextLine();
                    if(ch == 1)
                    {
                        System.out.println("Enter Student ID to search: ");
                        int searchId = s.nextInt();
                        s.nextLine();
                        Student found = manager.searchStudentById(searchId);
                        if (found != null) {
                            System.out.println("Student Found:\n" + found.display());
                        } else {
                            System.out.println("Student not found");
                        }
                    }
                    else if(ch == 2)
                    {
                        System.out.println("Enter Student Name to search: ");
                        String searchName = s.nextLine();
                        Student found = manager.searchStudentByName(searchName);
                        if (found != null) {
                            System.out.println("Student Found:\n" + found.display());
                        } else {
                            System.out.println("Student not found");
                        }
                    }
                    else
                    {
                        System.out.println("Please Enter ID or Name");
                        s.next();
                    }
                    break;

                case 4:
                    System.out.println("\nYou want to update student by ID or Name?");
                    System.out.println("1.ID");
                    System.out.println("2.Name");
                    int ch1 = s.nextInt();
                    s.nextLine();
                    if(ch1 == 1) {
                        System.out.println("Enter student ID to update: ");
                        int updateId = s.nextInt();
                        s.nextLine();
                        Student update = manager.searchStudentById(updateId);
                        if (update != null) {
                            System.out.println("Enter new Name: ");
                            String newName = s.nextLine();
                            System.out.println("Enter new Age: ");
                            int newAge = s.nextInt();
                            s.nextLine();
                            System.out.println("Enter new Course: ");
                            String newCourse = s.nextLine();
                            System.out.println("Enter new Marks: ");
                            double newMarks = s.nextDouble();
                            if (newMarks <= 0) {
                                System.out.println("Invalid marks. Give again");
                                return;
                            }
                            manager.updateStudent(updateId, newName, newAge, newCourse, newMarks);
                            System.out.println("Student Date Updated.");
                        } else {
                            System.out.println("Student Not Found.");
                        }
                    }
                        else if(ch1 == 2)
                        {
                            System.out.println("Enter student Name to update: ");
                            String updateName = s.nextLine();
                            Student update = manager.searchStudentByName(updateName);
                            if (update != null) {
                                System.out.println("Enter new ID");
                                int newId = s.nextInt();
                                s.nextLine();
                                System.out.println("Enter new Name: ");
                                String newName = s.nextLine();
                                System.out.println("Enter new Age: ");
                                int newAge = s.nextInt();
                                s.nextLine();
                                System.out.println("Enter new Course: ");
                                String newCourse = s.nextLine();
                                System.out.println("Enter new Marks: ");
                                double newMarks = s.nextDouble();
                                if (newMarks <= 0) {
                                    System.out.println("Invalid marks. Give again");
                                    return;
                                }
                                manager.updateStudent(newId, newName, newAge, newCourse, newMarks);
                                System.out.println("Student Date Updated.");
                            } else {
                                System.out.println("Student Not Found.");
                            }
                        }
                    else
                    {
                        System.out.println("Please Enter ID or Name");
                        s.next();
                    }

                    break;

                case 5:
                    System.out.println("\nYou want to delete student by ID or Name?");
                    System.out.println("1.ID");
                    System.out.println("2.Name");
                    int ch2 = s.nextInt();
                    s.nextLine();
                    if(ch2 == 1) {
                        System.out.println("Enter Student ID to delete");
                        int deleteId = s.nextInt();
                        s.nextLine();
                        if (manager.deleteStudent(deleteId)) {
                            System.out.println("Student Data Deleted.");
                        } else {
                            System.out.println("Student Not Found");
                        }
                    }
                    else if(ch2 == 2)
                    {
                        System.out.println("Enter Student Name to delete");
                        String deleteName = s.nextLine();
                        if (manager.deleteStudentByName(deleteName)) {
                            System.out.println("Student Data Deleted.");
                        } else {
                            System.out.println("Student Not Found");
                        }
                    }

                break;

                case 6:
                    System.out.println("Thank You for using Student Management System!");
                    running = false;
                    continue;

                default:
                    System.out.println("Invalid Choice. Try Again.");
                    continue;
            }
            running = askToContinue(s);
        }
        s.close();
    }
    public static boolean askToContinue(Scanner s)
    {
        while(true)
        {
            System.out.println("\nWould you like to Continue or you want to exit?");
            System.out.println("1: Continue");
            System.out.println("2: Exit");
            System.out.println("Which one you want?");
            if(s.hasNextInt()) {
                int input = s.nextInt();
                s.nextLine();
                if (input == 1) {
                    return true;
                } else if (input == 2) {
                    System.out.println("\nThank you for Using Student Management App!.");
                    System.out.println("Have a great day ahead. GoodBye!");
                    return false;
                }
            }
            else
            {
                System.out.println("Invalid Input. Please enter 1 or 2.");
                s.next();
            }
        }
    }
}
