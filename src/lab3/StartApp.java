package lab3;

import lab3.console.Console;

import java.io.IOException;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Console console = new Console();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
