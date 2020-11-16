package lab3.controller;

import lab3.repository.StudentRepository;

public class StudentController {
    private final StudentRepository studentRepository = new StudentRepository("students.txt");

    public StudentController() {}

    public void print() {
        this.studentRepository.printStudents();
    }

    /**
     * Save Student if not already in database.
     */
    public void saveStudent() {}
}
