package lab3.model;

import java.util.List;

public class Teacher extends Person{
    private Long teacherId;
    private List<Course> courses;

    public Teacher(Long teacherId, List<Course> courses) {
        this.teacherId = teacherId;
        this.courses = courses;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", courses=" + courses +
                '}';
    }
}
