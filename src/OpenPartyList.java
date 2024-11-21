import java.util.ArrayList;
/**
 * Implements the open party list voting system. In this system,
 * voters can vote for individual candidates rather than parties, and seats are allocated to candidates
 * based on the total votes each candidate receives, within their parties.
 * <p>
 * This class overrides specific methods to allocate seats to candidates based on their vote count,
 * to add parties, and to manage candidates
 * </p>
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class OpenPartyList extends Voting{
    /**
     * Allocates seats to candidates within each party based on the number of votes received. The method
     * iterates over each party, allocating seats to the candidates with the highest vote counts, and
     * resolves ties through a tie-breaking mechanism if there's a tie.
     */
    @Override
    public void giveSeats() {

        //for every party, parse the candidates and give them a seat based on their votes
        for (String party : parties.keySet()) {
            OPLParty partyObj = (OPLParty) parties.get(party);
            int availableSeats = partyObj.getAllocatedSeats();

            //while there are available seats, find the candidate with the most votes and give them a seat.
            while (availableSeats != 0) {
                int largestVotes = 0;
                String candidateName = null;
                ArrayList<String> tie = new ArrayList<>();

                //walk through candidates list and check their votes
                for (int i = 0; i < partyObj.getCandidateList().size(); i++) {
                    OPLCandidate oplCan = (OPLCandidate) candidates.get(partyObj.getCandidateList().get(i));
                    if(oplCan.getCandidateVotes() > largestVotes && !oplCan.getGotSeat()) {
                        largestVotes = oplCan.getCandidateVotes();
                        candidateName = oplCan.getCandidateName();
                    }
                    else if(oplCan.getCandidateVotes() == largestVotes && !oplCan.getGotSeat()) {
                        tie.add(oplCan.getCandidateName());
                    }
                }

                //if there is a tie, use the tiebreaker to randomly select a winner
                while (!tie.isEmpty()){
                    String t = tie.remove(0);
                    candidateName = breakTie(t, candidateName);
                }
                candidates.get(candidateName).setGotSeat();
                availableSeats--;
            }
        }
    }

    /**
     * Adds a party to the election if it is not already present.
     *
     * @param party The party to be added to the election.
     */
    // Add a party to both the HashMap and the ArrayList for order tracking
    @Override
    public void addParty(Party party) {
        if (!parties.containsKey(party.getPartyName())) {
            parties.put(party.getPartyName(), party);
        }
    }

    /**
     * Adds a candidate to the election and the corresponding party's list of candidates.
     *
     * @param candidate The candidate to be added to the election.
     */
    @Override
    public void addCandidate(Candidate candidate) {
        if (!candidates.containsKey(candidate.getCandidateName())) {
            candidates.put(candidate.getCandidateName(), candidate);
            order.add(candidate.getCandidateName());
        }
    }
}
