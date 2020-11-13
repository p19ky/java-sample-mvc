package lab3.console;

import lab3.controller.CourseController;
import lab3.controller.RegistrationSystem;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Console {
    StudentController studentController = new StudentController();
    TeacherController teacherController = new TeacherController();
    CourseController courseController = new CourseController();
    RegistrationSystem registrationSystem = new RegistrationSystem();

    public void printConsole() {
        System.out.println();
        System.out.println();
        System.out.println("            *** UBB CONTROL PANEL ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Register Student for Course.");
        System.out.println("  2 - Show Courses with available spaces.");
        System.out.println("  3 - Show Students enrolled in a given Course.");
        System.out.println("  4 - All available Courses at UBB.");
        System.out.println("  5 - All available Students at UBB.");
        System.out.println("  6 - All available Teachers at UBB.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public Console() throws IOException {
        try {
            this.printConsole();

            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Reading data using readLine
            String input = reader.readLine();

            while (!input.equals("0")) {
                // Printing the read line


                /**
                 * register Student for Course.
                 */
                if (input.trim().equals("1")) {

                    List<Course> listOfCourses = registrationSystem.getAllCourses();

                    if (listOfCourses.isEmpty()) {
                        System.out.println("NO COURSES AVAILABLE AT THE MOMENT!");
                        break;
                    }

                    System.out.println();
                    System.out.println("\nCourses:\n");
                    System.out.println();
                    for (Course c : listOfCourses)
                        System.out.println(c.toString());
                    System.out.println();
                    System.out.println("\nStudents:\n");
                    System.out.println();
                    for (Student s : StudentRepository.students)
                        System.out.println(s.toString());
                    System.out.println();

                    System.out.println("Enter Course id:\n");

                    //get course id
                    String inCourseId = reader.readLine();

                    System.out.println("Enter Student id:\n");

                    //get student id
                    String inStudentId = reader.readLine();

                    Course courseForCall = null;
                    Student studentForCall = null;

                    for (Course c : listOfCourses) {
                        if (c.getCourseId().equals(Long.parseLong(inCourseId.trim()))) {
                            courseForCall = c;
                            break;
                        }
                    }

                    if (courseForCall == null) {
                        System.out.println("COURSE ID IS NOT AVAILABLE");
                        continue;
                    }

                    for (Student s : StudentRepository.students) {
                        if (s.getStudentId().equals(Long.parseLong(inStudentId.trim()))) {
                            studentForCall = s;
                            break;
                        }
                    }

                    if (studentForCall == null) {
                        System.out.println("STUDENT ID IS NOT AVAILABLE");
                        continue;
                    }

                    registrationSystem.register(courseForCall, studentForCall);

                    /**
                     * Print Courses with free places.
                     */
                } else if (input.trim().equals("2")) {

                    List<Course> courses = registrationSystem.retrieveCoursesWithFreePlaces();

                    /**
                     * Get Students enrolled in a given course.
                     */
                } else if (input.trim().equals("3")) {

                    List<Course> listOfCourses = registrationSystem.getAllCourses();

                    if (listOfCourses.isEmpty()) {
                        System.out.println("NO COURSES AVAILABLE AT THE MOMENT!");
                        break;
                    }

                    System.out.println();
                    System.out.println("\nCourses:\n");
                    System.out.println();
                    for (Course c : listOfCourses)
                        System.out.println(c.toString());
                    System.out.println();

                    System.out.println("Enter Course id:\n");

                    String inCourseId = reader.readLine();

                    Course courseForCall = null;

                    for (Course c : CourseRepository.getCourses()) {
                        if (c.getCourseId().equals(Long.parseLong(inCourseId.trim()))) {
                            courseForCall = c;
                            break;
                        }
                    }

                    if (courseForCall == null) {
                        System.out.println("COURSE ID IS NOT AVAILABLE");
                        continue;
                    }

                    System.out.println("\nSTUDENTS ENROLLED IN GIVEN COURSE:\n\n");
                    for (Student stud : registrationSystem.retrieveStudentsEnrolledForACourse(courseForCall))
                        System.out.println(stud.toString());

                }
                /**
                 * Print all courses.
                 */
                else if (input.trim().equals("4")) {
                    this.courseController.print();
                }
                /**
                 * Print all students.
                 */
                else if (input.trim().equals("5")) {
                    this.studentController.print();
                }
                /**
                 * Print all teachers.
                 */
                else if (input.trim().equals("6")) {
                    this.teacherController.print();
                }

                /** REPRINT CONSOLE IF 0 WAS NOT THE INPUT */
                printConsole();
                input = reader.readLine();
            }

        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}
