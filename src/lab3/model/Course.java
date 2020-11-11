package lab3.model;

import java.util.List;

public class Course{
    private String name;
    private Long courseId;
    private Teacher teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;

    public Course(String name, Long courseId, Teacher teacher, int maxEnrollment, List<Student> studentsEnrolled, int credits) {
        this.name = name;
        this.courseId = courseId;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", courseId=" + courseId +
                ", teacher=" + teacher +
                ", maxEnrollment=" + maxEnrollment +
                ", studentsEnrolled=" + studentsEnrolled +
                ", credits=" + credits +
                '}';
    }

    public String customToString() {
        String splitter = ", ";
        String listSplitter = ";";
        StringBuilder course = new StringBuilder();

        //name
        course.append(this.name);
        course.append(splitter);

        //courseId
        course.append(String.valueOf(this.courseId));
        course.append(splitter);

        //teacherId
        course.append(String.valueOf(this.teacher.getTeacherId()));
        course.append(splitter);

        //maxEnrollment
        course.append(String.valueOf(this.maxEnrollment));
        course.append(splitter);

        //list of studentsEnrolled
        course.append("[");
        for (Student student:this.studentsEnrolled) {
            course.append(String.valueOf(student.getStudentId()));
            course.append(listSplitter);
        }
        course.append("]");
        course.append(splitter);

        //credits
        course.append(String.valueOf(this.credits));

        return course.toString();
    }
}

