import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * Handles the output of election results for MPO. Extends
 * the {@code Output} class to display results in the
 * console and writing them to an audit file for MPO's format
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class MPOOutput extends Output{
    /**
     * Constructs an {@code MPOOutput} object associated with {@code Voting} instance,
     * preparing it to handle output for the given election results.
     *
     * @param vote The {@code Voting} instance results this object will manage.
     */
    public MPOOutput(Voting vote) {
        this.vote = vote;
    }
    /**
     * Displays the results in the console, including detailed breakdowns of
     * party votes, seat allocations, and individual candidate results.
     */
    public void votingOutput(Voting vote){
        this.vote = vote;
    }//TODO: why is there two methods for making output
    /**
     * Displays the results in a string, including detailed breakdowns of
     * party votes, seat allocations, and individual candidate results.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Election Type: MPO\n");
        sb.append("Number of parties: ").append(vote.getNumParties()).append("\n");
        sb.append("Number of ballots: ").append(vote.getNumBallots()).append("\n");
        sb.append("Number of seats: ").append(vote.getSeatNumbers()).append("\n");
        HashMap<String, Party> parties = vote.getPartyDictionary();
        HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

        // Candidates
        sb.append("\nCandidates:\n");
        for (String party : parties.keySet()) {
            MPOParty partyObj = (MPOParty) parties.get(party);
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
            String row = String.format("%-15s %-15d %-15d %-15d %-15d %-15d %-15s %-15s\n",
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
                sb.append(can).append(": ").append(cand.getParty()).append(" received ").append(cand.getCandidateVotes()).append(" Votes").append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Displays the results in the console, including detailed breakdowns of
     * party votes, seat allocations, and individual candidate results.
     */
    public void displayResults() {

        System.out.println("Election Type: MPO");
        System.out.println("Number of parties: " + vote.getNumParties());
        System.out.println("Number of ballots: " + vote.getNumBallots());
        System.out.println("Number of seats: " + vote.getSeatNumbers());
        HashMap<String, Party> parties = vote.getPartyDictionary();
        HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

        // print out the candidates
        System.out.println("\nCandidates:");
        for (String party : parties.keySet()) {
            MPOParty partyObj = (MPOParty) parties.get(party);
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
                System.out.println(can + ": " + cand.getParty() + " received " + cand.getCandidateVotes() + " Votes");
            }
        }
    }
    /**
     * Generates an audit file containing the detailed results, ensuring the
     * election process is transparent and the results are verifiable.
     */
    public void makeAudit(){
        try {
            // these are for lab machines
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../testing/output.txt")));
            System.out.println("Output file path: " + new File("../testing/output.txt").getAbsolutePath());
            // these are for our local machines
            // PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Project1/testing/output.txt")));
            // System.out.println("Output file path: " + new File("Project1/testing/output.txt").getAbsolutePath());

            writer.println("Election Type: MPO");
            writer.println("Number of parties: " + vote.getNumParties());
            writer.println("Number of ballots: " + vote.getNumBallots());
            writer.println("Number of seats: " + vote.getSeatNumbers());
            HashMap<String, Party> parties = vote.getPartyDictionary();
            HashMap<String, Candidate> candidates = vote.getCandidatesDictionary();

            // print out the candidates
            writer.println("\nCandidates:");
            for (String party : parties.keySet()) {
                MPOParty partyObj = (MPOParty) parties.get(party);
                for (int i = 0; i < partyObj.getCandidateList().size(); i ++){
                    writer.println(partyObj.getCandidateList().get(i) + ": " + partyObj.getPartyName());
                }
            }

            // largest remainder calculation
            writer.println("\nLargest remainder calculation: " + vote.getNumBallots() + " / " + vote.getSeatNumbers() +
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
            writer.println("Seat Winners:");
            for (String can : candidates.keySet()) {
                Candidate cand = candidates.get(can);
                if (cand.getGotSeat()) {
                    writer.println(can + ": " + cand.getParty() + " received " + cand.getCandidateVotes() + " Votes");
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
