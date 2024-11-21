import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages the operations to CPL voting system. This class extends from
 * {@code Manager} to provide functionality for parsing CSV files, containing party and candidate
 * information, ballots, and constructing the voting system structure for a CPL
 * election. It includes methods for creating party and candidate instances, as well as for
 * initializing and processing votes.
 * <p>
 * Note: This class has modified its parent's abstract methods to specifically support the
 * Closed Party List voting system.
 * </p>
 *
 * @author Bao Ha, Riandy Setiadi, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class CPLManager extends Manager{

    /**
     * Parses a party from Scanner object, associating it with a given {@code Voting} instance.
     * This method reads a line from the scanner, creates or retrieves a {@code CPLParty} instance,
     * and populates it with candidates listed in the CSV format.
     *
     * @param br The Scanner object used to read party and candidate data.
     * @param vote The {@code Voting} instance to which the party and candidates are to be added.
     * @return The {@code CPLParty} instance populated with its candidates.
     */
    @Override
    public CPLParty parseParty(Scanner br, Voting vote){
        String line;
        String[] elements;
        String partyName = "";
        CPLParty party = null;

        //get the line with the party and candidates
        line = br.nextLine();
        elements = line.split(",");
        partyName = elements[0].trim();

        //if the party doesn't exist, make it
        if (vote.getPartyDictionary().get(partyName)!=null) {
            party = (CPLParty) vote.getPartyDictionary().get(partyName);
        } else {
            party = makeParty(partyName);
        }
        String c_name;

        //make each candidate and add it to the party and vote objects.
        for (int i = 1; i < elements.length; i++){
            c_name = elements[i].trim();
            CPLCandidate candidate = makeCandidate(c_name, party.getPartyName());
            party.addCandidate(candidate.getCandidateName());
            vote.addCandidate(candidate);
        }
        return party;
    }
    /**
     * Parses ballots from a Scanner object, updating vote counts in the {@code Voting} instance
     * based on ballot data. This method processes a specified number of ballots, updating the
     * vote counts for each party according to the votes indicated in each ballot.
     *
     * @param br The Scanner object used to read ballot data.
     * @param num_ballots The number of ballots to process.
     * @param vote_length The number of options (parties/candidates) listed in each ballot.
     * @param vote The {@code Voting} instance to be updated with the ballot data.
     * @throws IOException If an I/O error occurs during ballot processing.
     */
    @Override
    public void parseBallots(Scanner br, int num_ballots, int vote_length, Voting vote) throws IOException { //abstract
        String line;
        ArrayList<String> order = vote.order;

        //for every ballot in the file
        for (int i = 0;i < num_ballots; i ++){
            line = br.nextLine();

            //for every party in the ballot
            for (int j = 0; j < vote_length; j ++){
                if (line.charAt(j)=='1'){
                    //if that party has a vote ('1'), add the vote to the party
                    vote.parties.get(vote.order.get(j)).addVote();
                }
            }
        }
    }
    /**
     * Parses a CSV file using a Scanner, constructing a {@code Voting} instance based on the file's contents.
     * This method initializes the voting system with seat numbers, total number of ballots, and party
     * and candidate data extracted from the CSV file.
     *
     * @param file The Scanner object used to read the CSV file.
     * @return A {@code Voting} instance initialized with data from the CSV file.
     * @throws IOException If an I/O error occurs during file parsing.
     */
    @Override
    public Voting parseCSV(Scanner file) throws IOException {
        Voting vote = makeVote();
        Scanner br = file;

        //parse and set seats, ballots, and object numbers
        int num_seats = getNumSeats(br);
        int num_ballots = getNumBallots(br);
        vote.setSeatNumbers(num_seats);
        vote.setNumBallots(num_ballots);
        int num_objs = getNumObjs(br);

        //for all of parties in the file, parse them.
        for (int i = 0;i < num_objs; i ++){
            CPLParty p = parseParty(br, vote);
            vote.addParty(p);//abstract method again
        }
        parseBallots(br, num_ballots, num_objs, vote);
        return vote;
    }

    /**
     * Creates and initializes an instance of {@code ClosedPartyList}.
     * This method is responsible for constructing a new voting system
     * instance, where parties are pre-listed and seats
     * are allocated based on party votes.
     *
     * @return A new instance of {@code ClosedPartyList} initialized for managing the voting process.
     * @see ClosedPartyList
     */
    @Override
    public ClosedPartyList makeVote(){
        return new ClosedPartyList();
    }

    /**
     * Constructs a new {@code CPLParty} object with the specified party name.
     * Assigning them a unique name as identification. It is crucial for managing information such as
     * candidates list and vote counts within the voting process.
     *
     * @param partyName The name of the party to be created.
     * @return A newly created {@code CPLParty} instance with the specified name.
     * @see CPLParty
     */
    @Override
    public CPLParty makeParty(String partyName) {
        CPLParty party = new CPLParty();
        party.setPartyName(partyName);
        return party;
    }

    /**
     * Creates a new {@code CPLCandidate} object, setting its name and party affiliation
     * Each candidate is associated with a specific party, and
     * allocation of seats based on party votes and the candidates' order within the party.
     *
     * @param candidateName The name of the candidate to be created.
     * @param partyAffiliation The name of the party the candidate is affiliated with.
     * @return A {@code CPLCandidate} instance with the given name and party affiliation.
     * @see CPLCandidate
     */
    @Override
    public CPLCandidate makeCandidate(String candidateName, String partyAffiliation) {
        CPLCandidate candidate = new CPLCandidate();
        candidate.setName(candidateName);
        candidate.setParty(partyAffiliation);
        return candidate;
    }
}
