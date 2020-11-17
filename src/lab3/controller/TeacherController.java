package lab3.controller;

import lab3.exceptions.InvalidStudentException;
import lab3.exceptions.InvalidTeacherException;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

import static lab3.controller.StudentController.isNumeric;

public class TeacherController {
    private final TeacherRepository teacherRepository  = new TeacherRepository("teachers.txt");

    public TeacherController() {}

    public void print() {
        this.teacherRepository.printTeachers();
    }

    /**
     * find a teacher based on id.
     */
    public void findOneTeacher() {
        findOneTeacher: {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                System.out.println("\nYOU CAN GO BACK BY ENTERING -1\n\n");

                System.out.println("ENTER ID TO SEARCH FOR:\n");

                // Reading data using readLine
                String input = reader.readLine();

                if (input.trim().equals("-1"))
                    break findOneTeacher;

                if (!isNumeric(input.trim()))
                    throw new InvalidTeacherException("Invalid Teacher id. Id must be numeric!");

                if (input.length() < 1)
                    throw new InvalidTeacherException("Teacher id is required!");

                Teacher teacher = teacherRepository.findOne(Long.parseLong(input.trim()));

                TeacherRepository.printTeacher(teacher);

            } catch (IOException | InvalidTeacherException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Find all available teachers.
     */
    public void findAllTeachers() {
        teacherRepository.printTeachers();
    }


    /**
     * add a new teacher to database.
     */
    public void saveTeacher() {
        saveTeacher: {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                System.out.println("\nENTER NEW TEACHER DETAILS:\n\n");

                System.out.println("YOU CAN EXIT ANYTIME BY ENTERING -1\n");

                System.out.println("\nFIRST NAME:\n");
                String firstNameInput = reader.readLine();

                if (firstNameInput.trim().equals("-1"))
                    break saveTeacher;

                if (firstNameInput.trim().equals(""))
                    throw new InvalidTeacherException("Firstname is required!");

                System.out.println("\nLAST NAME:\n\n");
                String lastNameInput = reader.readLine();

                if (lastNameInput.trim().equals("-1"))
                    break saveTeacher;

                if (lastNameInput.trim().equals(""))
                    throw new InvalidTeacherException("Lastname is required!");

                System.out.println("\nTEACHER ID:\n\n");
                String teacherId = reader.readLine();

                if (teacherId.trim().equals("-1"))
                    break saveTeacher;

                if (teacherId.trim().equals(""))
                    throw new InvalidTeacherException("Teacher id is required!");

                if (!isNumeric(teacherId))
                    throw new InvalidTeacherException("Teacher id should only contain numbers!");

                Teacher teacher = teacherRepository.save(new Teacher(Long.parseLong(teacherId.trim()), firstNameInput.trim(), lastNameInput.trim()));

                if (teacher != null)
                    System.out.println("TEACHER ALREADY EXISTS!");
                else
                    System.out.println("TEACHER SUCCESSFULLY SAVED!");

            } catch (IOException | InvalidTeacherException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteTeacher() {
        deleteTeacher: {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {

                System.out.println("\nTEACHERS AVAILABLE:\n\n");
                for (Teacher teacher : TeacherRepository.getTeachers()) {
                    TeacherRepository.printTeacher(teacher);
                }

                System.out.println("\n\nYOU CAN EXIT AT ANYTIME BY ENTERING -1\n\n");

                System.out.println("\nENTER TEACHER ID:\n");

                String input = reader.readLine();

                if (input.trim().equals("-1"))
                    break deleteTeacher;

                if (input.trim().equals(""))
                    throw new InvalidTeacherException("Teacher id is required!");

                Teacher result_teacher = teacherRepository.delete(Long.parseLong(input.trim()));

                if (result_teacher == null)
                    System.out.println("TEACHER GIVEN DOES NOT EXIST!");
                else
                    System.out.println("TEACHER DELETED SUCCESSFULLY!");

            } catch (IOException | InvalidTeacherException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void updateTeacher() {
        updateTeacher: {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

                Teacher result_teacher = teacherRepository.update(Long.parseLong(teacherId.trim()), newTeacher);

                if (result_teacher == null)
                    System.out.println("TEACHER UPDATED SUCCESSFULLY!");
                else
                    System.out.println("TEACHER UPDATED FAILED!");

            } catch (IOException | InvalidTeacherException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * sort teachers on teacherId
     */
    public void sortTeachersOnId() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (int) (t1.getTeacherId() - t2.getTeacherId()));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on firstName
     */
    public void sortTeachersOnFirstName() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (t1.getFirstName().compareToIgnoreCase(t2.getFirstName())));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on lastName
     */
    public void sortTeachersOnLastName() {
        TeacherRepository.getTeachers().sort((t1, t2) -> (t1.getLastName().compareToIgnoreCase(t2.getLastName())));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }

    /**
     * sort teachers on number of courses
     */
    public void sortTeachersOnNumberOfCourses() {
        TeacherRepository.getTeachers().sort(Comparator.comparingInt(t -> t.getCourses(new ArrayList<>()).size()));
        for (Teacher teacher : TeacherRepository.getTeachers()) {
            TeacherRepository.printTeacher(teacher);
        }
    }
}
