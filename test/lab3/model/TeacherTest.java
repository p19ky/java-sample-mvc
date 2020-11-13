
package lab3.model;

import junit.framework.TestCase;
import lab3.repository.CourseRepository;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class TeacherTest extends TestCase {
    private static Teacher teacher;

    public void setUp() throws Exception {
        teacher = new Teacher(25L,"Shawn","Mendes");
    }

    public void tearDown() throws Exception {
    }

    public void testGetTeacherId() {
        assertEquals(25L, (long) teacher.getTeacherId());
        assertFalse(teacher.getTeacherId()!=25L);

    }

    public void testSetTeacherId() {
        teacher.setTeacherId(26L);
        assertEquals(26L, (long) teacher.getTeacherId());
        assertFalse(teacher.getTeacherId()!=26L);
    }

    public void testGetCourses() {
        CourseRepository courseRepository = new CourseRepository("courses.txt");
        assertTrue(teacher.getCourses(new ArrayList<Course>(){}).isEmpty());
    }
    public void testGetFirstName() {
        assertEquals("Shawn", teacher.getFirstName());
        assertNotEquals("Shan", teacher.getFirstName());
    }
    public void testSetFirstName() {
        teacher.setFirstName("Shelbey");
        assertEquals("Shelbey", teacher.getFirstName());
        assertNotEquals("Shellb", teacher.getFirstName());
    }

    public void testGetLastName() {
        assertEquals("Mendes", teacher.getLastName());
        assertNotEquals("Mend", teacher.getLastName());
    }
    public void testSetLastName() {
        teacher.setLastName("Mandy");
        assertEquals("Mandy", teacher.getLastName());
        assertNotEquals("Mend", teacher.getLastName());
    }
}