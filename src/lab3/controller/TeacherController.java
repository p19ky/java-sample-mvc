package lab3.controller;

import lab3.repository.TeacherRepository;

public class TeacherController {
    private final TeacherRepository teacherRepository  = new TeacherRepository("test/lab3/repository/teachersTest.txt");

    public TeacherController() {}

    public void print() {
        this.teacherRepository.printTeachers();
    }
}
