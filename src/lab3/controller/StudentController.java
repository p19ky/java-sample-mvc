package lab3.controller;

import lab3.exceptions.InvalidStudentException;
import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;

public class StudentController {
    private final StudentRepository studentRepository = new StudentRepository("students.txt");

    public StudentController() {}

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Print all students.
     */
    public void print() {
        this.studentRepository.printStudents();
    }

    /**
     * Find a single Student based on id.
     */
    public void findOneStudent() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println();
            System.out.println("ENTER ID TO SEARCH FOR: ");
            System.out.println();

            // Reading data using readLine
            String input = reader.readLine();

            if (!isNumeric(input.trim()))
                throw new InvalidStudentException("Invalid Student id. Id must be numeric!");

            if (input.length() < 1)
                throw new InvalidStudentException("Student id is required!");

            Student student = studentRepository.findOne(Long.parseLong(input.trim()));

            if (student != null)
                System.out.println(student);
            else
                System.out.println("STUDENT ID DOES NOT EXIST!");

        } catch (IOException | InvalidStudentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Find all available students.
     */
    public void findAllStudents() {
        for (Student student : studentRepository.findAll())
            System.out.println(student);
    }

    /**
     * Save Student if not already in database.
     */
    public void saveStudent() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("\nENTER NEW STUDENT DETAILS:\n\n");
            System.out.println("FIRST NAME:\n");
            String firstNameInput = reader.readLine();

            if (firstNameInput.trim().equals(""))
                throw new InvalidStudentException("Firstname is required!");

            System.out.println("\nLAST NAME:\n\n");
            String lastNameInput = reader.readLine();

            if (lastNameInput.trim().equals(""))
                throw new InvalidStudentException("Lastname is required!");

            System.out.println("\nSTUDENT ID:\n\n");
            String studentId = reader.readLine();

            if (studentId.trim().equals(""))
                throw new InvalidStudentException("Student id is required!");

            if (!isNumeric(studentId))
                throw new InvalidStudentException("Student id should only contain numbers!");

            Student stud = studentRepository.save(new Student(Long.parseLong(studentId.trim()), 0, firstNameInput.trim(), lastNameInput.trim()));

            if (stud != null)
                System.out.println("STUDENT ALREADY EXISTS!");
            else
                System.out.println("STUDENT SUCCESSFULLY SAVED!");

        } catch (IOException | InvalidStudentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Delete Student if it exists.
     */
    public void deleteStudent() {

    }

    /**
     * Update Student if it exists.
     */
    public void updateStudent() {

    }
}
