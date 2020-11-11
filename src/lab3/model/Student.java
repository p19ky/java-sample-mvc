package lab3.model;

import lab3.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private Long studentId;
    private int totalCredits;

    public Student(Long studentId, int totalCredits, String firstName, String lastName) {
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

    /**
     *
     * @return String value of the student which is accepted in the text file format.
     */
    public String customToString() {
        String splitter = ", ";
        String listSplitter = ";";
        StringBuilder student = new StringBuilder();

        //name
        student.append(this.getFirstName());
        student.append(" ");
        student.append(this.getLastName());
        student.append(splitter);

        //studentId
        student.append(String.valueOf(this.studentId));
        student.append(splitter);

        //totalCredits
        student.append(String.valueOf(this.totalCredits));
        student.append(splitter);

        //list of enrolledCourses
        student.append("[");
        for (Course course : getEnrolledCourses()) {
            student.append(String.valueOf(course.getCourseId()));
            student.append(listSplitter);
        }
        student.append("]");

        return student.toString();
    }
}
