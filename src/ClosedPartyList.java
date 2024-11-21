/**
 * Implements a closed party list voting system. In this system, parties provide a list of candidates
 * in a fixed order, and seats are allocated based on the total votes they receive, with
 * candidates receiving seats based on their order in the list. This class extends {@code Voting},
 * providing implementations for seat allocation, party and candidate management in the
 * context of a closed party list system.
 * <p>
 * </p>
 *
 * @author Bao Ha, Riandy Setiadi, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public class ClosedPartyList extends Voting{
    /**
     * Allocates seats to candidates based on the total votes received by their party and
     * the order of candidates within each party. This method overrides {@code giveSeats}
     * from the {@code Voting}
     */
    @Override
    public void giveSeats() {

        //for every party, parse the candidates and give them a seat
        for (String party : parties.keySet()) {
            CPLParty partyObj = (CPLParty) parties.get(party);
            int availableSeats = partyObj.getAllocatedSeats();
            int numCandidates = partyObj.getCandidateList().size();

            //for each available seat, give the next candidate in the candidate list a seat.
            for (int i = 0; i < availableSeats && i < numCandidates; i ++){
                String seatForCan = partyObj.getCandidateList().get(i);
                candidates.get(seatForCan).setGotSeat();
            }
        }
    }
    /**
     * Adds a party to the voting system if it doesn't already exist. This method
     * ensures that each party is represented within the system.
     *
     * @param party The party to be added. If it isn't in the system.
     */
    @Override
    public void addParty(Party party) {
        if (!parties.containsKey(party.getPartyName())) {
            parties.put(party.getPartyName(), party);
            order.add(party.getPartyName());
        }
    }

    /**
     * Adds a candidate to the voting system, associating them with their party.
     *
     * @param candidate The candidate to be added. if they're not in the system.
     */
    @Override
    public void addCandidate(Candidate candidate) {
        if (!candidates.containsKey(candidate.getCandidateName())) {
            candidates.put(candidate.getCandidateName(), candidate);
        }
    }
}
