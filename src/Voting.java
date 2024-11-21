import java.util.*;
import java.util.List;
/**
 * Abstract class provides the framework for handling parties, candidates, ballots, and seat allocation,
 * serving as the foundation for implementing specific types of voting systems.
 * It includes mechanisms for seat allocation based on votes, breaking ties between candidates
 * or parties, and calculating percentage of votes and seats. Subclasses are
 * expected to implement specific details of the voting and seat allocation processes for
 * different voting system
 *
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public abstract class Voting{

    protected HashMap<String, Party> parties = new HashMap<>();
    protected HashMap<String, Candidate> candidates = new HashMap<>();
    protected int numBallots;
    protected ArrayList<String> order = new ArrayList<>();//TODO fix order, it's containint both candidates and parties f
    protected int seatNumbers;

    /**
     * Constructor for the Voting class. Initializes a new instance of a voting system
     * with the default number of seats set to 0.
     */
    public Voting() {
        this.seatNumbers = 0;
    }

    /**
     * Breaks a tie between two entities (such as candidates or parties) by randomly selecting one.
     *
     * @param a The first entity involved in the tie.
     * @param b The second entity involved in the tie.
     * @return The entity selected to win the tie.
     */
    public String breakTie(String a, String b){
        return (Math.random() < 0.5) ? a : b;
    }

    /**
     * Randomly picks a specified number of items from a list.
     * This method shuffles the list to ensure randomness and then returns the desired number of elements from the beginning of the shuffled list.
     * If the requested number exceeds the list size, it returns as many items as are available.
     *
     * @param tiedObjects The list of items from which to randomly pick elements.
     * @param number The number of items to pick from the list.
     * @return A list containing the randomly picked items. The size of the returned list is the lesser of the number requested or the size of the input list.
     */
    public static List<String> pickRandom(List<String> tiedObjects, int number) {
        //shuffle the list
        Collections.shuffle(tiedObjects);

        //return the first 'numberOfWinners' elements
//        System.out.println(tiedObjects);
        return tiedObjects.subList(0, Math.min(number, tiedObjects.size()));
    }
    /**
     * Performs the first allocation of seats to parties based on the total votes each party has received.
     * This method uses a quota or divisor method, to fairly
     * distribute seats among the parties. The initial allocation may leave some seats unallocated,
     * which are then distributed to the second allocation phase.
     *
     * @param quota The vote quota required for a party to secure one seat. This is calculated based
     *              on the total number of votes and the number of seats available.
     * @param seatsLeft The number of seats that remained. This value is updated as seats
     *                  are distributed to parties during the allocation process.
     * @return The number of seats allocated. This value can be used to determine how many
     *         seats remain for the next allocation
     */

    public int firstAlloc(int quota, int seatsLeft) {
        HashMap<String, Integer> partySeats = new HashMap<>(); // To track allocated seats for ties resolution
        List<String> ties = new ArrayList<>();
        int seatsGiven = 0;

        // Allocate initial seats based on quota
        for (String party : parties.keySet()) {
            Party partyObj = parties.get(party);
            int partyVotes = partyObj.getPartyVotes();

            int allocatedSeats = partyVotes / quota;
            partyObj.setFirstAllocation(allocatedSeats);
            partyObj.setRemainder(partyVotes % quota);

            seatsGiven += allocatedSeats;
            partySeats.put(party, allocatedSeats); // Track seats for tie resolution

            // Add multiple entries for tie resolution if necessary
            for (int i = 0; i < allocatedSeats; i++) {
                ties.add(party);
            }
        }

        // Reduce seats if over-allocated
        while (seatsGiven > seatNumbers) {
            List<String> tiedParties = pickRandom(new ArrayList<>(new HashSet<>(ties)), seatsGiven - seatNumbers);

            for (String party : tiedParties) {
                Integer currentSeats = partySeats.get(party);
                if (currentSeats != null && currentSeats > 0) {
                    partySeats.put(party, currentSeats - 1); // Decrement seat count
                    parties.get(party).setFirstAllocation(currentSeats - 1); // Update party allocation
                    seatsGiven--;
                    System.out.println(seatsGiven);
                    System.out.println(seatNumbers);
                    ties.remove(party); // Remove from ties list once decremented
                }
            }
        }
        return seatsGiven;
    }
    /**
     * Performs the second allocation of seats based on the largest remainder method. This method is
     * called after the first seat allocation has been completed, and there are still seats left to
     * be allocated. It distributes the remaining seats to parties based on their vote remainders,
     * ensuring that parties with larger remainders are prioritized for the additional seats.
     * In cases where multiple parties have the same largest remainder, a tie-breaking mechanism is
     * going to decide which party receives the next seat. This tie-breaking process continues until
     * all ties are resolved and all remaining seats have been allocated.
     *
     * @param quota Even though this quota is not going to be used in the second allocation,
     *               it's still being passed in case there's a need for extended calculations
     *              or adjustments.
     * @param seatsLeft The number of seats that remain unallocated after the first allocation.
     *                  This value is decremented, as seats are allocated, during the execution.
     * @return The number of seats left after attempting to distribute all remaining seats.
     *         Ideally, this should be 0, indicating that all seats have been successfully allocated.
     */
    public int secondAlloc(int quota, int seatsLeft){
        ArrayList<String> ties = new ArrayList<>();

        //while there are still seats left to be allocate, use the largest remainder to set the second allocation.
        while (seatsLeft > 0) {
            int largestRemainder = -1;
            String largestPartyR = null;
            ties.clear();
            for (String party : parties.keySet()) {
                Party partyObj = parties.get(party);
                if (partyObj.getRemainder() > largestRemainder && partyObj.getSecondAllocation()!=1) {
                    largestRemainder = partyObj.getRemainder();
                    largestPartyR = party;
                    ties.clear();
                }
                else if (partyObj.getRemainder() == largestRemainder && partyObj.getSecondAllocation()!=1) {
                    ties.add(partyObj.getPartyName());
                }
            }

            //if there is a tie, randomly choose a party to give the second allocation.
            while (!ties.isEmpty()){
                pickRandom(ties, 1);
                ties.clear();
            }
            parties.get(largestPartyR).setSecondAllocation();
            seatsLeft--;
        }
        return seatsLeft;
    }

    /**
     * Allocates seats to parties and candidates based on the votes each has received.
     * This method works with the seat allocation process both in the first and second
     * allocation phases, and initilize the tie-breaking procedures if needed.
     */
    public void allocateSeats() {

        //set up variables, and use firstalloc and secondalloc to give the seats.
        int quota = numBallots/seatNumbers;
        int seatsLeft = seatNumbers;
        int seatsGiven = firstAlloc(quota, seatsLeft);
        seatsLeft = seatNumbers - seatsGiven;
        seatsGiven = secondAlloc(quota, seatsLeft);
        for (String party : parties.keySet()) {//set total seat allocation

            Party partyObj = parties.get(party);
            partyObj.setAllocatedSeats(partyObj.getSecondAllocation() + partyObj.getFirstAllocation());
        }
        giveSeats();
    }

    /**
     * Abstract method for adding a party to the election, to be implemented by subclasses.
     *
     * @param party The party to add.
     */
    public abstract void addParty(Party party);

    /**
     * Abstract method for adding a candidate to the election, to be implemented by subclasses.
     *
     * @param candidate The candidate to add.
     */
    public abstract void addCandidate(Candidate candidate);

    // Getters and setters
    /**
     * Get the total number of seats available in the election.
     *
     * @return The total number of seats.
     */
    public int getSeatNumbers() {
        return this.seatNumbers;
    }
    /**
     * Sets the total number of seats available in the election.
     *
     * @param seatNumbers The total number of seats to be set.
     */
    public void setSeatNumbers(int seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    /**
     * Get the current order of parties and candidates.
     *
     * @return The order list.
     */
    public ArrayList<String> getOrder() {
        return order;
    }

    /**
     * Retrieves the total number of ballots received in the election.
     *
     * @return The number of ballots.
     */
    public int getNumBallots() {
        return this.numBallots;
    }
    /**
     * Sets the total number of ballots received in the election.
     *
     * @param numBallots The number of ballots to be set.
     */
    public void setNumBallots(int numBallots) {
        this.numBallots = numBallots;
    }
    /**
     * Get a dictionary of all parties participating in the election.
     *
     * @return The parties dictionary.
     */
    public HashMap<String, Party> getPartyDictionary() {//TODO: remove duplicate
        return parties;
    }
    /**
     * Get the total number of parties participating in the election.
     *
     * @return The number of parties.
     */
    public int getNumParties() {return parties.size();}
    /**
     * Get a dictionary of all candidates participating in the election.
     *
     * @return The candidates dictionary.
     */
    public HashMap<String, Candidate> getCandidatesDictionary() {
        return candidates;
    }
    /**
     * Gives a seat to a candidate.
     *
     */
    public abstract void giveSeats();
    /**
     * Calculates the percentage of total seats of the party that won.
     *
     * @param party The party to calculate the seat percentage.
     * @return A string representing the percentage of seats won by the party.
     */
    public String getSeatPercentage(Party party){
        int partySeats = party.getAllocatedSeats();
        int totalSeats = seatNumbers;
        float percentage = 100*partySeats/totalSeats;
        return Float.toString(percentage) + "%";
    }
    /**
     * Calculates the percentage of total votes that a given party received.
     *
     * @param party The party to calculate the vote percentage.
     * @return A string representing the percentage of votes received by the party.
     */
    public String getVotePercentage(Party party){
        int partyVotes = party.getPartyVotes();
        int totalVotes = numBallots;
        float percentage = 100*partyVotes/totalVotes;
        return Float.toString(percentage) + "%";
    }

}
