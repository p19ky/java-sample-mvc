package lab3.controller;

import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class TeacherController {
    private final TeacherRepository teacherRepository  = new TeacherRepository("teachers.txt");

    public TeacherController() {}

    public void print() {
        this.teacherRepository.printTeachers();
    }

    /**
     * sort teachers on teacherId
     */
    public void sortTeachersOnId() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (int) (t1.getTeacherId() - t2.getTeacherId()));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on firstName
     */
    public void sortTeachersOnFirstName() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (t1.getFirstName().compareToIgnoreCase(t2.getFirstName())));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on lastName
     */
    public void sortTeachersOnLastName() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (t1.getLastName().compareToIgnoreCase(t2.getLastName())));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on number of courses
     */
    public void sortTeachersOnNumberOfCourses() {
        TeacherRepository.getTeachers().sort(Comparator.comparingInt(t -> t.getCourses(new ArrayList<>()).size()));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }
}
