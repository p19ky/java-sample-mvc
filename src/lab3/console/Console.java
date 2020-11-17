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
        System.out.println("  7 - Student Menu.");
        System.out.println("  8 - Teacher Menu.");
        System.out.println("  9 - Course Menu.");
        System.out.println("  10 - Student Sort Menu.");
        System.out.println("  11 - Teacher Sort Menu.");
        System.out.println("  12 - Course Sort Menu.");
        System.out.println("  13 - Filter Students on total Credits.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printStudentMenu() {
        System.out.println();
        System.out.println();
        System.out.println("            *** UBB STUDENT MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Find a given Student based on id.");
        System.out.println("  2 - Find all Students.");
        System.out.println("  3 - Add new Student.");
        System.out.println("  4 - Delete a Student.");
        System.out.println("  5 - Update an existing Student.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printTeacherMenu() {
        System.out.println();
        System.out.println();
        System.out.println("            *** UBB TEACHER MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Find a given Teacher based on id.");
        System.out.println("  2 - Find all Teachers.");
        System.out.println("  3 - Add new Teacher.");
        System.out.println("  4 - Delete a Teacher.");
        System.out.println("  5 - Update an existing Teacher.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printCourseMenu() {
        System.out.println();
        System.out.println();
        System.out.println("            *** UBB COURSE MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Find a given Course based on id.");
        System.out.println("  2 - Find all Courses.");
        System.out.println("  3 - Add new Course.");
        System.out.println("  4 - Delete a Course.");
        System.out.println("  5 - Update an existing Course.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printStudentSortMenu() {
        System.out.println();
        System.out.println();
        System.out.println("           *** UBB STUDENT SORT MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Sort on studentId.");
        System.out.println("  2 - Sort on firstName.");
        System.out.println("  3 - Sort on lastName.");
        System.out.println("  4 - Sort on totalCredits.");
        System.out.println("  5 - Sort on number of enrolledCourses.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printTeacherSortMenu() {
        System.out.println();
        System.out.println();
        System.out.println("           *** UBB TEACHER SORT MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Sort on teacherId.");
        System.out.println("  2 - Sort on firstName.");
        System.out.println("  3 - Sort on lastName.");
        System.out.println("  4 - Sort on number of courses.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printCourseSortMenu() {
        System.out.println();
        System.out.println();
        System.out.println("           *** UBB COURSE SORT MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Sort on courseId.");
        System.out.println("  2 - Sort on name.");
        System.out.println("  3 - Sort on maxEnrollment.");
        System.out.println("  4 - Sort on credits.");
        System.out.println("  5 - Sort on number of studentsEnrolled.");
        System.out.println();
        System.out.println("  0 - Exit.");
        System.out.println();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void printStudentFilterMenu() {
        System.out.println();
        System.out.println();
        System.out.println("           *** UBB STUDENT FILTER MENU ***");
        System.out.println();
        System.out.println();
        System.out.println("  1 - Filter on (less than given total credits).");
        System.out.println("  2 - Filter on (more than given total credits).");
        System.out.println("  3 - Filter on (equal to given total credits).");
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

                    registerStudent: {
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

                        System.out.println();
                        System.out.println("YOU CAN GO BACK TO THE MAIN MENU BY ENTERING -1");
                        System.out.println();
                        System.out.println();

                        System.out.println("Enter Course id:\n");

                        //get course id
                        String inCourseId = reader.readLine();
                        if (inCourseId.trim().equals("-1"))
                            break registerStudent;

                        System.out.println("Enter Student id:\n");

                        //get student id
                        String inStudentId = reader.readLine();
                        if (inStudentId.trim().equals("-1"))
                            break registerStudent;

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
                    }

                    /**
                     * Print Courses with free places.
                     */
                } else if (input.trim().equals("2")) {

                    List<Course> courses = registrationSystem.retrieveCoursesWithFreePlaces();

                    /**
                     * Get Students enrolled in a given course.
                     */
                } else if (input.trim().equals("3")) {

                    studentsEnrolledGivenCourse: {
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

                        System.out.println("YOU CAN GO BACK BY ENTERING -1");

                        System.out.println("Enter Course id:\n");

                        String inCourseId = reader.readLine();

                        if (inCourseId.trim().equals("-1"))
                            break studentsEnrolledGivenCourse;

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
                            StudentRepository.printStudent(stud);
                    }
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
                /**
                 * Print Student Menu
                 */
                else if (input.trim().equals("7")) {
                    printStudentMenu();

                    String studentMenuInput = reader.readLine();

                    while (!studentMenuInput.equals("0")) {
                        /**
                         * find one student
                         */
                        if (studentMenuInput.trim().equals("1")) {
                            studentController.findOneStudent();
                        }
                        /**
                         * find all students
                         */
                        else if (studentMenuInput.trim().equals("2")) {
                            studentController.findAllStudents();
                        }
                        /**
                         * save a new student to database
                         */
                        else if (studentMenuInput.trim().equals("3")) {
                            studentController.saveStudent();
                        }
                        /**
                         * delete an existing student
                         */
                        else if (studentMenuInput.trim().equals("4")) {
                            studentController.deleteStudent();
                        }
                        /**
                         * update an existing student
                         */
                        else if (studentMenuInput.trim().equals("5")) {
                            studentController.updateStudent();
                        }

                        printStudentMenu();
                        studentMenuInput = reader.readLine();
                    }
                }
                /**
                 * Print Teacher Menu
                 */
                else if (input.trim().equals("8")) {
                    printTeacherMenu();

                    String teacherMenuInput = reader.readLine();

                    while (!teacherMenuInput.equals("0")) {
                        /**
                         * find one teacher
                         */
                        if (teacherMenuInput.trim().equals("1")) {
                            teacherController.findOneTeacher();
                        }
                        /**
                         * find all teachers
                         */
                        else if (teacherMenuInput.trim().equals("2")) {
                            teacherController.findAllTeachers();
                        }
                        /**
                         * save a new teacher to database
                         */
                        else if (teacherMenuInput.trim().equals("3")) {
                            teacherController.saveTeacher();
                        }
                        /**
                         * delete an existing teacher
                         */
                        else if (teacherMenuInput.trim().equals("4")) {
                            teacherController.deleteTeacher();
                        }
                        /**
                         * update an existing teacher
                         */
                        else if (teacherMenuInput.trim().equals("5")) {
                            teacherController.updateTeacher();
                        }

                        printTeacherMenu();
                        teacherMenuInput = reader.readLine();
                    }
                }
                /**
                 * Print Course Menu
                 */
                else if (input.trim().equals("9")) {
                    printCourseMenu();

                    String courseMenuInput = reader.readLine();

                    while (!courseMenuInput.equals("0")) {
                        /**
                         * find one course
                         */
                        if (courseMenuInput.trim().equals("1")) {
//                            studentController.findOneStudent();
                        }
                        /**
                         * find all courses
                         */
                        else if (courseMenuInput.trim().equals("2")) {
//                            studentController.findAllStudents();
                        }
                        /**
                         * save a new course to database
                         */
                        else if (courseMenuInput.trim().equals("3")) {
//                            studentController.saveStudent();
                        }
                        /**
                         * delete an existing course
                         */
                        else if (courseMenuInput.trim().equals("4")) {
//                            studentController.deleteStudent();
                        }
                        /**
                         * update an existing course
                         */
                        else if (courseMenuInput.trim().equals("5")) {
//                            studentController.updateStudent();
                        }

                        printCourseMenu();
                        courseMenuInput = reader.readLine();
                    }
                }
                /**
                 * sort students on given criteria
                 */
                else if (input.trim().equals("10")) {
                    printStudentSortMenu();

                    String studentSortMenuInput = reader.readLine();

                    while (!studentSortMenuInput.equals("0")) {

                        if (studentSortMenuInput.trim().equals("1")){
                            studentController.sortStudentsOnId();
                        } else if (studentSortMenuInput.trim().equals("2")) {
                            studentController.sortStudentsOnFirstName();
                        } else if (studentSortMenuInput.trim().equals("3")) {
                            studentController.sortStudentsOnLastName();
                        } else if (studentSortMenuInput.trim().equals("4")) {
                            studentController.sortStudentsOnTotalCredits();
                        } else if (studentSortMenuInput.trim().equals("5")) {
                            studentController.sortStudentsOnNumberOfEnrolledCourses();
                        }

                        printStudentSortMenu();
                        studentSortMenuInput = reader.readLine();
                    }
                }
                /**
                 * sort teachers on given criteria
                 */
                else if (input.trim().equals("11")) {
                    printTeacherSortMenu();

                    String teacherSortMenuInput = reader.readLine();

                    while (!teacherSortMenuInput.equals("0")) {

                        if (teacherSortMenuInput.trim().equals("1")){
                            teacherController.sortTeachersOnId();
                        } else if (teacherSortMenuInput.trim().equals("2")) {
                            teacherController.sortTeachersOnFirstName();
                        } else if (teacherSortMenuInput.trim().equals("3")) {
                            teacherController.sortTeachersOnLastName();
                        } else if (teacherSortMenuInput.trim().equals("4")) {
                            teacherController.sortTeachersOnNumberOfCourses();
                        }

                        printTeacherSortMenu();
                        teacherSortMenuInput = reader.readLine();
                    }
                }
                /**
                 * sort courses on given criteria
                 */
                else if (input.trim().equals("12")) {
                    printCourseSortMenu();

                    String courseSortMenuInput = reader.readLine();

                    while (!courseSortMenuInput.equals("0")) {

                        if (courseSortMenuInput.trim().equals("1")){
                            courseController.sortCoursesOnCourseId();
                        } else if (courseSortMenuInput.trim().equals("2")) {
                            courseController.sortCoursesOnName();
                        } else if (courseSortMenuInput.trim().equals("3")) {
                            courseController.sortCoursesOnMaxEnrollment();
                        } else if (courseSortMenuInput.trim().equals("4")) {
                            courseController.sortCoursesOnCredits();
                        } else if (courseSortMenuInput.trim().equals("5")) {
                            courseController.sortTeachersOnNumberOfCourses();
                        }

                        printCourseSortMenu();
                        courseSortMenuInput = reader.readLine();
                    }
                }
                /**
                 * filter students on total credits
                 */
                else if (input.trim().equals("13")) {
                    printStudentFilterMenu();

                    String studentFilterMenuInput = reader.readLine();

                    while (!studentFilterMenuInput.equals("0")) {

                        if (studentFilterMenuInput.trim().equals("1")){

                            System.out.println("YOU CAN EXIT AT ANY TIME BY HITTING SIMPLY ENTER");

                            filterLessThan: {
                                System.out.println("\nVALUE TO FILTER AFTER:\n");

                                String inputValue = reader.readLine();

                                if (inputValue.trim().equals(""))
                                    break filterLessThan;

                                studentController.filterStudentsOnTotalCredits(Integer.parseInt(inputValue.trim()), "lt");
                            }
                        } else if (studentFilterMenuInput.trim().equals("2")) {

                            System.out.println("YOU CAN EXIT AT ANY TIME BY HITTING SIMPLY ENTER");

                            filterGreaterThan: {
                                System.out.println("\nVALUE TO FILTER AFTER:\n");

                                String inputValue = reader.readLine();

                                if (inputValue.trim().equals(""))
                                    break filterGreaterThan;

                                studentController.filterStudentsOnTotalCredits(Integer.parseInt(inputValue.trim()), "gt");
                            }

                        } else if (studentFilterMenuInput.trim().equals("3")) {

                            System.out.println("YOU CAN EXIT AT ANY TIME BY HITTING SIMPLY ENTER");

                            filterEqualTo: {
                                System.out.println("\nVALUE TO FILTER AFTER:\n");

                                String inputValue = reader.readLine();

                                if (inputValue.trim().equals(""))
                                    break filterEqualTo;

                                studentController.filterStudentsOnTotalCredits(Integer.parseInt(inputValue.trim()), "eq");
                            }
                        }

                        printStudentFilterMenu();
                        studentFilterMenuInput = reader.readLine();
                    }
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
