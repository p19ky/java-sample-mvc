package lab3.controller;

import lab3.exceptions.InvalidCourseException;
import lab3.exceptions.InvalidTeacherException;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static lab3.controller.StudentController.isNumeric;

public class CourseController {
    private final CourseRepository courseRepository = new CourseRepository("courses.txt");

    public CourseController() {}

    public void print() {
        this.courseRepository.printCourses();
    }

    /**
     * find a course based on id.
     */
    public Course findOneCourse(Long id) {
        return courseRepository.findOne(id);
    }


    /**
     * Find all available courses.
     */
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }


    /**
     *
     * @param course given
     * @return result course
     */
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     *
     * @param id given, should not be null
     * @return deleted course or null if cant be deleted.
     */
    public Course deleteCourse(Long id) {
        return courseRepository.delete(id);
    }

    public Course updateCourse(Long id, Course courseGiven) {
        return courseRepository.update(id, courseGiven);
    }

    /**
     * sort courses on courseId
     */
    public void sortCoursesOnCourseId() {
        CourseRepository.getCourses().sort((c1, c2) -> (int) (c1.getCourseId() - c2.getCourseId()));
        for (Course course : CourseRepository.getCourses()) {
            System.out.println(course.toString());
        }
    }

    /**
     * sort courses on course name
     */
    public void sortCoursesOnName() {
        CourseRepository.getCourses().sort((c1, c2) -> (c1.getName().compareToIgnoreCase(c2.getName())));
        for (Course course : CourseRepository.getCourses()) {
            System.out.println(course.toString());
        }
    }

    /**
     * sort courses on maxEnrollment
     */
    public void sortCoursesOnMaxEnrollment() {
        CourseRepository.getCourses().sort(Comparator.comparingInt(Course::getMaxEnrollment));
        for (Course course : CourseRepository.getCourses()) {
            System.out.println(course.toString());
        }
    }

    /**
     * sort courses on credits
     */
    public void sortCoursesOnCredits() {
        CourseRepository.getCourses().sort(Comparator.comparingInt(Course::getCredits));
        for (Course course : CourseRepository.getCourses()) {
            System.out.println(course.toString());
        }
    }

    /**
     * sort courses on number of studentsEnrolled
     */
    public void sortTeachersOnNumberOfCourses() {
        CourseRepository.getCourses().sort(Comparator.comparingInt(c -> c.getStudentsEnrolled().size()));
        for (Course course : CourseRepository.getCourses()) {
            System.out.println(course.toString());
        }
    }
}
