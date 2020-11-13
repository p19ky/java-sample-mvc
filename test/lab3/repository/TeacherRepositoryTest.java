package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Course;
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

        boolean IdOneFound = false;
        boolean IdTwoFound = false;

        for (Teacher teacher : teacherList) {
            if (teacher.getTeacherId().equals(1L))
                IdOneFound = true;
            else if (teacher.getTeacherId().equals(2L))
                IdTwoFound = true;
        }

        assertTrue(IdOneFound);
        assertTrue(IdTwoFound);
    }

    public void testSave() {
        StudentRepository studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");

        Teacher teacher = new Teacher(25L,"Shawn","Mendes");
        assertNull(teacherRepository.save(teacher));
        assertNotNull(teacherRepository.save(teacher));

        assertEquals(3, TeacherRepository.getTeachers().size());
    }

    public void testDelete() {
        StudentRepository studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
        Teacher teacher = new Teacher(25L,"Shawn","Mendes");

        assertEquals(teacherRepository.delete(25L).getTeacherId(), teacher.getTeacherId());
        assertNull(teacherRepository.delete(25L));
    }

    public void testUpdate() {
        StudentRepository studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");
        Teacher teacher = new Teacher(1L,"Tudor","Chifor");

        System.out.println(CourseRepository.getCourses());

        Teacher resultTeacher =  teacherRepository.update(1L,teacher);
        assertNull(resultTeacher);

        teacher.setFirstName("Catalin");
        teacher.setLastName("Rusu");

        Teacher resultTeacher1 =  teacherRepository.update(1L,teacher);
        assertNull(resultTeacher1);
    }
}