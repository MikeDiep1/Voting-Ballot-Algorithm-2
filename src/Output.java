/**
 * An abstract class designed to define the structure for outputting election results.
 * This class contains common properties and methods that can be used by subclasses to
 * implement specific output formats for different types of elections.
 * It holds a reference to a {@link Voting} instance, which contains the data for
 * generating election results and audit files using the class's methods,
 * which must be implemented by subclasses for the specifics of each voting system.
 * <p>
 * </p>
 *
 * @author Bao Ha, Riandy Setiadi, Vivian Tsang, Michael Diep
 * @version 1.0
 * @since 1.0
 */
public abstract class Output {
    /**
     * Reference to a {@link Voting} instance containing the election data to be outputted.
     */
    Voting vote;
    /**
     * Abstract method intended to be override by subclasses, so they can initialize or update
     * the {@link Voting} instance associated with this output. This method
     *  ensures that the output class is always working with the current election data.
     *
     * @param vote The {@link Voting} instance containing the election data to be outputted.
     */
    public abstract void votingOutput(Voting vote);
    /**
     * Optionally, overridden method to display the results of the election. Subclasses should
     * provide a specific implementation to output the appropriate election type's results
     */
    public void displayResults() {
    }
    /**
     * Optionally, overridden method to generate an audit file of
     * the election. Subclasses should provide a specific implementation to create
     * audit files appropriate to the election type
     */
    public void makeAudit(){
    }
}
