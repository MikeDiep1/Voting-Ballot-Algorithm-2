import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Multiple Popularity Only (MPO) voting system where multiple winners are chosen
 * based on the highest number of votes received. Ties are resolved through a random selection process.
 * This class handles the allocation of seats to candidates based on their popularity (number of votes),
 * ensuring that the highest voted candidates are given priority in seat allocation.
 *
 * The class overrides methods from the {@link Voting} abstract class
 * for adding parties, adding candidates, and allocating seats based on the MPO voting rules.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class MultiplePopularityOnly extends Voting{
    /**
     * Allocates seats to candidates based on the number of votes they have received. The method
     * iterates over all candidates, selecting the candidate(s) with the highest votes for available seats.
     * In the event of a tie, a random tie-breaking mechanism determines the seat allocation
     */
    @Override
    public void giveSeats() {
        int availableSeats = seatNumbers;
        List<String> tie = new ArrayList<>();
        int greatestVotes = -1;
        while (availableSeats > 0){
//            for every candidate, if they have the highest number of votes and don't have a seat, then add them to the ties array
            for (String cand : order){
                if (candidates.get(cand).getCandidateVotes() > greatestVotes && !candidates.get(cand).getGotSeat()){
                    tie.clear();
                    tie.add(cand);
                    greatestVotes = candidates.get(cand).getCandidateVotes();
                }

                else if (candidates.get(cand).getCandidateVotes() == greatestVotes && !candidates.get(cand).getGotSeat()){
                    tie.add(cand);
                }

            }
//           make sure ties is not empty
            if (tie.isEmpty())
                break;
//            pick a random winner from those who are tied to give the seat
            List<String> winner = pickRandom(tie, 1);
            candidates.get(winner.get(0)).setGotSeat();
            availableSeats--;
            greatestVotes = -1;
            tie.clear();

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
