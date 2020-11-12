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

    public List<Course> getCourses(List<Course> listToSearchIn) {
        List<Course> myCourses = new ArrayList<Course>();

        if (listToSearchIn.isEmpty()) {
            for (Course course: CourseRepository.getCourses()) {
                if (course.getTeacher().getTeacherId().equals(teacherId)) {
                    myCourses.add(course);
                }
            }
        } else {
            for (Course course: listToSearchIn) {
                if (course.getTeacher().getTeacherId().equals(teacherId)) {
                    myCourses.add(course);
                }
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

    public String customToString(List<Course> listToSearchIn) {
        String splitter = ", ";
        String listSplitter = ";";
        StringBuilder teacher = new StringBuilder();

        //name
        teacher.append(this.getFirstName());
        teacher.append(" ");
        teacher.append(this.getLastName());
        teacher.append(splitter);

        //teacherId
        teacher.append(String.valueOf(this.teacherId));
        teacher.append(splitter);

        //list of Courses
        teacher.append("[");
        for (Course course : this.getCourses(listToSearchIn)){
            teacher.append(String.valueOf(course.getCourseId()));
            teacher.append(listSplitter);
        }
        teacher.append("]");


        return teacher.toString();
    }
}
