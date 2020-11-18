package lab3.console;

import lab3.controller.CourseController;
import lab3.controller.RegistrationSystem;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;
import lab3.exceptions.InvalidCourseException;
import lab3.exceptions.InvalidStudentException;
import lab3.exceptions.InvalidTeacherException;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static lab3.controller.StudentController.isNumeric;

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

                /*
                  register Student for Course.
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

                    /*
                      Print Courses with free places.
                     */
                } else if (input.trim().equals("2")) {

                    List<Course> courses = registrationSystem.retrieveCoursesWithFreePlaces();

                    if (!courses.isEmpty())
                        for (Course course : courses)
                            System.out.println(course);
                    else
                        System.out.println("NO COURSES WITH FREE PLACES!");

                    /*
                      Get Students enrolled in a given course.
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
                /*
                  Print all courses.
                 */
                else if (input.trim().equals("4")) {
                    this.courseController.print();
                }
                /*
                  Print all students.
                 */
                else if (input.trim().equals("5")) {
                    this.studentController.print();
                }
                /*
                  Print all teachers.
                 */
                else if (input.trim().equals("6")) {
                    this.teacherController.print();
                }
                /*
                  Print Student Menu
                 */
                else if (input.trim().equals("7")) {
                    printStudentMenu();

                    String studentMenuInput = reader.readLine();

                    while (!studentMenuInput.equals("0")) {
                        /*
                          find one student
                         */
                        if (studentMenuInput.trim().equals("1")) {

                            findOneStudent: {
                                try {
                                    System.out.println("\nYOU CAN GO BACK BY ENTERING -1\n\n");

                                    System.out.println("ENTER ID TO SEARCH FOR:\n");

                                    // Reading data using readLine
                                    String inputFind = reader.readLine();

                                    if (inputFind.trim().equals("-1"))
                                        break findOneStudent;

                                    if (!isNumeric(inputFind.trim()))
                                        throw new InvalidStudentException("Invalid Student id. Id must be numeric!");

                                    if (inputFind.trim().length() < 1)
                                        throw new InvalidStudentException("Student id is required!");

                                    Student stud = studentController.findOneStudent(Long.parseLong(inputFind.trim()));

                                    if (stud != null)
                                        StudentRepository.printStudent(stud);
                                    else
                                        System.out.println("STUDENT DOES NOT EXIST");

                                } catch (IOException | InvalidStudentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          find all students
                         */
                        else if (studentMenuInput.trim().equals("2")) {
                            var students = studentController.findAllStudents();

                            if (!students.isEmpty()) {
                                StudentRepository.printStudents();
                            } else {
                                System.out.println("NO STUDENTS AVAILABLE!");
                            }
                        }
                        /*
                          save a new student to database
                         */
                        else if (studentMenuInput.trim().equals("3")) {

                            saveStudent : {
                                try {
                                    System.out.println("\nENTER NEW STUDENT DETAILS:\n\n");

                                    System.out.println("YOU CAN EXIT ANYTIME BY ENTERING -1\n");

                                    System.out.println("\nFIRST NAME:\n");
                                    String firstNameInput = reader.readLine();

                                    if (firstNameInput.trim().equals("-1"))
                                        break saveStudent;

                                    if (firstNameInput.trim().equals(""))
                                        throw new InvalidStudentException("Firstname is required!");

                                    System.out.println("\nLAST NAME:\n\n");
                                    String lastNameInput = reader.readLine();

                                    if (lastNameInput.trim().equals("-1"))
                                        break saveStudent;

                                    if (lastNameInput.trim().equals(""))
                                        throw new InvalidStudentException("Lastname is required!");

                                    System.out.println("\nSTUDENT ID:\n\n");
                                    String studentId = reader.readLine();

                                    if (studentId.trim().equals("-1"))
                                        break saveStudent;

                                    if (studentId.trim().equals(""))
                                        throw new InvalidStudentException("Student id is required!");

                                    if (!isNumeric(studentId.trim()))
                                        throw new InvalidStudentException("Student id should only contain numbers!");

                                    Student stud = new Student(Long.parseLong(studentId.trim()), 0, firstNameInput.trim(), lastNameInput.trim());

                                    Student student = studentController.saveStudent(stud);

                                    if (student != null)
                                        System.out.println("STUDENT ALREADY EXISTS!");
                                    else
                                        System.out.println("STUDENT SUCCESSFULLY SAVED!");

                                } catch (IOException | InvalidStudentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          delete an existing student
                         */
                        else if (studentMenuInput.trim().equals("4")) {

                            deleteStudent: {

                                try {
                                    System.out.println("\nSTUDENTS AVAILABLE:\n\n");
                                    for (Student student : StudentRepository.getStudents()) {
                                        StudentRepository.printStudent(student);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER STUDENT ID:\n");

                                    String studentId = reader.readLine();

                                    if (studentId.trim().equals("-1"))
                                        break deleteStudent;

                                    if (studentId.trim().equals(""))
                                        throw new InvalidStudentException("Student id is required!");

                                    if (!isNumeric(studentId.trim()))
                                        throw new InvalidStudentException("Student id is must be numeric!");

                                    Student result_student = studentController.deleteStudent(Long.parseLong(studentId.trim()));

                                    if (result_student == null)
                                        System.out.println("STUDENT GIVEN DOES NOT EXIST!");
                                    else
                                        System.out.println("STUDENT DELETED SUCCESSFULLY!");

                                } catch (IOException | InvalidStudentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          update an existing student
                         */
                        else if (studentMenuInput.trim().equals("5")) {

                            updateStudent: {
                                try {
                                    System.out.println("\nSTUDENTS AVAILABLE:\n\n");
                                    for (Student student : StudentRepository.getStudents()) {
                                        StudentRepository.printStudent(student);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER STUDENT ID:\n");

                                    String studentId = reader.readLine();

                                    if (studentId.trim().equals("-1"))
                                        break updateStudent;

                                    if (studentId.trim().equals(""))
                                        throw new InvalidStudentException("Student id is required!");

                                    if (!isNumeric(studentId.trim()))
                                        throw new InvalidStudentException("Student id should be numeric!");

                                    System.out.println("\nENTER STUDENT NEW FIRSTNAME:\n");

                                    String firstName = reader.readLine();

                                    if (firstName.trim().equals("-1"))
                                        break updateStudent;

                                    if (firstName.trim().equals(""))
                                        throw new InvalidStudentException("Student firstname is required!");

                                    System.out.println("\nENTER STUDENT NEW LASTNAME:\n");

                                    String lastName = reader.readLine();

                                    if (lastName.trim().equals("-1"))
                                        break updateStudent;

                                    if (lastName.trim().equals(""))
                                        throw new InvalidStudentException("Student lastname is required!");

                                    Student newStudent = new Student(Long.parseLong(studentId.trim()), 0, firstName.trim(), lastName.trim());

                                    Student result_student = studentController.updateStudent(Long.parseLong(studentId.trim()), newStudent);

                                    if (result_student == null)
                                        System.out.println("STUDENT UPDATED SUCCESSFULLY!");
                                    else
                                        System.out.println("STUDENT UPDATED FAILED!");

                                } catch (IOException | InvalidStudentException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                        printStudentMenu();
                        studentMenuInput = reader.readLine();
                    }
                }
                /*
                  Print Teacher Menu
                 */
                else if (input.trim().equals("8")) {
                    printTeacherMenu();

                    String teacherMenuInput = reader.readLine();

                    while (!teacherMenuInput.equals("0")) {
                        /*
                          find one teacher
                         */
                        if (teacherMenuInput.trim().equals("1")) {

                            findOneTeacher: {
                                try {
                                    System.out.println("\nYOU CAN GO BACK BY ENTERING -1\n\n");

                                    System.out.println("ENTER ID TO SEARCH FOR:\n");

                                    // Reading data using readLine
                                    String inputId = reader.readLine();

                                    if (inputId.trim().equals("-1"))
                                        break findOneTeacher;

                                    if (!isNumeric(inputId.trim()))
                                        throw new InvalidTeacherException("Invalid Teacher id. Id must be numeric!");

                                    if (inputId.trim().length() < 1)
                                        throw new InvalidTeacherException("Teacher id is required!");

                                    Teacher teacher = teacherController.findOneTeacher(Long.parseLong(inputId.trim()));

                                    if (teacher != null)
                                        TeacherRepository.printTeacher(teacher);
                                    else
                                        System.out.println("TEACHER DOES NOT EXIST!");

                                } catch (IOException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          find all teachers
                         */
                        else if (teacherMenuInput.trim().equals("2")) {
                            var allTeachers = teacherController.findAllTeachers();

                            if (!allTeachers.isEmpty())
                                TeacherRepository.printTeachers();
                            else
                                System.out.println("NO TEACHERS AVAILABLE!");
                        }
                        /*
                          save a new teacher to database
                         */
                        else if (teacherMenuInput.trim().equals("3")) {

                            saveTeacher: {
                                try {
                                    System.out.println("\nENTER NEW TEACHER DETAILS:\n\n");

                                    System.out.println("YOU CAN EXIT ANYTIME BY ENTERING -1\n");

                                    System.out.println("\nID:\n");
                                    String id = reader.readLine();

                                    if (id.trim().equals("-1"))
                                        break saveTeacher;

                                    if (id.trim().equals(""))
                                        throw new InvalidTeacherException("Id is required!");

                                    if (!isNumeric(id.trim()))
                                        throw new InvalidTeacherException("Teacher id should only contain numbers!");

                                    System.out.println("\nFIRST NAME:\n\n");
                                    String firstName = reader.readLine();

                                    if (firstName.trim().equals("-1"))
                                        break saveTeacher;

                                    if (firstName.trim().equals(""))
                                        throw new InvalidTeacherException("Firstname is required!");

                                    System.out.println("\nLAST NAME:\n\n");
                                    String lastName = reader.readLine();

                                    if (lastName.trim().equals("-1"))
                                        break saveTeacher;

                                    if (lastName.trim().equals(""))
                                        throw new InvalidTeacherException("Lastname is required!");

                                    Teacher teacher = teacherController.saveTeacher(new Teacher(Long.parseLong(id.trim()), firstName.trim(), lastName.trim()));

                                    if (teacher != null)
                                        System.out.println("TEACHER ALREADY EXISTS!");
                                    else
                                        System.out.println("TEACHER SUCCESSFULLY SAVED!");

                                } catch (IOException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          delete an existing teacher
                         */
                        else if (teacherMenuInput.trim().equals("4")) {
                            deleteTeacher: {
                                try {
                                    System.out.println("\nTEACHERS AVAILABLE:\n\n");
                                    for (Teacher teacher : TeacherRepository.getTeachers()) {
                                        TeacherRepository.printTeacher(teacher);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER TEACHER ID:\n");

                                    String inputId = reader.readLine();

                                    if (inputId.trim().equals("-1"))
                                        break deleteTeacher;

                                    if (inputId.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher id is required!");

                                    if (!isNumeric(inputId.trim()))
                                        throw new InvalidTeacherException("Teacher id should only contain numbers!");

                                    Teacher result_teacher = teacherController.deleteTeacher(Long.parseLong(inputId.trim()));

                                    if (result_teacher == null)
                                        System.out.println("TEACHER GIVEN DOES NOT EXIST!");
                                    else
                                        System.out.println("TEACHER DELETED SUCCESSFULLY!");

                                } catch (IOException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          update an existing teacher
                         */
                        else if (teacherMenuInput.trim().equals("5")) {

                            updateTeacher: {
                                try {
                                    System.out.println("\nTEACHERS AVAILABLE:\n\n");
                                    for (Teacher teacher : TeacherRepository.getTeachers()) {
                                        TeacherRepository.printTeacher(teacher);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER TEACHER ID:\n");

                                    String teacherId = reader.readLine();

                                    if (teacherId.trim().equals("-1"))
                                        break updateTeacher;

                                    if (teacherId.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher id is required!");

                                    if (!isNumeric(teacherId.trim()))
                                        throw new InvalidTeacherException("Teacher id should only contain numbers!");

                                    System.out.println("\nENTER TEACHER NEW FIRSTNAME:\n");

                                    String firstName = reader.readLine();

                                    if (firstName.trim().equals("-1"))
                                        break updateTeacher;

                                    if (firstName.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher firstname is required!");

                                    System.out.println("\nENTER TEACHER NEW LASTNAME:\n");

                                    String lastName = reader.readLine();

                                    if (lastName.trim().equals("-1"))
                                        break updateTeacher;

                                    if (lastName.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher lastname is required!");

                                    Teacher newTeacher = new Teacher(Long.parseLong(teacherId.trim()), firstName.trim(), lastName.trim());

                                    Teacher result_teacher = teacherController.updateTeacher(Long.parseLong(teacherId.trim()), newTeacher);

                                    if (result_teacher == null)
                                        System.out.println("TEACHER UPDATED SUCCESSFULLY!");
                                    else
                                        System.out.println("TEACHER UPDATED FAILED!");

                                } catch (IOException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                        printTeacherMenu();
                        teacherMenuInput = reader.readLine();
                    }
                }
                /*
                  Print Course Menu
                 */
                else if (input.trim().equals("9")) {
                    printCourseMenu();

                    String courseMenuInput = reader.readLine();

                    while (!courseMenuInput.equals("0")) {
                        /*
                          find one course
                         */
                        if (courseMenuInput.trim().equals("1")) {
                            findOneCourse: {
                                try {
                                    System.out.println("\nYOU CAN GO BACK BY ENTERING -1\n\n");

                                    System.out.println("ENTER ID TO SEARCH FOR:\n");

                                    // Reading data using readLine
                                    String inputId = reader.readLine();

                                    if (inputId.trim().equals("-1"))
                                        break findOneCourse;

                                    if (!isNumeric(inputId.trim()))
                                        throw new InvalidCourseException("Invalid Course id. Id must be numeric!");

                                    if (inputId.trim().length() < 1)
                                        throw new InvalidCourseException("Course id is required!");

                                    Course course = courseController.findOneCourse(Long.parseLong(inputId.trim()));

                                    if (course != null)
                                        System.out.println(course);
                                    else
                                        System.out.println("COURSE DOES NOT EXIST!");

                                } catch (IOException | InvalidCourseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          find all courses
                         */
                        else if (courseMenuInput.trim().equals("2")) {
                            var myCourses = courseController.findAllCourses();

                            if (!myCourses.isEmpty())
                                CourseRepository.printCourses();
                            else
                                System.out.println("NO COURSES AVAILABLE!");
                        }
                        /*
                          save a new course to database
                         */
                        else if (courseMenuInput.trim().equals("3")) {
                            saveCourse: {
                                try {
                                    System.out.println("\nENTER NEW COURSE DETAILS:\n\n");

                                    System.out.println("YOU CAN EXIT ANYTIME BY ENTERING -1\n");

                                    System.out.println("\nID:\n");
                                    String id = reader.readLine();

                                    if (id.trim().equals("-1"))
                                        break saveCourse;

                                    if (id.trim().equals(""))
                                        throw new InvalidCourseException("Id is required!");

                                    if (!isNumeric(id.trim()))
                                        throw new InvalidCourseException("Course id should only contain numbers!");

                                    System.out.println("\nNAME:\n\n");
                                    String name = reader.readLine();

                                    if (name.trim().equals("-1"))
                                        break saveCourse;

                                    if (name.trim().equals(""))
                                        throw new InvalidCourseException("Name is required!");

                                    System.out.println("\nALL TEACHERS:\n\n");

                                    TeacherRepository.printTeachers();

                                    System.out.println("\n\nTEACHER ID:\n\n");
                                    String teacherId = reader.readLine();

                                    if (teacherId.trim().equals("-1"))
                                        break saveCourse;

                                    if (teacherId.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher id is required!");

                                    if (!isNumeric(teacherId.trim()))
                                        throw new InvalidTeacherException("Teacher ids are numeric!");


                                    Teacher teacher = null;
                                    for (Teacher t : TeacherRepository.getTeachers())
                                        if (t.getTeacherId().equals(Long.parseLong(teacherId.trim())))
                                            teacher = t;

                                    if (teacher == null)
                                        throw new InvalidTeacherException("Teacher does not exist!");

                                    System.out.println("\n\nMAX ENROLLMENT:\n\n");
                                    String maxEnrollment = reader.readLine();

                                    if (maxEnrollment.trim().equals("-1"))
                                        break saveCourse;

                                    if (maxEnrollment.trim().equals(""))
                                        throw new InvalidCourseException("Max Enrollment number is required!");

                                    if (!isNumeric(maxEnrollment.trim()))
                                        throw new InvalidCourseException("Max Enrollment number should only contain numbers!");

                                    if (Integer.parseInt(maxEnrollment.trim()) <= 0)
                                        throw new InvalidCourseException("Max Enrollment should not be negative!");

                                    System.out.println("\n\nCREDITS:\n\n");
                                    String credits = reader.readLine();

                                    if (credits.trim().equals("-1"))
                                        break saveCourse;

                                    if (credits.trim().equals(""))
                                        throw new InvalidCourseException("Credits is required!");

                                    if (!isNumeric(credits.trim()))
                                        throw new InvalidCourseException("Credits should only contain numbers!");

                                    if (Integer.parseInt(credits.trim()) <= 0)
                                        throw new InvalidCourseException("Credits should not be negative!");

                                    Course course = courseController.saveCourse(new Course(name.trim(), Long.parseLong(id.trim()), teacher, Integer.parseInt(maxEnrollment.trim()), new ArrayList<>(), Integer.parseInt(credits.trim())));

                                    if (course != null)
                                        System.out.println("COURSE ALREADY EXISTS!");
                                    else
                                        System.out.println("COURSE SUCCESSFULLY SAVED!");

                                } catch (IOException | InvalidCourseException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          delete an existing course
                         */
                        else if (courseMenuInput.trim().equals("4")) {
                            deleteCourse: {
                                try {
                                    System.out.println("\nCOURSES AVAILABLE:\n\n");
                                    for (Course course : CourseRepository.getCourses()) {
                                        System.out.println(course);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER COURSE ID:\n");

                                    String inputId = reader.readLine();

                                    if (inputId.trim().equals("-1"))
                                        break deleteCourse;

                                    if (inputId.trim().equals(""))
                                        throw new InvalidCourseException("Course id is required!");

                                    if (!isNumeric(inputId.trim()))
                                        throw new InvalidCourseException("Course id should only contain numbers!");

                                    Course result_course = courseController.deleteCourse(Long.parseLong(inputId.trim()));

                                    if (result_course == null)
                                        System.out.println("COURSE GIVEN DOES NOT EXIST!");
                                    else
                                        System.out.println("COURSE DELETED SUCCESSFULLY!");

                                } catch (IOException | InvalidCourseException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        /*
                          update an existing course
                         */
                        else if (courseMenuInput.trim().equals("5")) {
                            updateCourse: {
                                try {
                                    System.out.println("\nCOURSES AVAILABLE:\n\n");
                                    for (Course course : CourseRepository.getCourses()) {
                                        System.out.println(course);
                                    }

                                    System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                                    System.out.println("\nENTER COURSE ID:\n");

                                    String inputId = reader.readLine();

                                    if (inputId.trim().equals("-1"))
                                        break updateCourse;

                                    if (inputId.trim().equals(""))
                                        throw new InvalidCourseException("Course id is required!");

                                    if (!isNumeric(inputId.trim()))
                                        throw new InvalidCourseException("Course id should only contain numbers!");

                                    System.out.println("\nENTER COURSE NAME:\n");

                                    String name = reader.readLine();

                                    if (name.trim().equals("-1"))
                                        break updateCourse;

                                    if (inputId.trim().equals(""))
                                        throw new InvalidCourseException("Course name is required!");

                                    System.out.println("\n\nTEACHER ID:\n\n");
                                    String teacherId = reader.readLine();

                                    if (teacherId.trim().equals("-1"))
                                        break updateCourse;

                                    if (teacherId.trim().equals(""))
                                        throw new InvalidTeacherException("Teacher id is required!");

                                    if (!isNumeric(teacherId.trim()))
                                        throw new InvalidTeacherException("Teacher ids are numeric!");

                                    Teacher teacher = null;
                                    for (Teacher t : TeacherRepository.getTeachers())
                                        if (t.getTeacherId().equals(Long.parseLong(teacherId.trim())))
                                            teacher = t;

                                    if (teacher == null)
                                        throw new InvalidTeacherException("Teacher does not exist!");

                                    System.out.println("\n\nMAX ENROLLMENT:\n\n");
                                    String maxEnrollment = reader.readLine();

                                    if (maxEnrollment.trim().equals("-1"))
                                        break updateCourse;

                                    if (maxEnrollment.trim().equals(""))
                                        throw new InvalidCourseException("Max Enrollment number is required!");

                                    if (!isNumeric(maxEnrollment.trim()))
                                        throw new InvalidCourseException("Max Enrollment number should only contain numbers!");

                                    if (Integer.parseInt(maxEnrollment.trim()) <= 0)
                                        throw new InvalidCourseException("Max Enrollment should not be negative!");

                                    System.out.println("\n\nCREDITS:\n\n");
                                    String credits = reader.readLine();

                                    if (credits.trim().equals("-1"))
                                        break updateCourse;

                                    if (credits.trim().equals(""))
                                        throw new InvalidCourseException("Credits is required!");

                                    if (!isNumeric(credits.trim()))
                                        throw new InvalidCourseException("Credits should only contain numbers!");

                                    if (Integer.parseInt(credits.trim()) <= 0)
                                        throw new InvalidCourseException("Credits should not be negative!");

                                    Course course =
                                            new Course(name.trim(), Long.parseLong(inputId.trim()), teacher, Integer.parseInt(maxEnrollment.trim()), new ArrayList<>(), Integer.parseInt(credits.trim()));

                                    Course result = courseController.updateCourse(Long.parseLong(inputId.trim()), course);

                                    if (result == null)
                                        System.out.println("UPDATE SUCCESSFUL!");
                                    else
                                        System.out.println("UPDATED FAILED!");

                                } catch (IOException | InvalidCourseException | InvalidTeacherException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                        printCourseMenu();
                        courseMenuInput = reader.readLine();
                    }
                }
                /*
                  sort students on given criteria
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
                /*
                  sort teachers on given criteria
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
                /*
                  sort courses on given criteria
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
                /*
                  filter students on total credits
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

                /* REPRINT CONSOLE IF 0 WAS NOT THE INPUT */
                printConsole();
                input = reader.readLine();
            }

        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}
