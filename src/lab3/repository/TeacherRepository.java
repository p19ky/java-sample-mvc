package lab3.repository;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.utilities.DeleteSpecificFileLines;
import lab3.utilities.ModelReader;
import lab3.utilities.ModelWriter;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements ICrudRepository<Teacher>{
    private String fileName;
    private static List<Teacher> teachers;

    public TeacherRepository(String fileName) {
        this.fileName = fileName;
        teachers = new ArrayList<Teacher>();
        this.fillTeacherRepositoryWithTeachersFromFile();
    }

    private void fillTeacherRepositoryWithTeachersFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);
        for (String line : listOfLines) {
            String[] words = line.split(", ");

            //name
            String[] name = words[0].split(" ");
            String firstName = name[0];
            String lastName = name[1];

            //teacherId
            Long teacherId = Long.parseLong(words[1]);

            Teacher teacher = new Teacher(teacherId, firstName, lastName);

            boolean alreadyExists = false;

            for (Teacher t : teachers)
                if (t.getTeacherId().equals(teacher.getTeacherId())) {
                    alreadyExists = true;
                    break;
                }


            if (!alreadyExists){
                teachers.add(teacher);
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(List<Teacher> teachers) {
        TeacherRepository.teachers = teachers;
    }

    /**
     *
     * @param id -the id of the entity to be returned id must not be null
     * @return The teacher with the given id or null if none was found.
     */
    @Override
    public Teacher findOne(Long id) {
        for (Teacher teacher : teachers)
            if (teacher.getTeacherId().equals(id))
                return teacher;

        return null;
    }

    /**
     *
     * @return all teachers.
     */
    @Override
    public List<Teacher> findAll() {
        return teachers;
    }

    /**
     *
     * @param teacher
     * @return null- if the given teacher is saved otherwise returns the teacher (id already exists)
     */
    @Override
    public Teacher save(Teacher teacher) {
        for (Teacher t: teachers)
            if (t.getTeacherId().equals(teacher.getTeacherId()))
                return teacher;

        String newLine = teacher.customToString();
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(this.fileName, newLine);
        teachers.add(teacher);

        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Teacher delete(Long id) {
        Teacher teacherToReturn = null;
        int index = -1;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getTeacherId().equals(id)) {
                teacherToReturn = teachers.get(i);
                index = i;
            }
        }

        if (index != -1) {
            teachers.remove(index);
        }

        //if teacher deleted
        if (teacherToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(this.fileName, String.valueOf(teacherToReturn.getTeacherId()));
            return teacherToReturn;
        }

        //teacher not found.
        return null;
    }

    /**
     *
     * @param id id must not be null
     * @param teacher with which the found teacher should be updated.
     * @return null - if the teacher is updated, otherwise returns the teacher - (e.g id does not exist).
     */
    @Override
    public Teacher update(Long id, Teacher teacher) {
        for (Teacher t : teachers)
            if (t.getTeacherId().equals(id)) {
                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(this.fileName, String.valueOf(t.getTeacherId()));
                t.setFirstName(teacher.getFirstName());
                t.setLastName(teacher.getLastName());
                String newLine = t.customToString();
                ModelWriter mw = new ModelWriter();
                mw.writeToFile(this.fileName, newLine);
                return null;
            }

        return teacher;
    }

    /**
     * PRINT TEACHERS TO CONSOLE.
     */
    public void printTeachers() {
        for (Teacher teacher : teachers) {
            StringBuilder str = new StringBuilder();
            str.append(teacher.toString() + " courses: [");
            for (Course course : teacher.getCourses())
                str.append(course.getName() + ";");
            str.append("]");
            System.out.println(str.toString());
        }
    }
}
