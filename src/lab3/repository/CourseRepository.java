package lab3.repository;

import lab3.model.Student;
import lab3.model.Teacher;
import lab3.utilities.ModelReader;
import lab3.utilities.DeleteSpecificFileLines;
import lab3.utilities.ModelWriter;
import lab3.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICrudRepository<Course> {
    private String fileName; //= "courses.txt";
    private static List<Course> courses;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static void setCourses(List<Course> newCourses) {
        courses = newCourses;
    }

    public CourseRepository(String fileName) {
        this.fileName = fileName;
        courses = new ArrayList<Course>();
        this.fillCourseRepositoryWithCoursesFromFile();
    }

    /**
     * Fills the courses List with the courses from 'this.fileName'
     */
    private void fillCourseRepositoryWithCoursesFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);
        if (StudentRepository.getStudents() != null && TeacherRepository.getTeachers() != null)
            for (String line : listOfLines) {
                String[] words = line.split(", ");

                //name
                String name = words[0];

                //courseId
                Long courseId = Long.parseLong(words[1]);

                //teacher
                Teacher teacher = null;
                for (Teacher t : TeacherRepository.getTeachers())
                    if (t.getTeacherId() == Long.parseLong(words[2])) teacher = t;

                //maxEnrollment
                int maxEnrollment = Integer.parseInt(words[3]);

                //studentsEnrolled
                List<Student> studentsEnrolled = new ArrayList<Student>();
                String regx = "[]";
                char[] ca = regx.toCharArray();
                for (char c:ca) words[4] = words[4].replace("" + c, "");
                String[] studentIds = words[4].split(";");
                for (String studentId:studentIds)
                    for (Student student : StudentRepository.getStudents())
                        if (Long.parseLong(studentId) == student.getStudentId()) studentsEnrolled.add(student);

                //credits
                int credits = Integer.parseInt(words[5]);

                Course newCourse = new Course(name, courseId, teacher, maxEnrollment, studentsEnrolled, credits);

                boolean alreadyExists = false;
                for (Course c:courses)
                    if (c.getCourseId().equals(newCourse.getCourseId())) {
                        alreadyExists = true;
                        break;
                    }

                if (!alreadyExists) courses.add(newCourse);
            }

        return;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return The course with the given id or null if none was found.
     */
    @Override
    public Course findOne(Long id) {
        //Course with this the given id was found.
        return courses.stream().filter(course -> course.getCourseId().equals(id)).findFirst().orElse(null);

        //Course with given id does not exist.
        //return null
    }

    /**
     * @return A list of all the courses.
     */
    @Override
    public List<Course> findAll() {
        return courses;
    }

    /**
     *
     * @param course given course to be saved.
     * @return course if it already exists or null if given course was saved successfully to database.
     */
    @Override
    public Course save(Course course) {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);

        for (String line : listOfLines) {
            String[] words = line.split(", ");
            //Course given already exists.
            for (Course c : courses) if (c.getCourseId() == Long.parseLong(words[1])) return c;
        }

        String newLine = course.customToString();
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(this.fileName, newLine);
        courses.add(course);

        //Course given successfully saved to database.
        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return Course that was deleted or null if there is no Course with given id.
     */
    @Override
    public Course delete(Long id) {
        Course courseToReturn = null;
        int index = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)) {
                courseToReturn = courses.get(i);
                index = i;
            }
        }

        if (index != -1) {
            courses.remove(index);
        }

        //if course deleted
        if (courseToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(this.fileName, String.valueOf(courseToReturn.getCourseId()));
            return courseToReturn;
        }

        //course not found.
        return null;
    }

    /**
     *
     * @param id id must not be null
     * @param course with which the found course should be updated.
     * @return null if update successful. Otherwise, the given course. (ex. if given id was not found)
     */
    @Override
    public Course update(Long id, Course course) {

        for (Course c : courses)
            if (c.getCourseId().equals(id)) {
                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(this.fileName, String.valueOf(c.getCourseId()));
                c.setName(course.getName());
                c.setTeacher(course.getTeacher());
                c.setMaxEnrollment(course.getMaxEnrollment());
                c.setStudentsEnrolled(course.getStudentsEnrolled());
                c.setCredits(course.getCredits());
                String newLine = c.customToString();
                ModelWriter mw = new ModelWriter();
                mw.writeToFile(this.fileName, newLine);
                return null;
            }

        return course;
    }

    /**
     * PRINT COURSES TO CONSOLE.
     */
    public void printCourses() {
        for (Course course : courses)
            System.out.println(course);
    }
}
