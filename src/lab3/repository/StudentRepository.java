package lab3.repository;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.utilities.ModelReader;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements ICrudRepository<Student>{
    private String fileName;
    private static List<Student> students;

    public StudentRepository(String fileName) {
        this.fileName = fileName;
    }

    private void fillStudentRepositoryWithStudentsFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);
        for (String line : listOfLines) {
            String[] words = line.split(", ");

            //name
            String[] strArray = words[0].split(" ");
            String firstName = strArray[0];
            String lastName = strArray[1];

            //studentId
            Long studentId = Long.parseLong(words[1]);

            //totalCredits
            int totalCredits = Integer.parseInt(words[2]);

            //TODO
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        StudentRepository.students = students;
    }

    /**
     *
     * @param id -the id of the entity to be returned id must not be null
     * @return the student with the specified id or null - if there is no student with the given id
     */
    @Override
    public Student findOne(Long id) {
        return null;
    }

    /**
     *
     * @return all students
     */
    @Override
    public List<Student> findAll() {
        return null;
    }

    /**
     *
     * @param student which has to be saved
     * @return  null- if the given student is saved otherwise returns the student (id already exists)
     */
    @Override
    public Student save(Student student) {
        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Student delete(Long id) {
        return null;
    }

    /**
     *
     * @param id id must not be null
     * @param student with which the found student should be updated.
     * @return null - if the student is updated, otherwise returns the student - (e.g id does not exist).
     */
    @Override
    public Student update(Long id, Student student) {
        return null;
    }
}
