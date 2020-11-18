package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.utilities.DeleteSpecificFileLines;
import lab3.utilities.ModelWriter;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {

    public void callRegister() {

    }

    /**
     *
     * @param course whom the student should be enrolled to.
     * @param student which has to be enrolled to the given course.
     * @return true if successful - false else.
     */
    public boolean register(Course course, Student student){
        boolean studentExists = false;
        boolean courseExists = false;

        for (Course c : CourseRepository.getCourses())
            if (c.getCourseId().equals(course.getCourseId())) {
                courseExists = true;
                break;
            }

        if (!courseExists) {
            System.out.println("COURSE IS NOT AVAILABLE!");
            return false;
        }

        for (Student s : StudentRepository.getStudents())
            if (s.getStudentId().equals(student.getStudentId())) {
                studentExists = true;
                break;
            }

        if (!studentExists) {
            System.out.println("STUDENT DOES NOT EXIST!");
            return false;
        }


        if (student.getTotalCredits() + course.getCredits() > 30) {
            System.out.println("STUDENT CAN'T BE ADDED TO COURSE. TOTAL CREDIT NUMBER EXCEEDED!");
            return false;
        }

        for (Student stud : course.getStudentsEnrolled())
            if (stud.getStudentId().equals(student.getStudentId())) {
                System.out.println("THE GIVEN STUDENT IS ALREADY ENROLLED IN THIS COURSE!");
                return false;
            }

        for (Course course1 : student.getEnrolledCourses(new ArrayList<Course>(){}))
            if (course1.getCourseId().equals(course.getCourseId())) {
                System.out.println("THE GIVEN STUDENT IS ALREADY ENROLLED IN THIS COURSE!");
                return false;
            }

        if (course.getStudentsEnrolled().size() < course.getMaxEnrollment()) {
            for (Course c : CourseRepository.courses) {
                if (c.getCourseId().equals(course.getCourseId())) {
                    //course updates.
                    List<Student> newStudArray = new ArrayList<Student>(){};
                    newStudArray = c.getStudentsEnrolled();
                    newStudArray.add(student);
                    c.setStudentsEnrolled(newStudArray);

                    DeleteSpecificFileLines df = new DeleteSpecificFileLines();
                    df.deleteLines(CourseRepository.fileName, String.valueOf(course.getCourseId()));

                    ModelWriter mw = new ModelWriter();
                    String newLine = c.customToString();
//                    System.out.println(newLine);
                    mw.writeToFile(CourseRepository.fileName, newLine);

                    //student updates
                    for(Student s : StudentRepository.students) {
                        if (s.getStudentId().equals(student.getStudentId())) {
                            s.setTotalCredits(s.getTotalCredits() + course.getCredits());

                            df.deleteLines(StudentRepository.fileName, String.valueOf(student.getStudentId()));
                            newLine = student.customToString(new ArrayList<Course>(){});
//                            System.out.println(newLine);
                            mw.writeToFile(StudentRepository.fileName, newLine);
                        }
                    }

                    System.out.println("STUDENT SUCCESSFULLY ENROLLED TO COURSE!");
                    return true;
                }
            }
        }


        System.out.println("COURSE HAS NO FREE PLACES!");
        return false;

    };

    /**
     *
     * @return Courses which still have free place(s) for student(s)
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> freeCourses = new ArrayList<Course>(){};

        for (Course course : CourseRepository.getCourses())
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment())
                freeCourses.add(course);

        if (freeCourses.isEmpty()) {
            System.out.println("NO COURSES WITH FREE PLACES!");
            return freeCourses;
        }

        System.out.println();
        System.out.println("Courses with free available places:\n");
        System.out.println();
        for (Course course : freeCourses) {
            System.out.println("free places: " + (course.getMaxEnrollment() - course.getStudentsEnrolled().size())  + " " + course.toString());

        }

        return freeCourses;
    };

    /**
     *
     * @param course whom enrolled students should be returned.
     * @return enrolled students for give course.
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course) {

        for (Course c : CourseRepository.courses)
        {
            if (c.getCourseId().equals(course.getCourseId())) {
                return c.getStudentsEnrolled();
            }
        }


        System.out.println("THERE IS NO SUCH COURSE!");
        return new ArrayList<Student>(){};

    }

    /**
     *
     * @return all the courses.
     */
    public List<Course> getAllCourses(){
        return CourseRepository.getCourses();
    }
}
