package lab3.controller;

import junit.framework.TestCase;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

public class CourseControllerTest extends TestCase {
    private static StudentRepository studentRepository;
    private static TeacherRepository teacherRepository;
    private static CourseController courseController;

    public void setUp() throws Exception {
        studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        teacherRepository =new TeacherRepository("test/lab3/repository/teachersTest.txt");
        courseController = new CourseController("test/lab3/repository/coursesTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testSortCoursesOnCourseId() {

        courseController.sortCoursesOnCourseId();
        if(CourseRepository.courses.size()>=2) {

            int size_coursesArray = CourseRepository.courses.size();
            int i=0,j=1;
            while(i<size_coursesArray-1 && j<size_coursesArray)
            {
                assertTrue(CourseRepository.courses.get(i).getCourseId() <= CourseRepository.courses.get(j).getCourseId() );
                i++;
                j++;
            }
        }
    }

    public void testSortCoursesOnName() {

        courseController.sortCoursesOnName();
        if(CourseRepository.courses.size()>=2) {

            int size_coursesArray = CourseRepository.courses.size();
            int i=0,j=1;
            while(i<size_coursesArray-1 && j<size_coursesArray)
            {
                assertTrue(CourseRepository.courses.get(i).getName().compareToIgnoreCase(CourseRepository.courses.get(j).getName()) <=0 );
                i++;
                j++;
            }
        }
    }

    public void testSortCoursesOnMaxEnrollment() {

        courseController.sortCoursesOnMaxEnrollment();
        if(CourseRepository.courses.size()>=2) {

            int size_coursesArray = CourseRepository.courses.size();
            int i=0,j=1;
            while(i<size_coursesArray-1 && j<size_coursesArray)
            {
                assertTrue(CourseRepository.courses.get(i).getMaxEnrollment() <= CourseRepository.courses.get(j).getMaxEnrollment() );
                i++;
                j++;
            }
        }
    }

    public void testSortCoursesOnCredits() {

        courseController.sortCoursesOnCredits();
        if(CourseRepository.courses.size()>=2) {

            int size_coursesArray = CourseRepository.courses.size();
            int i=0,j=1;
            while(i<size_coursesArray-1 && j<size_coursesArray)
            {
                assertTrue(CourseRepository.courses.get(i).getCredits() <= CourseRepository.courses.get(j).getCredits() );
                i++;
                j++;
            }
        }
    }

    public void testSortCoursesOnNumberOfStudentsEnrolled() {

        courseController.sortCoursesOnNumberOfStudentsEnrolled();
        if(CourseRepository.courses.size()>=2) {

            int size_coursesArray = CourseRepository.courses.size();
            int i=0,j=1;
            while(i<size_coursesArray-1 && j<size_coursesArray)
            {
                assertTrue(CourseRepository.courses.get(i).getStudentsEnrolled().size() <= CourseRepository.courses.get(j).getStudentsEnrolled().size() );
                i++;
                j++;
            }
        }
    }
}