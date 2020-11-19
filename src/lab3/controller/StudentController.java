package lab3.controller;

import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController() {
        studentRepository = new StudentRepository("students.txt");
    }

    public StudentController(String fileName) {
        studentRepository = new StudentRepository(fileName);
    }

    /**
     * utility function for checking if given string is numeric
     * @param str given string
     * @return true if string is numeric else false
     */
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Print all students.
     */
    public void print() {
        StudentRepository.printStudents();
    }

    /**
     * Find a single Student based on id.
     */
    public Student findOneStudent(Long id) throws IOException {
        return studentRepository.findOne(id);
    }

    /**
     * Find all available students.
     */
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Save Student if not already in database.
     */
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    /**
     * Delete Student if it exists.
     */
    public Student deleteStudent(Long id) {
        return studentRepository.delete(id);
    }

    /**
     * Update Student if it exists.
     */
    public Student updateStudent(Long idForSearch, Student newStudentData) {
        return studentRepository.update(idForSearch, newStudentData);
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

    /**
     *
     * @param TypeOfFilter if "lt" return students with total credits less than given value.
     *                     if "gt" return students with total credits greater than given value.
     *                     if "eq" return students with total credits equal to given value.
     * @param totalCredits total credits to filter after.
     * return filtered list of students based on total credits.
     */
    public List<Student> filterStudentsOnTotalCredits(int totalCredits, String TypeOfFilter) {
        var students = StudentRepository.getStudents();

        Predicate<Student> byTotalCredits = null;

        if (TypeOfFilter.equals("lt"))
            byTotalCredits = student -> student.getTotalCredits() < totalCredits;
        else if (TypeOfFilter.equals("gt"))
            byTotalCredits = student -> student.getTotalCredits() > totalCredits;
        else
            byTotalCredits = student -> student.getTotalCredits() == totalCredits;

        var result = students.stream().filter(byTotalCredits)
                .collect(Collectors.toList());

        for (Student student : result)
            StudentRepository.printStudent(student);

        return result;
    }
}
