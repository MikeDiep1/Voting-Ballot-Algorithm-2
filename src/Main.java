import javax.swing.*;
import java.io.IOException;
import java.sql.SQLOutput;

/**
 * The main class of the Election Result Processing System (ERPS). This class
 * initializes the application and provides two modes of interaction: a graphical user interface (GUI)
 * and a command-line interface (CLI). The GUI is launched upon execution, while the CLI begins right after
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class Main {
    /**
     * The main method that starts the application. It first initializes the GUI on the event dispatch thread
     * to ensure thread safety for Swing components. After initializing the GUI, it creates an instance of the
     * {@link Election} class and starts the command-line interface, allowing the user to interact with the
     * application through either interfaces.
     *
     * @param args The command-line arguments passed to the application. Currently, these are not used.
     * @throws IOException If an I/O error occurs during the execution of the command-line interface.
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please provide at least one file name.");
            return;
        }
        Election election = new Election();
        election.processFiles(args);
    }
}
