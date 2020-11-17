package lab3.controller;

import lab3.model.Teacher;
import lab3.repository.TeacherRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherController {
    private final TeacherRepository teacherRepository  = new TeacherRepository("teachers.txt");

    public TeacherController() {}

    public void print() {
        TeacherRepository.printTeachers();
    }

    /**
     * find a teacher based on id.
     * @return found Teacher or null
     */
    public Teacher findOneTeacher(Long id) {
        return teacherRepository.findOne(id);
    }

    /**
     * Find all available teachers.
     * @return resulted Teachers or empty list
     */
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }


    /**
     * add a new teacher to database.
     * @return resulted Teacher or null
     */
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * @param id must not be null
     * @return deleted Teacher or null if unsuccessful
     */
    public Teacher deleteTeacher(Long id) {
        return teacherRepository.delete(id);
    }


    /**
     * @param
     * @return resulted teacher
     */
    public Teacher updateTeacher(Long idForSearch, Teacher teacher) {
        return teacherRepository.update(idForSearch, teacher);
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
