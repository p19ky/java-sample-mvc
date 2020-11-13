package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryTest extends TestCase {
    private static TeacherRepository teacherRepository;

    public void setUp() throws Exception {
        teacherRepository=new TeacherRepository("test/lab3/repository/teachersTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testFindOne() {

        Teacher teacher1 = teacherRepository.findOne(1L);
        assertNotNull(teacher1);
        assertEquals(1L, (long) teacher1.getTeacherId());

        Teacher teacher2 = teacherRepository.findOne(40L);
        assertNull(teacher2);

    }

    public void testFindAll() {

        List<Teacher> teacherList = teacherRepository.findAll();
        assertEquals(2, teacherList.size());

        assertEquals(1L, (long) teacherList.get(0).getTeacherId());
        assertEquals(2L, (long) teacherList.get(1).getTeacherId());
    }

    public void testSave() {
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");

        Teacher teacher = new Teacher(25L,"Shawn","Mendes");
        assertNull(teacherRepository.save(teacher));
        assertNotNull(teacherRepository.save(teacher));
    }

    public void testDelete() {
    }

    public void testUpdate() {
    }
}