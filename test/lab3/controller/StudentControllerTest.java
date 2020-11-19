package lab3.controller;

import junit.framework.TestCase;
import lab3.model.Student;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.ArrayList;

public class StudentControllerTest extends TestCase {

    private static StudentController studentController;
    private static TeacherRepository teacherRepository;
    private static CourseRepository courseRepository;

    public void setUp() throws Exception {
        studentController = new StudentController("test/lab3/repository/studentsTest.txt");
        teacherRepository =new TeacherRepository("test/lab3/repository/teachersTest.txt");
        courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testSortStudentsOnId() {

        studentController.sortStudentsOnId();
        if(StudentRepository.students.size()>=2) {

            int size_studentArray = StudentRepository.students.size();
            int i=0,j=1;
            while(i<size_studentArray-1 && j<size_studentArray)
            {
                assertTrue(StudentRepository.students.get(i).getStudentId() <= StudentRepository.students.get(j).getStudentId());
                i++;
                j++;
            }
        }
    }

    public void testSortStudentsOnFirstName() {

        studentController.sortStudentsOnFirstName();
        if(StudentRepository.students.size()>=2) {

            int size_studentArray = StudentRepository.students.size();
            int i=0,j=1;
            while(i<size_studentArray-1 && j<size_studentArray)
            {
                assertTrue(StudentRepository.students.get(i).getFirstName().compareToIgnoreCase(StudentRepository.students.get(j).getFirstName())<=0);
                i++;
                j++;
            }
        }
    }

    public void testSortStudentsOnLastName() {

        studentController.sortStudentsOnLastName();
        if(StudentRepository.students.size()>=2) {

            int size_studentArray = StudentRepository.students.size();
            int i=0,j=1;
            while(i<size_studentArray-1 && j<size_studentArray)
            {
                assertTrue(StudentRepository.students.get(i).getLastName().compareToIgnoreCase(StudentRepository.students.get(j).getLastName())<=0);
                i++;
                j++;
            }
        }
    }

    public void testSortStudentsOnTotalCredits() {

        studentController.sortStudentsOnTotalCredits();
        if(StudentRepository.students.size()>=2) {

            int size_studentArray = StudentRepository.students.size();
            int i=0,j=1;
            while(i<size_studentArray-1 && j<size_studentArray)
            {
                assertTrue(StudentRepository.students.get(i).getTotalCredits() <= StudentRepository.students.get(j).getTotalCredits());
                i++;
                j++;
            }
        }
    }

    public void testSortStudentsOnNumberOfEnrolledCourses() {

        studentController.sortStudentsOnNumberOfEnrolledCourses();
        if(StudentRepository.students.size()>=2) {

            int size_studentArray = StudentRepository.students.size();
            int i=0,j=1;
            while(i<size_studentArray-1 && j<size_studentArray)
            {
                assertTrue(StudentRepository.students.get(i).getEnrolledCourses(new ArrayList<>()).size() <= StudentRepository.students.get(j).getEnrolledCourses(new ArrayList<>()).size());
                i++;
                j++;
            }
        }
    }

    public void testFilterStudentsOnTotalCredits() {

        var result = studentController.filterStudentsOnTotalCredits(5, "eq");

        for(Student student: result)
            assertEquals(5,student.getTotalCredits());
    }
}