import java.io.*;

// Student class implementing Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int studentID;
    String name;
    String grade;

    // Constructor
    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    // Method to display student info
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Create a Student object
        Student student = new Student(101, "Bhumi", "A");

        // --- Serialization ---
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(student);
            System.out.println("Student object has been serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Deserialization ---
        Student deserializedStudent = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            deserializedStudent = (Student) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Display deserialized student info
        if (deserializedStudent != null) {
            System.out.println("\nStudent object has been deserialized:");
            deserializedStudent.displayInfo();
        }
    }
}
