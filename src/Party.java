//TODO: add function to set candidate order array
//TODO: add member variable for candidate order array (initialize it too)
//TODO: remove candidate hashmap and change to arraylist
//TODO: update sdd; we removed getcandidate from here
//TODO: update sdd; we changed the name of addpartyvote() to addVote()

import java.util.ArrayList;
/**
 * Represents a political party in an election, containing details of the party's name,
 * list of candidates, vote count, and allocated seats. This class manages the party's information
 * and operations across various types of election systems.
 * It is designed to be flexible with both Closed Party List (CPL) and Open Party List (OPL)
 * by maintaining a candidate list in order, relevant to each systems, and a vote count that
 * affects seat allocation.
 * @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public abstract class Party {
    private String partyName;
    private ArrayList<String> candidateList = new ArrayList<String>(); //array list of candidates in this particular party.in order for cpl, NOT for opl
    private int partyVotes = 0;
    private int allocatedSeats = 0;
    private int secondAllocation = 0;
    private int firstAllocation= 0 ;
    private int remainder= 0;

    /**
     * Gets the name of the party.
     *
     * @return The party's name.
     */
    public String getPartyName() {
        return partyName;
    }
    /**
     * Sets the name of the party.
     *
     * @param partyName The name to set for the party.
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    /**
     * Adds a candidate to the party's list of candidates.
     *
     * @param candidateName The name of the candidate to add.
     */
    public void addCandidate(String candidateName) {//add to arraylist
        candidateList.add(candidateName);
    }

    /**
     * Retrieves the list of candidates associated with the party.
     *
     * @return A list of candidate names.
     */
    public ArrayList<String> getCandidateList(){ return this.candidateList; }

    /**
     * Retrieves the total number of votes received by the party.
     *
     * @return The party's vote count.
     */
    public int getPartyVotes() {
        return partyVotes;
    }
    /**
     * Increments the party's seat
     */
    public void addSeat(){
        allocatedSeats ++;
    }
    /**
     * Sets the number of seats allocated
     *
     * @param seats The number of seats to be allocated.
     */
    public void setAllocatedSeats(int seats) {
        this.allocatedSeats = seats;
    }

    /**
     * Gets the number of seats allocated to this party.
     *
     * @return The number of allocated seats.
     */
    public int getAllocatedSeats() {
        return this.allocatedSeats;
    }

    /**
     * Sets the number of seats allocated to this party in the first allocation
     *
     * @param seats The number of seats allocated during the first allocation.
     */
    public void setFirstAllocation(int seats) {
        this.firstAllocation = seats;
    }

    /**
     * Gets the number of seats allocated to this party in the first allocation
     *
     * @return The number of seats allocated during the first allocation.
     */
    public int getFirstAllocation() {
        return this.firstAllocation;
    }

    /**
     * Sets a flag indicating that this party has received seats in the second allocation
     * Note: This method currently sets the second allocation to 1
     */
    public void setSecondAllocation() {
        this.secondAllocation = 1;
    }

    /**
     * Gets the status of seat allocation to this party in the second allocation.
     *
     * @return 1 if seats have been allocated during the second phase, otherwise 0.
     */
    public int getSecondAllocation() {
        return this.secondAllocation;
    }

    /**
     * Gets the remainder of votes for this party after the initial seat allocation.
     *
     * @return The number of remainder votes.
     */
    public int getRemainder() {
        return this.remainder;
    }

    /**
     * Sets the remainder of votes for this party after the initial seat allocation.
     *
     * @param remainder The number of remainder votes to set.
     */
    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    /**
     * Increments the party's vote count by one, indicating a new vote was received.
     */
    public void addVote() {
        partyVotes++;
    }

}