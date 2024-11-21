import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Handles the output of election results CPL. This class
 * extends {@code Output} for displaying results in the console
 * and writing them to an audit file. It includes methods for printing detailed results of the election,
 * including the calculation of seats based on the largest remainder method, and lists of candidates
 * and parties with their votes and seat allocations.
 * <p>
 * </p>
 *
 * @author Bao Ha, Riandy Setiadi, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 * @see Output
 */
public class CPLOutput extends Output{
    /**
     * Constructs a {@code CPLOutput} object associated with a specific {@code Voting} instance.
     * This constructor initializes the object to handle output for the given voting system.
     *
     * @param vote The {@code Voting} instance whose results this {@code CPLOutput} object will manage.
     */
    public CPLOutput(Voting vote) {
        this.vote = vote;
    }

    public void votingOutput(Voting vote){
        this.vote = vote;
    }//TODO: why is there two methods for making output
    /**
     * Displays the results of the CPL election to the console. This includes the election type,
     * total counts of parties, ballots, and seats, as well as detailed lists of candidates by party,
     * a breakdown of votes and seat allocations, and the winners of the seats.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Election Type: CPL\n");
        sb.append("Number of parties: ").append(vote.getNumParties()).append("\n");
        sb.append("Number of ballots: ").append(vote.getNumBallots()).append("\n");
        sb.append("Number of seats: ").append(vote.getSeatNumbers()).append("\n");
        HashMap<String, Party> parties = vote.getPartyDictionary();
        HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

        // Candidates
        sb.append("\nCandidates:\n");
        for (String party : parties.keySet()) {
            CPLParty partyObj = (CPLParty) parties.get(party);
            for (int i = 0; i < partyObj.getCandidateList().size(); i++){
                sb.append(partyObj.getCandidateList().get(i)).append(": ").append(partyObj.getPartyName()).append("\n");
            }
        }

        // Largest remainder calculation
        sb.append("Largest remainder calculation: ").append(vote.getNumBallots()).append(" / ").append(vote.getSeatNumbers())
                .append(" = ").append(vote.getNumBallots() / vote.getSeatNumbers()).append("\n");

        // Table representing seat allotments
        String header = String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s\n",
                "Parties", "Votes", "First Allocation", "Remaining Votes", "Second Allocation", "Final Seat", "% of Vote", "% of Seats");
        sb.append(header);
        for (Map.Entry<String, Party> partyEntry : parties.entrySet()) {
            String votePercent = vote.getVotePercentage(partyEntry.getValue());
            String seatPercent = vote.getSeatPercentage(partyEntry.getValue());
            String row = String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s\n",
                    partyEntry.getKey(),
                    partyEntry.getValue().getPartyVotes(),
                    partyEntry.getValue().getFirstAllocation(),
                    partyEntry.getValue().getRemainder(),
                    partyEntry.getValue().getSecondAllocation(),
                    partyEntry.getValue().getAllocatedSeats(),
                    votePercent,
                    seatPercent);
            sb.append(row);
        }

        // List of seat winners and their party affiliation
        sb.append("Seat Winners:\n");
        for (String can : candidates.keySet()) {
            Candidate cand = candidates.get(can);
            if (cand.getGotSeat()) {
                sb.append(can).append(": ").append(cand.getParty()).append("\n");
            }
        }

        return sb.toString();
    }
    /**
     * Displays the results of the CPL election to the console. This includes the election type,
     * total counts of parties, ballots, and seats, as well as detailed lists of candidates by party,
     * a breakdown of votes and seat allocations, and the winners of the seats.
     */
    public void displayResults() {

        System.out.println("Election Type: CPL");
        System.out.println("Number of parties: " + vote.getNumParties());
        System.out.println("Number of ballots: " + vote.getNumBallots());
        System.out.println("Number of seats: " + vote.getSeatNumbers());
        HashMap<String, Party> parties = vote.getPartyDictionary();
        HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

        // print out the candidates
        System.out.println("\nCandidates:");
        for (String party : parties.keySet()) {
            CPLParty partyObj = (CPLParty) parties.get(party);
            for (int i = 0; i < partyObj.getCandidateList().size(); i ++){
                System.out.println(partyObj.getCandidateList().get(i) + ": " + partyObj.getPartyName());
            }
        }
        // largest remainder calculation
        System.out.println("Largest remainder calculation: " + vote.getNumBallots() + " / " + vote.getSeatNumbers() +
                " = "  + (vote.getNumBallots() / vote.getSeatNumbers()));

        // table representing seat allotments
        System.out.println(String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s",
                "Parties",
                "Votes",
                "First Allocation",
                "Remaining Votes",
                "Second Allocation",
                "Final Seat",
                "% of Vote",
                "% of Seats"));
        for (Map.Entry<String, Party> partyEntry : parties.entrySet()) {
            String votePercent = vote.getVotePercentage(partyEntry.getValue());
            String seatPercent = vote.getSeatPercentage(partyEntry.getValue());
            System.out.println(String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s",
                    partyEntry.getKey(),
                    partyEntry.getValue().getPartyVotes(),
                    partyEntry.getValue().getFirstAllocation(),
                    partyEntry.getValue().getRemainder(),
                    partyEntry.getValue().getSecondAllocation(),
                    partyEntry.getValue().getAllocatedSeats(),
                    votePercent,
                    seatPercent));
        }

        // list of seat winners and their party affiliation
        System.out.println("Seat Winners:");
        for (String can : candidates.keySet()) {
            Candidate cand = candidates.get(can);

            if (cand.getGotSeat()) {
                System.out.println(can + ": " + cand.getParty());
            }
        }
    }
    /**
     * Generates an audit file containing the detailed results of the CPL election. The file includes
     * information similar to that displayed by {@link #displayResults()}, but saved to a file named
     * "output.txt" within a "testing" directory. This method handles file creation and management, ensuring
     * the results are recorded for audit purposes.
     */
    public void makeAudit(){
        try {
            // PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Project1/testing/output.txt")));
            // System.out.println("Output file path: " + new File("Project1/testing/output.txt").getAbsolutePath());
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../testing/output.txt")));
            System.out.println("Output file path: " + new File("../testing/output.txt").getAbsolutePath());


            writer.println("Election Type: CPL");
            writer.println("Number of parties: " + vote.getNumParties());
            writer.println("Number of ballots: " + vote.getNumBallots());
            writer.println("Number of seats: " + vote.getSeatNumbers());
            HashMap<String, Party> parties = vote.getPartyDictionary();
            HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

            // print out the candidates
            writer.println("\nCandidates:");
            for (String party : parties.keySet()) {
                CPLParty partyObj = (CPLParty) parties.get(party);
                for (int i = 0; i < partyObj.getCandidateList().size(); i ++){
                    writer.println(partyObj.getCandidateList().get(i) + ": " + partyObj.getPartyName());
                }
            }

            // largest remainder calculation
            writer.println("\n Largest remainder calculation: " + vote.getNumBallots() + " / " + vote.getSeatNumbers() +
                    " = " + (vote.getNumBallots() / vote.getSeatNumbers()));

            // table representing seat allotments
            writer.println(String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s",
                    "Parties",
                    "Votes",
                    "First Allocation",
                    "Remaining Votes",
                    "Second Allocation",
                    "Final Seat",
                    "% of Vote",
                    "% of Seats"));

            for (Map.Entry<String, Party> partyEntry : parties.entrySet()) {
                String votePercent = vote.getVotePercentage(partyEntry.getValue());
                String seatPercent = vote.getSeatPercentage(partyEntry.getValue());
                writer.println(String.format("%-14s %-10s %-19s %-17s %-20s %-15s %-13s %-13s",
                        partyEntry.getKey(),
                        partyEntry.getValue().getPartyVotes(),
                        partyEntry.getValue().getFirstAllocation(),
                        partyEntry.getValue().getRemainder(),
                        partyEntry.getValue().getSecondAllocation(),
                        partyEntry.getValue().getAllocatedSeats(),
                        votePercent,
                        seatPercent));
            }

            // list of seat winners and their party affiliation
            writer.println("Seat Winners");
            for (String can : candidates.keySet()) {
                Candidate cand = candidates.get(can);
                if (cand.getGotSeat()) {
                    writer.println(can + ": " + cand.getParty());
                }
            }
            writer.close();
        } catch (FileNotFoundException e){
            System.err.println("Error: File not found or cannot be created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
