import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Provides a graphical user interface for the Election Result Processing System (ERPS). This class
 * extends {@link JFrame} to create a window, where users can input the name of a file containing
 * election data to be processed. It validates the file's existence within a predefined directory
 * ("testing/") and initiates the election processing if the file is valid.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class GUI extends JFrame {
    /**
     * Constructs the GUI and initializes the user interface components.
     */
    public GUI(){
        makeGUI();
    }
    /**
     * Provides a graphical user interface for the Election Result Processing System (ERPS). This class
     * extends {@link JFrame} to create a window, where users can input the name of a file containing
     * election data to be processed. It validates the file's existence within a predefined directory
     * ("testing/") and initiates the election processing if the file is valid.
     */
    public static boolean validFile(String filename) {
        File file = new File("../testing/"+filename);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            System.out.println("file exists");
            return true;
        }
        return false;
    }

    /**
     * Initializes and arranges the GUI components. This method sets up the frame, labels, text field,
     * and submit button. When user click the submit button,
     * the program starts validating the input file and process the election results.
     */
    public void makeGUI() {
        //JFrame setup
        setTitle("ERPS");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //setup for labels, text field, and button
        JLabel title = new JLabel("Welcome to the ERPS System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruction = new JLabel("Please enter file name below:");
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField textField = new JTextField(20);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        textField.setBorder(border);
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Submit");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        //button action
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submitted Text: " + textField.getText());
                if (validFile(textField.getText())){
                    Election elect = new Election();
                    try {
                        elect.startProgram("../testing/"+textField.getText());
                        displayResults();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    // Notify user of invalid filename
                    JOptionPane.showMessageDialog(GUI.this, "Invalid file name. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        // Add components to the JFrame
        add(Box.createVerticalStrut(60));

        add(title);
        add(Box.createVerticalStrut(10));

        add(instruction);
        add(Box.createVerticalStrut(10));
        add(textField);
        add(Box.createVerticalStrut(10));
        add(button);

        // Make the JFrame visible
        setVisible(true);
    }

    /**
     * Provides a graphical user interface for the Election Result Processing System (ERPS). This class
     * extends {@link JFrame} to create a window, where users can see the results of the election.
     */
    public void displayResults() {
        JFrame resultsFrame = new JFrame("Election Results");
        resultsFrame.setSize(500, 300);
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.setLayout(new BoxLayout(resultsFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel resultsLabel = new JLabel("Please see terminal for results or check output.txt");
        resultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultsFrame.add(Box.createVerticalStrut(30));
        resultsFrame.add(resultsLabel);
        resultsFrame.setVisible(true);
    }

}
