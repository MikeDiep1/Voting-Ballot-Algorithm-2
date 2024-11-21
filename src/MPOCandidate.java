/**
 * Represents a candidate in the Multiple Popularity Only (MPO) voting system. This class extends the generic
 * {@link Candidate} class and is tailored for the specific requirements of MPO elections.
 *
 * In MPO, candidates can be tied with others based on the number of votes received,
 * requiring a tie-breaker to determine seat allocation. This class does not implement
 * additional logic beyond {@link Candidate} but serves as a specialized type to clarify the
 * usage within the MPO system.
 */
public class MPOCandidate extends Candidate{
}