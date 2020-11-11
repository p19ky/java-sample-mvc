package lab3.model;

import lab3.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private Long studentId;
    private int totalCredits;

    public Student(Long studentId, int totalCredits, List<Course> enrolledCourses, String firstName, String lastName) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        List<Course> myCourses = new ArrayList<Course>();

        for (Course course: CourseRepository.getCourses())
            for (Student student:course.getStudentsEnrolled())
                if (student.getStudentId().equals(studentId)) myCourses.add(course);

        return myCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", totalCredits=" + totalCredits +
                '}';
    }
}
