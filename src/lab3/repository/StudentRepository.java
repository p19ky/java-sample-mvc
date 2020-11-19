package lab3.repository;

import lab3.model.Course;
import lab3.model.Student;
import lab3.utilities.DeleteSpecificFileLines;
import lab3.utilities.ModelReader;
import lab3.utilities.ModelWriter;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements ICrudRepository<Student>{
    public static String fileName;
    public static List<Student> students;

    public StudentRepository(String fileNameNew) {
        fileName = fileNameNew;
        students = new ArrayList<>();
        this.fillStudentRepositoryWithStudentsFromFile();
    }

    private void fillStudentRepositoryWithStudentsFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(fileName);
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
    public Student findOne(Long id)
    {
        return students.stream().filter(student -> student.getStudentId().equals(id)).findFirst().orElse(null);
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


        String newLine = student.customToString(new ArrayList<>(){});
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(fileName, newLine);
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

        //student found.
        if (index != -1) {
            List<Student> newStudentArray;
            for (Course course : CourseRepository.getCourses()) {
                newStudentArray = new ArrayList<>(){};
                for (Student student : course.getStudentsEnrolled()) {
                    if (!(student.getStudentId().equals(students.get(index).getStudentId())))
                        newStudentArray.add(student);
                }
                course.setStudentsEnrolled(newStudentArray);

                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(CourseRepository.fileName, String.valueOf(course.getCourseId()));
                String newLine = course.customToString();
                ModelWriter mw = new ModelWriter();
                mw.writeToFile(CourseRepository.fileName, newLine);
            }

            students.remove(index);
        }

        //if student deleted
        if (studentToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(fileName, String.valueOf(studentToReturn.getStudentId()));
//            System.out.println("STUDENT DELETED SUCCESSFULLY!");
            return studentToReturn;
        }

//        System.out.println("STUDENT WITH GIVEN ID DOES NOT EXIST!");
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

                if (!(stud.getStudentId().equals(student.getStudentId())))
                {
                    System.out.println("STUDENT ID CAN'T BE CHANGED!");
                    return student;
                }

                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(fileName, String.valueOf(stud.getStudentId()));

                stud.setFirstName(student.getFirstName());
                stud.setLastName(student.getLastName());

                String newLine = stud.customToString(new ArrayList<>(){});
                ModelWriter mw = new ModelWriter();
                mw.writeToFile(fileName, newLine);

                return null;
            }

        System.out.println("STUDENT WITH THE GIVEN ID DOES NOT EXIST!");
        return student;
    }

    /**
     * PRINT STUDENTS TO CONSOLE.
     */
    public static void printStudents() {
        for (Student student : students) {
            StringBuilder str = new StringBuilder();
            str.append(student.toString());
            str.append(" enrolledCourses: [");
            for (Course course : student.getEnrolledCourses(new ArrayList<>(){})) {
                str.append(course.getName());
                str.append(";");
            }
            str.append("]");
            str.append(" firstName: ");
            str.append(student.getFirstName());
            str.append(" lastName: ");
            str.append(student.getLastName());
            System.out.println(str.toString());
        }
    }

    /**
     * PRINT STUDENT TO CONSOLE.
     */
    public static void printStudent(Student student) {

        if (student != null) {
            StringBuilder str = new StringBuilder();
            str.append(student.toString());
            str.append(" enrolledCourses: [");
            for (Course course : student.getEnrolledCourses(new ArrayList<>(){})) {
                str.append(course.getName());
                str.append(";");
            }
            str.append("]");
            str.append(" firstName: ");
            str.append(student.getFirstName());
            str.append(" lastName: ");
            str.append(student.getLastName());
            System.out.println(str.toString());
        } else {
            System.out.println("STUDENT DOES NOT EXIST!");
        }
    }
}
