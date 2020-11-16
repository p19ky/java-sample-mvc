package lab3.controller;

import lab3.exceptions.InvalidStudentException;
import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.util.*;

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

            studentRepository.printStudent(student);

        } catch (IOException | InvalidStudentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Find all available students.
     */
    public void findAllStudents() {
        studentRepository.printStudents();
    }

    /**
     * Save Student if not already in database.
     */
    public void saveStudent() throws IOException{
        saveStudent : {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                System.out.println("\nENTER NEW STUDENT DETAILS:\n\n");

                System.out.println("YOU CAN EXIT ANYTIME BY ENTERING -1\n");

                System.out.println("\nFIRST NAME:\n");
                String firstNameInput = reader.readLine();

                if (firstNameInput.trim().equals("-1"))
                    break saveStudent;

                if (firstNameInput.trim().equals(""))
                    throw new InvalidStudentException("Firstname is required!");

                System.out.println("\nLAST NAME:\n\n");
                String lastNameInput = reader.readLine();

                if (lastNameInput.trim().equals("-1"))
                    break saveStudent;

                if (lastNameInput.trim().equals(""))
                    throw new InvalidStudentException("Lastname is required!");

                System.out.println("\nSTUDENT ID:\n\n");
                String studentId = reader.readLine();

                if (studentId.trim().equals("-1"))
                    break saveStudent;

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
    }

    /**
     * Delete Student if it exists.
     */
    public void deleteStudent() throws IOException {
        deleteStudent: {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                System.out.println("\nSTUDENTS AVAILABLE:\n\n");
                for (Student student : StudentRepository.getStudents()) {
                    System.out.println(student.toString());
                }

                System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                System.out.println("\nENTER STUDENT ID:\n");

                String input = reader.readLine();

                if (input.trim().equals("-1"))
                    break deleteStudent;

                if (input.trim().equals(""))
                    throw new InvalidStudentException("Student id is required!");

                Student result_student = studentRepository.delete(Long.parseLong(input.trim()));

                if (result_student == null)
                    System.out.println("STUDENT GIVEN DOES NOT EXIST!");
                else
                    System.out.println("STUDENT DELETED SUCCESSFULLY!");

            } catch (IOException | InvalidStudentException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Update Student if it exists.
     */
    public void updateStudent() {

        updateStudent: {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                System.out.println("\nSTUDENTS AVAILABLE:\n\n");
                for (Student student : StudentRepository.getStudents()) {
                    System.out.println(student.toString());
                }

                System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                System.out.println("\nENTER STUDENT ID:\n");

                String studentId = reader.readLine();

                if (studentId.trim().equals("-1"))
                    break updateStudent;

                if (studentId.trim().equals(""))
                    throw new InvalidStudentException("Student id is required!");

                System.out.println("\nENTER STUDENT NEW FIRSTNAME:\n");

                String firstName = reader.readLine();

                if (firstName.trim().equals("-1"))
                    break updateStudent;

                if (firstName.trim().equals(""))
                    throw new InvalidStudentException("Student firstname is required!");

                System.out.println("\nENTER STUDENT NEW LASTNAME:\n");

                String lastName = reader.readLine();

                if (lastName.trim().equals("-1"))
                    break updateStudent;

                if (lastName.trim().equals(""))
                    throw new InvalidStudentException("Student lastname is required!");

                Student newStudent = new Student(Long.parseLong(studentId.trim()), 0, firstName.trim(), lastName.trim());

                Student result_student = studentRepository.update(Long.parseLong(studentId.trim()), newStudent);

                if (result_student == null)
                    System.out.println("STUDENT UPDATED SUCCESSFULLY!");
                else
                    System.out.println("STUDENT UPDATED FAILED!");

            } catch (IOException | InvalidStudentException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * sort students on studentId
     */
    public void sortStudentsOnId() {
        StudentRepository.getStudents().sort((s1, s2) -> (int) (s1.getStudentId() - s2.getStudentId()));
        for (Student student : StudentRepository.getStudents()) {
            studentRepository.printStudent(student);
        }
    }

    /**
     * sort students on firstName
     */
    public void sortStudentsOnFirstName() {
        StudentRepository.getStudents().sort((s1, s2) -> (s1.getFirstName().compareToIgnoreCase(s2.getFirstName())));
        for (Student student : StudentRepository.getStudents()) {
            studentRepository.printStudent(student);
        }
    }

    /**
     * sort students on lastName
     */
    public void sortStudentsOnLastName() {
        StudentRepository.getStudents().sort((s1, s2) -> (s1.getLastName().compareToIgnoreCase(s2.getLastName())));
        for (Student student : StudentRepository.getStudents()) {
            studentRepository.printStudent(student);
        }
    }

    /**
     * sort students on totalCredits
     */
    public void sortStudentsOnTotalCredits() {
        StudentRepository.getStudents().sort(Comparator.comparingInt(Student::getTotalCredits));
        for (Student student : StudentRepository.getStudents()) {
            studentRepository.printStudent(student);
        }
    }

    /**
     * sort students on number of enrolledCourses
     */
    public void sortStudentsOnNumberOfEnrolledCourses() {
        StudentRepository.getStudents().sort(Comparator.comparingInt(s -> s.getEnrolledCourses(new ArrayList<>()).size()));
        for (Student student : StudentRepository.getStudents()) {
            studentRepository.printStudent(student);
        }
    }
}
