package lab3.repository;

import junit.framework.TestCase;
import lab3.model.Student;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryTest extends TestCase {

    private static StudentRepository studentRepository;

    public void setUp() throws Exception {
        studentRepository = new StudentRepository("test/lab3/repository/studentsTest.txt");
    }

    public void tearDown() throws Exception {
    }

    public void testFindOne() {

        Student student1 = studentRepository.findOne(40L);
        assertNull(student1);

        Student student2 = studentRepository.findOne(1L);
        assertNotNull(student2);
    }

    public void testFindAll() {
        List<Student> studentList = studentRepository.findAll();
        assertEquals(3, studentList.size());
       boolean IdOneFound = false;
       boolean IdTwoFound = false;
        boolean IdThreeFound = false;

        System.out.println(studentList);
        for (Student student : studentList) {
            if (student.getStudentId()==1L)
                IdOneFound = true;
            else if (student.getStudentId()==2L)
                IdTwoFound = true;
            else if(student.getStudentId()==3L)
                IdThreeFound = true;
        }

        assertTrue(IdOneFound);
        assertTrue(IdTwoFound);
        assertTrue(IdThreeFound);
    }

    public void testSave() {
        TeacherRepository teacherRepository = new TeacherRepository("test/lab3/repository/teachersTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");

        Student student = new Student(40L,6,"Tom","Hanks");
        assertNull(studentRepository.save(student));
        assertNotNull(studentRepository.save(student));

        assertEquals(4, StudentRepository.getStudents().size());
    }

    public void testDelete() {
        TeacherRepository teacherRepository = new TeacherRepository("test/lab3/repository/teachersTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");


        Student student = new Student(40L,6,"Tom","Hanks");
        assertSame(studentRepository.delete(40L).getStudentId(), student.getStudentId());
        assertNull(studentRepository.delete(40L));
    }

    public void testUpdate() {
        TeacherRepository teacherRepository = new TeacherRepository("test/lab3/repository/teachersTest.txt");
        CourseRepository courseRepository = new CourseRepository("test/lab3/repository/coursesTest.txt");

        Student student1 = new Student(2L,11,"Teodora","Olteanu");
        Student resultStudent = studentRepository.update(2L,student1);
        assertNull(resultStudent);

        Student student2 = new Student(2L,11,"Teo","Olteanu");
        Student resultStudent2 = studentRepository.update(2L,student2);
        assertNull(resultStudent2);

    }
}