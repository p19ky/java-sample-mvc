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

    /**
     * @return courses in which this student is enrolled.
     */
    public List<Course> getEnrolledCourses(List<Course> listToSearchIn) {
        List<Course> myCourses = new ArrayList<Course>();

        if (listToSearchIn.isEmpty()) {
            for (Course course : CourseRepository.getCourses())
                for (Student student : course.getStudentsEnrolled())
                    if (student.getStudentId().equals(studentId)) myCourses.add(course);
        } else {
            for (Course course : CourseRepository.getCourses())
                for (Student student : course.getStudentsEnrolled())
                    if (student.getStudentId().equals(studentId)) myCourses.add(course);
        }



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
    public String customToString(List<Course> listToSearchIn) {
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
        for (Course course : this.getEnrolledCourses(listToSearchIn)) {
            student.append(String.valueOf(course.getCourseId()));
            student.append(listSplitter);
        }
        student.append("]");



        return student.toString();
    }
}
