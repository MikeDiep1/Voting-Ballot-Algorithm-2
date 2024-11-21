import java.io.IOException;
import java.util.Scanner;

/**
 * Serves as an abstract class for different types of election managers. It outlines
 * functionalities, like retrieving the number of seats, ballots, and objects (parties/candidates)
 * from an input file. It defines abstract methods that subclasses must implement to parse specific data
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public abstract class Manager {
    /**
     * Reads and returns the number of seats from the provided Scanner object.
     *
     * @param br The Scanner object reading from an input source.
     * @return The number of seats as an integer.
     */
    public int getNumSeats(Scanner br){
        int seats = -1;
        seats = Integer.parseInt(br.nextLine().trim());
        return seats;
    }
    /**
     * Reads and returns the number of ballots from the provided Scanner object.
     *
     * @param br The Scanner object reading from an input source.
     * @return The number of ballots as an integer.
     */
    public int getNumBallots(Scanner br){
        int ballots = -1;
        ballots = Integer.parseInt(br.nextLine().trim());
        return ballots;
    }
    /**
     * Reads and returns the number of objects (parties or candidates) from the provided Scanner object.
     *
     * @param br The Scanner object reading from an input source.
     * @return The number of objects as an integer.
     */
    public int getNumObjs(Scanner br){
        int objs = -1;
        objs = Integer.parseInt(br.nextLine().trim());
        return objs;
    }
    /**
     * Abstract method to parse party and return a {@link Party} object from the provided Scanner object.
     *
     * @param br The Scanner object for reading party data.
     * @param vote The {@link Voting} instance associated with this election.
     * @return A {@link Party} object parsed from the input source.
     */
    public abstract Party parseParty(Scanner br, Voting vote);
    /**
     * Abstract method to parse ballots from the provided Scanner object.
     *
     * @param br The Scanner object for reading ballot data.
     * @param num_ballots The number of ballots to be parsed.
     * @param vote_length The length of a single vote/ballot.
     * @param vote The {@link Voting} instance associated with this election.
     * @throws IOException If an I/O error occurs during parsing.
     */
    public abstract void parseBallots(Scanner br, int num_ballots, int vote_length, Voting vote) throws IOException ;
    /**
     * Abstract method to parse the CSV file and return a {@link Voting} object, representing
     * the election process.
     *
     * @param file The Scanner object for reading the CSV file.
     * @return A {@link Voting} object populated with the election data.
     * @throws IOException If an I/O error occurs during file parsing.
     */
    public abstract Voting parseCSV(Scanner file) throws IOException;
    /**
     * Abstract method to create and return a new {@link Voting} instance.
     *
     * @return A new {@link Voting} instance.
     */
    public abstract Voting makeVote();
    /**
     * Abstract method to create and return a new {@link Party} object with the specified name.     *
     * @param partyName The name of the party to create.
     * @return A new {@link Party} instance.
     */
    public abstract Party makeParty(String partyName);
    /**
     * Abstract method to create and return a new {@link Candidate} object with the specified name
     * and party affiliation.
     *
     * @param candidateName The name of the candidate to create.
     * @param partyAffiliation The party affiliation of the candidate.
     * @return A new {@link Candidate} instance.
     */
    public abstract Candidate makeCandidate(String candidateName, String partyAffiliation);

}
