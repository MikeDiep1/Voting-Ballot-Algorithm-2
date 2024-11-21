import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The main class for managing election processes. This class handles the initialization and execution
 * based on input file, such as Open Party List (OPL) and Closed Party List (CPL)
 * It supports operations that validate the input file, reading election
 * details from the file, and executing the election process accordingly.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class Election {
    Voting vote = null;
    /**
     * Validates if the input filename corresponds to an existing file in the "testing" directory and checks its existence.
     *
     * @param filename The name of the file to validate.
     * @return true if the file exists, false otherwise.
     */
    public static boolean validFile(String filename) {
        // File file = new File("Project1/testing/"+filename);
        File file = new File("../testing/"+filename);

        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            System.out.println("file exists");
            return true;
        }
        return false;
    }

    /**
     * Processes multiple election data files, combines their contents into a single file,
     * and initiates the election process. It handles the addition of ballot data across
     * multiple files and updates the total ballots count.
     *
     * @param filenames an array of file names to be processed
     * @throws IOException if an I/O error occurs during file processing
     */

    public void processFiles(String[] filenames) throws IOException {

        // creates a file to combine the files being passed in
        String combinedFilePath = "../testing/combinedElectionData.csv";
        String line;

        // reads in the first file passed in and detects what type of election it is
        Scanner scanner = new Scanner(new File("../testing/"+filenames[0]));
        line = scanner.nextLine();
        System.out.println(line);
        scanner.close();

        // opens the files
        int totalBallots = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(combinedFilePath))) {
            boolean firstFile = true;
            for (String filename : filenames) {
                String filePath = "../testing/" + filename;
                if (!validFile(filePath)) {
                    System.out.println("Filename invalid or does not exist: " + filePath);
                    continue;
                }
                // if this is the first file, we use copy it and use it as a base
                if (firstFile) {
                    totalBallots += copyHeadersAndReturnBallots(filePath, writer, line);
                    firstFile = false;
                } else {
                    // if this is not the first file we append the ballots onto the copy of the first file
                    totalBallots += appendBallotsOnly(filePath, writer, line);
                }
            }
            // updates the total ballots (either line 2 or 4 depending on election type)
            updateTotalBallots(combinedFilePath, totalBallots, line);
            writer.close();
            // start of the program
            startProgram(overwriteVotes(combinedFilePath, totalBallots, line));
        }
    }

    /**
     * Creates a modified copy of the original file with updated vote totals.
     *
     * @param originalFileName the path to the original file
     * @param totalBallots the updated total number of ballots to overwrite in the file
     * @param type the type of election (used to determine file processing logic)
     * @return the path to the modified file with updated vote totals
     * @throws IOException if an error occurs during file writing
     */

    public String overwriteVotes(String originalFileName, int totalBallots, String type) throws IOException {
        // create a BufferedReader to read from the original file.
        File file = new File(originalFileName);
        System.out.println(file.getAbsolutePath());
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int lineNumber = 0;
            int ballotInd = -1;
            // read all lines from the file and modify the third line (index 2).
            if (type.equals("OPL") || type.equals(("CPL"))){
                ballotInd = 2;
            }
            else if (type.equals("MPO") || type.equals(("MV"))){
                ballotInd = 4;
            }
            while (line != null) {
                if (lineNumber == ballotInd) {
                    // this is the third line where we need to update the total number of ballots.
                    lines.add(String.valueOf(totalBallots));
                } else {
                    // for other lines, just add them to the list as they are.
                    lines.add(line);
                }
                lineNumber++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error reading from the file: " + originalFileName, e);
        }
        // now write the updated content back to the file using the writer.
        // ensure that we flush and close the writer after writing to make sure all changes are committed.
        String newFile = "../testing/combinedElectionDataFinal.csv";

        BufferedWriter newWriter = new BufferedWriter(new FileWriter(newFile));
            for (String line : lines) {
                newWriter.write(line);
                newWriter.newLine();
            }
            newWriter.close();
        return newFile;
    }

    /**
     * Copies headers and ballot data from the specified file, returning the count of ballots.
     *
     * @param filePath the file path to copy data from
     * @param writer the BufferedWriter to write data to the combined file
     * @param type the type of election
     * @return the number of ballots read from the file
     * @throws IOException if an error occurs during file reading or writing
     */
    public int copyHeadersAndReturnBallots(String filePath, BufferedWriter writer, String type) throws IOException {
        int ballotsCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int ballotInd = -1;

            // if OPL/CPL ballots listed is line 2
            if (type.equals("OPL") || type.equals(("CPL"))){
                ballotInd = 2;
            }
            // if MPO/MV ballots listed is line 4
            else if (type.equals("MPO") || type.equals(("MV"))){
                ballotInd = 4;
            }
            int lineNumber = 0;

            // copies the contents of the file to a new file
            while ((line = reader.readLine()) != null) {

                writer.write(line);
                writer.newLine();
                if (lineNumber == ballotInd) {
                    ballotsCount = Integer.parseInt(line.trim());
                }
                lineNumber++;
            }
            return ballotsCount;
        }
    }

    /**
     * Appends only ballot data from the specified file to a combined file.
     *
     * @param filePath the file path to append data from
     * @param writer the BufferedWriter to append data to the combined file
     * @param type the type of election
     * @return the number of ballots appended
     * @throws IOException if an error occurs during file reading or writing
     */
    public int appendBallotsOnly(String filePath, BufferedWriter writer, String type) throws IOException {
        int ballotsCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int ballotInd = -1;

            // if OPL/CPL ballots listed is line 2
            if (type.equals("OPL") || type.equals(("CPL"))){
                ballotInd = 2;
            }
            // if MPO/MV ballots listed is line 4
            else if (type.equals("MPO") || type.equals(("MV"))){
                ballotInd = 4;
            }

            boolean pastHeader = false; // flag to indicate when headers have ended
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (lineNumber == ballotInd) {
                    ballotsCount = Integer.parseInt(line.trim()); // extract the number of ballots from the third line
                } else if (lineNumber > 3 && !pastHeader) {
                    if (line.trim().matches("^[1,]+$")) { // use regex to detect the start of ballot data (assumes data is numeric or commas)
                        pastHeader = true;
                    }
                }
                if (pastHeader) {
                    writer.write(line);
                    writer.newLine();
                }
                lineNumber++;
            }
        }
        return ballotsCount;
    }

    /**
     * Updates the total ballots count in a file after all ballots have been processed.
     *
     * @param filePath the file path where the ballots count needs to be updated
     * @param totalBallots the total number of ballots to write
     * @param type the type of election
     * @throws IOException if an error occurs during file writing
     */
    public void updateTotalBallots(String filePath, int totalBallots, String type) throws IOException {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int ballotInd = -1;

            // if OPL/CPL ballots listed is line 2
            if (type.equals("OPL") || type.equals(("CPL"))){
                ballotInd = 2;
            }
            // if MPO/MV ballots listed is line 4
            else if (type.equals("MPO") || type.equals(("MV"))){
                ballotInd = 4;

            }
            int lineNumber = 0;


            while ((line = reader.readLine()) != null) {
                if (lineNumber == ballotInd) {
                    fileContent.add(String.valueOf(totalBallots));  // Update the total ballots count
                } else {
                    fileContent.add(line);
                }
                lineNumber++;
            }
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String contentLine : fileContent) {
                writer.write(contentLine);
                writer.newLine();
            }
        }
    }



    /**
     * Start by reading the input file, determining the type of election (OPL or CPL),
     * and initializing the appropriate manager for the election type. It also handles the display of results
     * and creation of an audit file based on the election type.
     *
     * @param file The path to the election file to be processed.
     * @throws IOException If an error occurs during file reading or processing.
     */
    public void startProgram(String file) throws IOException {
        try (Scanner fileScanner = new Scanner(new File(file))) {

            //read in the first line to check election type
            String type = fileScanner.nextLine().trim();
            Manager manager = null;
            if (type.equals("OPL")) {
                manager = makeOPLManager();
            } else if (type.equals("CPL")) {
                manager = makeCPLManager();
            }
            else if (type.equals("MPO")) {
                manager = makeMPOManager();
            }
            else if (type.equals("MV")){
                System.out.println("Thanks for inputting an MV election. We cannot process that at the moment.");
                return;
            }

            //if the manager was created, begin to parse the file. Allocate the seats based on the parsed information
            if (manager != null) {
                vote = manager.parseCSV(fileScanner);
                vote.allocateSeats();
                //create output object based on voting type
                if (type.equals("OPL")) {
                    OPLOutput output = new OPLOutput(vote);
                    output.displayResults();
                    output.makeAudit();
                }else if (type.equals("CPL")) {
                    CPLOutput output = new CPLOutput(vote);
                    output.displayResults();
                    output.makeAudit();
                }
                else if (type.equals("MPO")) {

                    MPOOutput output = new MPOOutput(vote);
                    output.displayResults();
                    output.makeAudit();
                }
            } else {
                System.err.println("Invalid election type specified in the file.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }

    /**
     * Creates an instance of {@link OPLManager} for managing an Open Party List (OPL) election.
     *
     * @return A new instance of {@code OPLManager}.
     */
    public OPLManager makeOPLManager() {
        return new OPLManager();
    }
    /**
     * Creates an instance of {@link CPLManager} for managing a Closed Party List (CPL) election.
     *
     * @return A new instance of {@code CPLManager}.
     */
    public CPLManager makeCPLManager() {
        return new CPLManager();
    }
    /**
     * Factory method to create an instance of MPOManager, specialized for managing Mixed Party-Open List elections.
     *
     * @return a new instance of MPOManager
     */
    public MPOManager makeMPOManager() {
        return new MPOManager();
    }
    /**
     * Factory method to create an instance of MVManager, specialized for managing Majority Vote elections.
     *
     * Note: This function is currently not operational as Majority Vote elections are not supported.
     *
     * @return a new instance of MVManager
     */
    public MVManager makeMVManager(){
        return new MVManager();
    }
}