package lab3.repository;

import lab3.model.Course;
import lab3.model.Student;
import lab3.utilities.DeleteSpecificFileLines;
import lab3.utilities.ModelReader;
import lab3.utilities.ModelWriter;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements ICrudRepository<Student>{
    private String fileName;
    private static List<Student> students;

    public StudentRepository(String fileName) {
        this.fileName = fileName;
        students = new ArrayList<Student>();
        this.fillStudentRepositoryWithStudentsFromFile();
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

            Student stud = new Student(studentId, totalCredits, firstName, lastName);

            boolean alreadyExists = false;

            for (Student student : students)
                if (student.getStudentId().equals(stud.getStudentId())) {
                    alreadyExists = true;
                    break;
                }

            if (!alreadyExists)
                students.add(stud);
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
        for (Student student : students)
            if (student.getStudentId().equals(id))
                return student;

        return null;
    }

    /**
     *
     * @return all students
     */
    @Override
    public List<Student> findAll() {
        return students;
    }

    /**
     *
     * @param student which has to be saved
     * @return null- if the given student is saved otherwise returns the student (id already exists)
     */
    @Override
    public Student save(Student student) {
        for (Student stud : students)
            if (stud.getStudentId().equals(student.getStudentId()))
                return student;

        String newLine = student.customToString();
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(this.fileName, newLine);
        students.add(student);

        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Student delete(Long id) {
        Student studentToReturn = null;
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(id)) {
                studentToReturn = students.get(i);
                index = i;
            }
        }

        if (index != -1) {
            students.remove(index);
        }

        //if student deleted
        if (studentToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(this.fileName, String.valueOf(studentToReturn.getStudentId()));
            return studentToReturn;
        }

        //student not found.
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
        for (Student stud : students)
            if (stud.getStudentId().equals(id)) {
                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(this.fileName, String.valueOf(stud.getStudentId()));
                stud.setFirstName(student.getFirstName());
                stud.setLastName(student.getLastName());
                stud.setTotalCredits(student.getTotalCredits());
                String newLine = stud.customToString();
                ModelWriter mw = new ModelWriter();
                mw.writeToFile(this.fileName, newLine);
                return null;
            }

        return student;
    }

    /**
     * PRINT STUDENTS TO CONSOLE.
     */
    public void printStudents() {
        for (Student student : students) {
            StringBuilder str = new StringBuilder();
            str.append(student.toString() + " enrolledCourses: [");
            for (Course course : student.getEnrolledCourses())
                str.append(course.getName() + ";");
            str.append("]");
            System.out.println(str.toString());
        }
    }
}
