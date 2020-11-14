package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryTest extends TestCase {
    private static TeacherRepository teacherRepository;
    private static StudentRepository studentRepository;
    private static CourseRepository courseRepository;

    public void setUp() throws Exception {
        teacherRepository = new TeacherRepository("test/lab3/repository/teachersTest.txt");
        studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testFindOne() {
        Course course1 = courseRepository.findOne(12L);
        assertNotNull(course1);

        Course course2 = courseRepository.findOne(40L);
        assertNull(course2);
    }

    public void testFindAll() {
        List<Course> courseList = courseRepository.findAll();
        assertEquals(2, courseList.size());

        boolean IdOneFound = false;
        boolean IdTwoFound = false;

        for (Course course : courseList) {
            if (course.getCourseId().equals(12L))
                IdOneFound = true;
            else if (course.getCourseId().equals(11L))
                IdTwoFound = true;
        }

        assertTrue(IdOneFound);
        assertTrue(IdTwoFound);
    }

    public void testSave() {

        Teacher teacher = new Teacher(2L,"Cristian","Sacarea");
        Course course = new Course("WS",13L,teacher,20,new ArrayList<Student>(),4);
        assertNull(courseRepository.save(course));
        assertNotNull(courseRepository.save(course));

        assertEquals(3, CourseRepository.getCourses().size());
    }

    public void testDelete() {
        Course course1, course2;
        course1=courseRepository.delete(13L);
        assertNotNull(course1);

        course2=courseRepository.delete(13L);
        assertNull(course2);

        assertEquals(2, CourseRepository.getCourses().size());
    }

    public void testUpdate() {
    }
}