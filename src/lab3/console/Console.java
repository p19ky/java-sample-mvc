package lab3.console;

import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    StudentController studentController = new StudentController();
    TeacherController teacherController = new TeacherController();
    CourseController courseController = new CourseController();

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

                if (input.trim().equals("4")) {
                    this.courseController.print();
                }
                else if (input.trim().equals("5")) {
                    this.studentController.print();
                } else if (input.trim().equals("6")) {
                    this.teacherController.print();
                }

                printConsole();
                input = reader.readLine();
            }

        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}
