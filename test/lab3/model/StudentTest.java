package lab3.model;

import junit.framework.TestCase;
import lab3.repository.CourseRepository;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class StudentTest extends TestCase {
    private static Student student;

    public void setUp() throws Exception {
       student = new Student(25L,6,"Chester","Bennington");
    }

    public void tearDown() throws Exception {
    }

    public void testGetStudentId() {
        assertEquals(25L, (long) student.getStudentId());
        assertNotEquals(26L, (long) student.getStudentId());
    }

    public void testSetStudentId() {
        student.setStudentId(26L);

        assertEquals(26L, (long) student.getStudentId());
        assertNotEquals(25L, (long) student.getStudentId());
    }

    public void testGetTotalCredits() {

        assertEquals(6, student.getTotalCredits());
        assertFalse(student.getTotalCredits()!=6);
    }

    public void testSetTotalCredits() {
        student.setTotalCredits(9);
        assertEquals(9, student.getTotalCredits());
        assertFalse(student.getTotalCredits()!=9);
    }

    public void testGetEnrolledCourses() {
        CourseRepository courseRepository = new CourseRepository("courses.txt");
        assertTrue(student.getEnrolledCourses(new ArrayList<>()).isEmpty());
    }
}