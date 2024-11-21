import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages the Mutliple Popularity Only (MPO) voting process. This class is responsible for parsing election data
 * specific to MPO voting systems, including parsing parties and candidates from a CSV file, handling ballot data,
 * and constructing the voting system with the parsed data.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 *  @version 1.0
 * @since 1.0
 */
public class MPOManager extends Manager{

    /**
     * Parses a line from the CSV file to extract party and candidate information.
     * It splits the line into candidates and then extracts the candidate's name and affiliated party to
     * create {@link MPOParty} and {@link MPOCandidate} objects as needed.
     *
     * @param br The scanner to read the CSV file.
     * @param vote The voting system instance where the parsed party and candidates will be added.
     * @return Always returns null as this implementation does not need to return the party directly.
     */
    @Override
    public MPOParty parseParty(Scanner br, Voting vote){
        String line = br.nextLine();
//        split the one line into elements of [candidate, party]
        String[] cands = line.split("], ");

//        for every element, split it into a candidate string and a party string
        for (int i = 0; i < cands.length; i ++){
            String info;
            if (i == cands.length - 1){
                info = cands[i].substring(1, cands[i].length() - 1);
            }
            else
                info = cands[i].substring(1, cands[i].length());
            String[] info_ = info.split(", ");

//            create and populate the party and candidate objects based on the parsed String array
            MPOParty party;
            if (vote.getPartyDictionary().get(info_[1])==null){
                party = makeParty(info_[1]);
                vote.addParty(party);
            }
            else{
                party = (MPOParty) vote.getPartyDictionary().get(info_[1]);
            }

            party.addCandidate(info_[0]);
            MPOCandidate cand = makeCandidate(info_[0], info_[1]);
            vote.addCandidate(cand);

        }
        return null;
    }
    /**
     * Parses ballot data from the CSV file, updating vote counts for parties and candidates.
     *
     * @param br The scanner to read the CSV file.
     * @param num_ballots The number of ballots to process.
     * @param vote_length The number of options per ballot.
     * @param vote The voting system where the ballot data will be processed.
     * @throws IOException If an I/O error occurs while reading the file.
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
     * Parses the CSV file to construct the MPO voting system.
     *
     * @param file The scanner to read the CSV file.
     * @return The constructed Voting system based on the parsed data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Override
    public Voting parseCSV(Scanner file) throws IOException {//abstract
        Voting vote = makeVote();
        Scanner br = file;

        //parse and set seats, object numbers, candidate line, and ballot numbers
        int num_seats = getNumSeats(br);
        int num_objs = getNumObjs(br);

//        parse the line with all the candidate and party info
        parseParty(br, vote);
        int num_ballots = getNumBallots(br);
        vote.setSeatNumbers(num_seats);
        vote.setNumBallots(num_ballots);

//       parse the ballots
        parseBallots(br, num_ballots, num_objs, vote);

        return vote;
    }

    /**
     * Creates a new {@link MultiplePopularityOnly} instance to represent the voting system for MPO.
     *
     * @return A new instance of {@link MultiplePopularityOnly}.
     */
    @Override
    public MultiplePopularityOnly makeVote() {
        return new MultiplePopularityOnly();
    }
    /**
     * Creates a new {@link MPOParty} with the specified party name.
     *
     * @param partyName The name of the party to be created.
     * @return A new instance of {@link MPOParty} with the specified name.
     */
    @Override
    public MPOParty makeParty(String partyName) {
        MPOParty party = new MPOParty();
        party.setPartyName(partyName);
        return party;
    }
    /**
     * Creates a new {@link MPOCandidate} with the specified name and party affiliation.
     *
     * @param candidateName The name of the candidate to be created.
     * @param partyAffiliation The party the candidate is affiliated with.
     * @return A new instance of {@link MPOCandidate} with the specified name and party affiliation.
     */
    @Override
    public MPOCandidate makeCandidate(String candidateName, String partyAffiliation) {
        MPOCandidate candidate = new MPOCandidate();
        candidate.setName(candidateName);
        candidate.setParty(partyAffiliation);
        return candidate;
    }
}
