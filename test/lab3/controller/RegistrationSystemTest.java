package lab3.controller;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystemTest extends TestCase {
    private static RegistrationSystem registrationSystem;
    private static TeacherRepository teacherRepository;
    private static StudentRepository studentRepository;
    private static CourseRepository courseRepository;

    public void setUp() throws Exception {
        registrationSystem = new RegistrationSystem();
        teacherRepository = new TeacherRepository("test/lab3/repository/teachersTest.txt");
        studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testRegister() {
        Course course_result=null;
        for(Course course: CourseRepository.getCourses())
            if(course.getCourseId()==11L)
                course_result=course;

        Student student_result=null;
        for(Student student: StudentRepository.getStudents())
            if(student.getStudentId()==2L)
                student_result=student;

        boolean result_register;
        result_register = registrationSystem.register(course_result,student_result);
        assertFalse(result_register);
    }

    public void testRetrieveCoursesWithFreePlaces() {

        List<Course> courseList = new ArrayList<Course>() {};
        courseList = CourseRepository.getCourses();

        List<Course> courseList2 = new ArrayList<Course>() {};
        courseList2 = registrationSystem.retrieveCoursesWithFreePlaces();

        assertSame(courseList2.get(0).getCourseId(), courseList.get(0).getCourseId());
        assertSame(courseList2.get(1).getCourseId(), courseList.get(1).getCourseId());

    }


    public void testRetrieveStudentsEnrolledForACourse() {
        Student stundent1 = new Student(1L,5,"Krisztian", "Pinkovai");
        Student stundent2 = new Student(2L,11,"Teo", "Olteanu");

        List<Student> studentList = new ArrayList<Student>(){};
        studentList.add(stundent1);
        studentList.add(stundent2);

        Course course = new Course("MAP",11L,TeacherRepository.getTeachers().get(1),25,studentList,5);
        List<Student> studentList2 = new ArrayList<Student>(){};
        studentList2= registrationSystem.retrieveStudentsEnrolledForACourse(course);

       //Professor: Catalin Rusu
        assertSame(studentList2.get(0).getStudentId(), stundent1.getStudentId());
        assertSame(studentList2.get(1).getStudentId(), stundent2.getStudentId());

    }

    public void testGetAllCourses() {
        List<Course> courseList = new ArrayList<Course>(){};
        courseList= registrationSystem.getAllCourses();

        assertSame(courseList.get(0).getCourseId(), CourseRepository.getCourses().get(0).getCourseId());
        assertSame(courseList.get(1).getCourseId(), CourseRepository.getCourses().get(1).getCourseId());
    }
}