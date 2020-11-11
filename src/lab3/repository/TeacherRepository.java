package lab3.repository;

import lab3.model.Teacher;

import java.util.List;

public class TeacherRepository {
    private String fileName;
    private static List<Teacher> teachers;

    public TeacherRepository(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(List<Teacher> teachers) {
        TeacherRepository.teachers = teachers;
    }
}
