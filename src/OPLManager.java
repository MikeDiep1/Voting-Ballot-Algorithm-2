import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages the parsing and processing of Open Party List (OPL) election data. This class extends
 * {@link Manager} to implement OPL-specific logic for reading from a CSV file,
 * including the creation of parties and candidates, and the votes based on OPL rules.
 * It defines methods for constructing the voting system, parties, and candidates, as well as for
 * parsing party information and ballots from the input data.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class OPLManager extends Manager{

    /**
     * Parses party information from the CSV file. It creates an {@link OPLParty} instance
     * for the party and {@link OPLCandidate} instances for each candidate listed.
     * Candidates are added to both the party and the global candidate list in the {@link Voting}.
     *
     * @param br The scanner reading from the CSV file.
     * @param vote The {@link Voting} instance representing the current election context.
     * @return The {@link OPLParty} instance created or found for the line of input.
     */
    @Override
    public OPLParty parseParty(Scanner br, Voting vote){
        String line;
        String[] elements;
        String partyName = "";
        OPLParty party = null;

        //get the line with the party and candidate
        line = br.nextLine();
        elements = line.split(",");
        partyName = elements[0].trim();

        //if the party doesn't exist, make it
        if (vote.getPartyDictionary().get(partyName)!=null){
            party = (OPLParty) vote.getPartyDictionary().get(partyName);
        }
        else
            party = makeParty(partyName);
        String c_name;
        //make each candidate and add it to the party and vote objects.
        for (int i = 1; i < elements.length; i++){
            c_name = elements[i].trim();
            OPLCandidate candidate = makeCandidate(c_name, party.getPartyName());
            party.addCandidate(candidate.getCandidateName());
            vote.addCandidate(candidate);
        }
        return party;
    }
    /**
     * Parses ballots from the CSV file, incrementing vote counts for both candidates and their
     * parties based on the ballot data. This method updates the vote totals,
     * where each vote for a candidate also counts as a vote for the candidate's party.
     *
     * @param br The scanner reading ballot data from the CSV file.
     * @param num_ballots The total number of ballots to be processed.
     * @param vote_length The number of candidates or options on each ballot.
     * @param vote The {@link Voting} context of the election for updating vote counts.
     * @throws IOException If an error occurs during ballot parsing.
     */
    @Override
    public void parseBallots(Scanner br, int num_ballots, int vote_length, Voting vote) throws IOException {

        String line;
        ArrayList<String> order = vote.order;
        //for every ballot in the file
        for (int i = 0;i < num_ballots; i ++){
            line = br.nextLine();

            //for every candidate in the ballot
            for (int j = 0; j < vote_length; j ++){
                if (line.charAt(j)=='1'){
                    //if that candidate has a vote ('1'), add the vote to the candidate and the party
                    vote.candidates.get(vote.order.get(j)).addVote();
                    String party = vote.candidates.get(vote.order.get(j)).getParty();
                    vote.parties.get(party).addVote();
                }
            }

        }
    }
    /**
     * Parses the CSV file to initialize and populate an {@link OpenPartyList} instance representing
     * the OPL election. This includes setting up the election parameters, parsing party and candidate
     * information, and processing ballots.
     *
     * @param file The scanner reading from the CSV file.
     * @return An {@link OpenPartyList} instance fully populated with the parsed election data.
     * @throws IOException If an error occurs during CSV parsing.
     */
    @Override
    public Voting parseCSV(Scanner file) throws IOException {//abstract
        Voting vote = makeVote();
        Scanner br = file;

        //parse and set seats, ballots, and object numbers
        int num_seats = getNumSeats(br);
        int num_ballots = getNumBallots(br);
        vote.setSeatNumbers(num_seats);
        vote.setNumBallots(num_ballots);
        int num_objs = getNumObjs(br);

        //for all of the candidate/party pairs in the file, parse the party.
        for (int i = 0;i < num_objs; i ++){
            OPLParty p = parseParty(br, vote);
            vote.addParty(p);
        }

        parseBallots(br, num_ballots, num_objs, vote);

        return vote;
    }

    /**
     * Creates a new {@link OpenPartyList} instance, specific to managing an OPL election.
     *
     * @return A new instance of {@link OpenPartyList}.
     */
    @Override
    public OpenPartyList makeVote() {
        return new OpenPartyList();
    }
    /**
     * creates a new {@link OPLParty} with the given party name.
     *
     * @param partyName The name of the party to create.
     * @return A new {@link OPLParty} instance with the specified name.
     */
    @Override
    public OPLParty makeParty(String partyName) {
        OPLParty party = new OPLParty();
        party.setPartyName(partyName);
        return party;
    }
    /**
     * Creates a new {@link OPLCandidate} with the specified name and party affiliation.
     *
     * @param candidateName The name of the candidate to create.
     * @param partyAffiliation The party with which the candidate is affiliated.
     * @return A new {@link OPLCandidate} instance.
     */
    @Override
    public OPLCandidate makeCandidate(String candidateName, String partyAffiliation) {
        OPLCandidate candidate = new OPLCandidate();
        candidate.setName(candidateName);
        candidate.setParty(partyAffiliation);
        return candidate;
    }
}
