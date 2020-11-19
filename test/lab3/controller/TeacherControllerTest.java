package lab3.controller;

import junit.framework.TestCase;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.ArrayList;

public class TeacherControllerTest extends TestCase {

    private static StudentRepository studentRepository;
    private static TeacherController teacherController;
    private static CourseRepository courseRepository;

    public void setUp() throws Exception {
        studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        teacherController =new TeacherController("test/lab3/repository/teachersTest.txt");
        courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testSortTeachersOnId() {

        teacherController.sortTeachersOnId();
        if(TeacherRepository.teachers.size()>=2) {

            int size_teacherArray = TeacherRepository.teachers.size();
            int i=0,j=1;
            while(i<size_teacherArray-1 && j<size_teacherArray)
            {
                assertTrue(TeacherRepository.teachers.get(i).getTeacherId() <= TeacherRepository.teachers.get(j).getTeacherId());
                i++;
                j++;
            }
        }
    }

    public void testSortTeachersOnFirstName() {

        teacherController.sortTeachersOnFirstName();
        if(TeacherRepository.teachers.size()>=2) {

            int size_teacherArray = TeacherRepository.teachers.size();
            int i=0,j=1;
            while(i<size_teacherArray-1 && j<size_teacherArray)
            {
                assertTrue(TeacherRepository.teachers.get(i).getFirstName().compareToIgnoreCase(TeacherRepository.teachers.get(j).getFirstName())<=0);
                i++;
                j++;
            }
        }
    }

    public void testSortTeachersOnLastName() {

        teacherController.sortTeachersOnLastName();
        if(TeacherRepository.teachers.size()>=2) {

            int size_teacherArray = TeacherRepository.teachers.size();
            int i=0,j=1;
            while(i<size_teacherArray-1 && j<size_teacherArray)
            {
                assertTrue(TeacherRepository.teachers.get(i).getLastName().compareToIgnoreCase(TeacherRepository.teachers.get(j).getLastName())<=0);
                i++;
                j++;
            }
        }
    }

    public void testSortTeachersOnNumberOfCourses() {

        teacherController.sortTeachersOnNumberOfCourses();
        if(TeacherRepository.teachers.size()>=2) {

            int size_teacherArray = TeacherRepository.teachers.size();
            int i=0,j=1;
            while(i<size_teacherArray-1 && j<size_teacherArray)
            {
                assertTrue(TeacherRepository.teachers.get(i).getCourses(new ArrayList<>()).size() <= TeacherRepository.teachers.get(j).getCourses(new ArrayList<>()).size());
                i++;
                j++;
            }
        }

    }
}