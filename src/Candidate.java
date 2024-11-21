/**
 * Represents an abstract candidate in an election. This class stores information about a candidate, including name, party affiliation,
 * vote count, and seat allocation status. It is designed to be extended by specific types
 * of candidates, with additional properties or behaviors.
 *
 * @author Bao Ha, Riandy Setiadi, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public abstract class Candidate {
    /**
     * The name of the candidate.
     */
    private String name;

    /**
     * The name of the party the candidate is affiliated.
     */
    private String party;

    /**
     * The total number of votes received by the candidate.
     */
    private int candidateVotes;

    /**
     * Flag indicating whether the candidate has been allocated a seat.
     */
    private boolean gotSeat = false;

    /**
     * Sets the name of the candidate.
     *
     * @param name The name of the candidate. Cannot be {@code null} or empty.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the party associated with the candidate.
     *
     * @return The party name as a {@code String}.
     */
    public String getParty() {
        return party;
    }

    /**
     * Sets the party affiliation for the candidate.
     *
     * @param party The name of the party. Cannot be {@code null} or empty.
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * get the name of the candidate.
     *
     * @return The candidate's name as a {@code String}.
     */
    public String getCandidateName() {
        return name;
    }

    /**
     * Marks if the candidate has allocated a seat. This method will set the
     * {@code gotSeat} flag to {@code true}.
     */
    public void setGotSeat() {
        gotSeat = true;
    }

    /**
     * Checks if the candidate has been allocated a seat.
     *
     * @return {@code true} if the candidate has been allocated a seat, otherwise {@code false}.
     */
    public boolean getGotSeat() {
        return gotSeat;
    }

    /**
     * Get the total number of votes received by the candidate.
     *
     * @return The total vote count as an {@code int}.
     */
    public int getCandidateVotes() {
        return candidateVotes;
    }

    /**
     * Increments the vote count by one. This method should be called to
     * reflect any new vote allocated to this candidate.
     */
    public void addVote() {
        candidateVotes++;
    }

    /**
     * Provides a string of the candidate,
     * This method returns the party name of the candidate.
     *
     * @return A string of the candidate, which is the party's name.
     */
    @Override
    public String toString(){
        return getParty();
    }
}