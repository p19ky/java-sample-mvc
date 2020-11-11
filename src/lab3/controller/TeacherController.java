package lab3.controller;

import lab3.repository.TeacherRepository;

public class TeacherController {
    private TeacherRepository teacherRepository  = new TeacherRepository("teachers.txt");

    public TeacherController() {}

    public void print() {
        this.teacherRepository.printTeachers();
    }
}
