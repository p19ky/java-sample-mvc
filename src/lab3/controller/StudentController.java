package lab3.controller;

import lab3.repository.StudentRepository;

public class StudentController {
    private final StudentRepository studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");

    public StudentController() {}

    public void print() {
        this.studentRepository.printStudents();
    }
}
