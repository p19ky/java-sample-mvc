package lab3.model;

import lab3.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    private Long teacherId;

    public Teacher(Long teacherId, String firstName, String lastName) {
        super(firstName, lastName);
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        List<Course> myCourses = new ArrayList<Course>();
        for (Course course: CourseRepository.getCourses()) {
            if (course.getTeacher().getTeacherId().equals(teacherId)) {
                myCourses.add(course);
            }
        }

        return myCourses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                '}';
    }
}
