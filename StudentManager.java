
import java.io.*;
import java.util.*;
public class StudentManager {
    private List<Student> StudentList = new ArrayList<>();
    private final String filePath="Student.txt";

    public StudentManager()
    {
        loadFromFile();
    }

    //Load Students from File
    private void loadFromFile()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String course = parts[3];
                    double marks = Double.parseDouble(parts[4]);
                    StudentList.add(new Student(id, name, age, course, marks));
                }
            }
        } catch (IOException e) {
            System.out.println("No Existing data found. Starting fresh.");
        }
    }

    //Save  students to file
    private void saveToFile()
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            for(Student s : StudentList)
            {
                bw.write(s.toString());
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file");
        }
    }

    //Add a student
    public void addStudent(Student student)
    {
        StudentList.add(student);
        saveToFile();
        System.out.println("Student Details added successfully!");
    }

    //View all students
    public void viewStudents()
    {
        if(StudentList.isEmpty())
        {
            System.out.println("No Students Data Found");
        }
        else
        {
            for( Student s: StudentList)
            {
                System.out.println(s.display());
            }
        }
    }

    //Search student by ID
    public Student searchStudentById(int id)
    {
        for( Student s : StudentList)
        {
            if(s.getId() == id)
            {
                return s;
            }
        }
        return null;
    }

    //Search student by Name
    public Student searchStudentByName(String name)
    {
        for( Student s : StudentList)
        {
            if(s.getName().equals(name))
            {
                return s;
            }
        }
        return null;
    }

    //Update student by ID
    public boolean updateStudent(int id, String name, int age, String course, double marks)
    {
        Student s = searchStudentById(id);
        if(s != null)
        {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            s.setMarks(marks);
            saveToFile();
            return true;
        }
        return false;
    }

    //Update student by Name
    public boolean updateStudentByName(int id, String name, int age, String course, double marks)
    {
        Student s = searchStudentByName(name);
        if(s != null)
        {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            s.setMarks(marks);
            saveToFile();
            return true;
        }
        return false;
    }

    //Delete student by ID
    public boolean deleteStudent(int id)
    {
        Student s = searchStudentById(id);
        if(s != null)
        {
            StudentList.remove(s);
            saveToFile();
            return true;
        }
        return false;
    }

    //Delete student by Name
    public boolean deleteStudentByName(String name)
    {
        Student s = searchStudentByName(name);
        if(s != null)
        {
            StudentList.remove(s);
            saveToFile();
            return true;
        }
        return false;
    }
}
