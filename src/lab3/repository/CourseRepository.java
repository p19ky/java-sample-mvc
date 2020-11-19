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
    public static String fileName; //= "courses.txt";
    public static List<Course> courses;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileNameNew) {
        fileName = fileNameNew;
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static void setCourses(List<Course> newCourses) {
        courses = newCourses;
    }

    public CourseRepository(String fileNameNew) {
        fileName = fileNameNew;
        courses = new ArrayList<Course>();
        this.fillCourseRepositoryWithCoursesFromFile();
    }

    /**
     * Fills the courses List with the courses from 'this.fileName'
     */
    private void fillCourseRepositoryWithCoursesFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(fileName);
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

                if (!studentIds[0].equals(""))
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

    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return The course with the given id or null if none was found.
     */
    @Override
    public Course findOne(Long id)
    {
        System.out.println(courses);
        return courses.stream().filter(course -> course.getCourseId().equals(id)).findFirst().orElse(null);
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

        for(Course course1: courses)
            if(course1.getCourseId().equals(course.getCourseId()))
            {
                System.out.println("COURSE ALREADY EXISTS!");
                return course1;
            }

        String newLine = course.customToString();
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(fileName, newLine);
        courses.add(course);

        //update courses for teacher that has been selected for new course.
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            if (teacher.getTeacherId().equals(course.getTeacher().getTeacherId())) {
                DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                df.deleteLines(TeacherRepository.fileName, String.valueOf(teacher.getTeacherId()));
                newLine = teacher.customToString(new ArrayList<Course>(){});
                mw.writeToFile(TeacherRepository.fileName, newLine);
            }
        }

        System.out.println("COURSE SUCCESSFULLY ADDED!");
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
                break;
            }
        }

        if (index != -1) {
            List<Course> oldCourses = new ArrayList<Course>(courses);
            oldCourses.remove(index);

            //delete the removed course from enrolledCourses of students and decrease students totalCredits.
            for (Student student : StudentRepository.students){
                for (Course enrolledCourse : student.getEnrolledCourses(new ArrayList<Course>(){})) {
                    if (enrolledCourse.getCourseId().equals(courseToReturn.getCourseId())) {
                        student.setTotalCredits(student.getTotalCredits() - enrolledCourse.getCredits());
                        DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                        df.deleteLines(StudentRepository.fileName, String.valueOf(student.getStudentId()));
                        String newLine = student.customToString(oldCourses);
                        ModelWriter mw = new ModelWriter();
                        mw.writeToFile(StudentRepository.fileName, newLine);
                        break;
                    }
                }
            }

            //delete the removed course from Courses of teachers.
            for (Teacher teacher : TeacherRepository.teachers)
                for (Course course : teacher.getCourses(new ArrayList<Course>(){}))
                    if (course.getCourseId().equals(courseToReturn.getCourseId())){
                        DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                        df.deleteLines(TeacherRepository.fileName, String.valueOf(teacher.getTeacherId()));
                        String newLine = teacher.customToString(oldCourses);
                        ModelWriter mw = new ModelWriter();
                        mw.writeToFile(TeacherRepository.fileName, newLine);
                        break;
                    }

            courses.remove(index);
        }

        //if course deleted
        if (courseToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(fileName, String.valueOf(courseToReturn.getCourseId()));
            System.out.println("COURSE DELETED SUCCESSFULLY!");
            return courseToReturn;
        }

        System.out.println("PROVIDED COURSE WAS NOT FOUND!");
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

        if (!(id.equals(course.getCourseId()))) {
            System.out.println("COURSE ID CAN'T BE CHANGED!");
            return course;
        }

        Course removedCourse = null;
        int index = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)) {
                removedCourse = courses.get(i);
                index = i;
                break;
            }
        }

        if (index != -1) {
            if (course.getMaxEnrollment() < removedCourse.getMaxEnrollment())
            {
                System.out.println("MAX ENROLLMENT NUMBER CAN'T BE CHANGED TO A SMALLER VALUE!");
                return course;
            }

            List<Course> oldCoursesWithoutRemovedOne = new ArrayList<Course>(courses);
            oldCoursesWithoutRemovedOne.remove(index);

            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(fileName, String.valueOf(courses.get(index).getCourseId()));

            Course newCourse =
                    new Course(course.getName(), course.getCourseId(), course.getTeacher(),
                            course.getMaxEnrollment(), courses.get(index).getStudentsEnrolled(), course.getCredits());

            courses.add(newCourse);
            oldCoursesWithoutRemovedOne.add(newCourse);

            String newLine = newCourse.customToString();
            ModelWriter mw = new ModelWriter();
            mw.writeToFile(fileName, newLine);

            /* --------------------------- */

            //teacher was changed.
            if (!(removedCourse.getTeacher().getTeacherId().equals(newCourse.getTeacher().getTeacherId()))) {
                for (Teacher teacher : TeacherRepository.teachers)
                    for (Course c : teacher.getCourses(oldCoursesWithoutRemovedOne))
                        if (c.getCourseId().equals(removedCourse.getCourseId())) {
                            df.deleteLines(TeacherRepository.fileName, String.valueOf(teacher.getTeacherId()));
                            newLine = teacher.customToString(oldCoursesWithoutRemovedOne);
                            mw.writeToFile(TeacherRepository.fileName, newLine);
                        }

                for (Teacher teacher : TeacherRepository.getTeachers())
                    if (teacher.getTeacherId().equals(removedCourse.getTeacher().getTeacherId())) {
                        df.deleteLines(TeacherRepository.fileName, String.valueOf(teacher.getTeacherId()));
                        newLine = teacher.customToString(oldCoursesWithoutRemovedOne);
                        mw.writeToFile(TeacherRepository.fileName, newLine);
                        break;
                    }
            }

            //credits was changed
            if (course.getCredits() != removedCourse.getCredits()){
                for (Student stud : removedCourse.getStudentsEnrolled()) {
                    stud.setTotalCredits(stud.getTotalCredits() - removedCourse.getCredits());
                    stud.setTotalCredits(stud.getTotalCredits() + newCourse.getCredits());
                    df.deleteLines(StudentRepository.fileName, String.valueOf(stud.getStudentId()));
                    newLine = stud.customToString(oldCoursesWithoutRemovedOne);
                    mw.writeToFile(StudentRepository.fileName, newLine);
                }
            }

            courses.remove(index);

            System.out.println("UPDATE SUCCESSFUL!");
            return null;
        }

        System.out.println("THE PROVIDED COURSE ID WAS NOT FOUND!");
        return course;
    }

    /**
     * PRINT COURSES TO CONSOLE.
     */
    public static void printCourses() {
        for (Course course : courses)
            System.out.println(course);
    }
}
