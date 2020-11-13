package lab3.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class CourseTest extends TestCase {
    private static Course course;
    private static Teacher teacher;

    public void setUp() throws Exception {

        teacher = new Teacher(25L,"Shawn","Mendes");
        course=new Course("Java",7L,teacher,72,new ArrayList<Student>(){},6);
    }

    public void tearDown() throws Exception {
    }

    public void testTestGetName() {

        assertEquals("Java",course.getName());
        assertNotEquals("Jav", course.getName());
    }

    public void testTestSetName() {

        course.setName("MAP");
        assertEquals("MAP",course.getName());
        assertNotEquals("map", course.getName());
    }

    public void testGetCourseId() {

        assertEquals(7L, (long) course.getCourseId());
        assertNotEquals(8L, (long) course.getCourseId());
    }

    public void testSetCourseId() {

        course.setCourseId(8L);
        assertEquals(8L, (long) course.getCourseId());
        assertNotEquals(7L, (long) course.getCourseId());
    }

    public void testGetTeacher() {

        assertEquals(course.getTeacher(), teacher);
    }

    public void testSetTeacher() {

        Teacher teacher1=new Teacher(30L,"Adam","Gontier");
        course.setTeacher(teacher1);

        assertEquals(course.getTeacher(), teacher1);
    }

    public void testGetMaxEnrollment() {

        assertEquals(72, course.getMaxEnrollment());
        assertFalse(course.getMaxEnrollment()!=72);
    }

    public void testSetMaxEnrollment() {

        course.setMaxEnrollment(80);

        assertEquals(80, course.getMaxEnrollment());
        assertFalse(course.getMaxEnrollment()!=80);
    }

    public void testGetStudentsEnrolled() {

        assertTrue(course.getStudentsEnrolled().isEmpty());
    }

    public void testSetStudentsEnrolled() {

        Student student = new Student(25L,6,"Chester","Bennington");
        List<Student> arrayStudent = new ArrayList<Student>(){};
        arrayStudent.add(student);

        course.setStudentsEnrolled(arrayStudent);

        assertTrue(course.getStudentsEnrolled().size()>0);
        assertEquals(25L, (long) course.getStudentsEnrolled().get(0).getStudentId());
    }

    public void testGetCredits() {

        assertEquals(6, course.getCredits());
        assertFalse(course.getCredits()!=6);
    }

    public void testSetCredits() {

        course.setCredits(5);

        assertEquals(5, course.getCredits());
        assertFalse(course.getCredits()!=5);
    }
}