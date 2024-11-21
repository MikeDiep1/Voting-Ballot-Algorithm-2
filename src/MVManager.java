    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Scanner;

    /**
     * Manages the Mulniciple voting (MV) process. This class is responsible for parsing election data
     * specific to MPO voting systems, including parsing parties and candidates from a CSV file, handling ballot data,
     * and constructing the voting system with the parsed data.
     *
     * Note: this is a dummy class we created to be able to take MV files, it doesn't do anything
     *  @author Bao Ha, Riandy Setiady, Vivian Tsang, Michael Diep
     *  @version 1.0
     * @since 1.0
     */
    public class MVManager extends Manager{

        /**
         * Parses a line from the CSV file to extract party and candidate information.
         * It splits the line into candidates and then extracts the candidate's name and affiliated party to
         * create {@link MPOParty} and {@link MPOCandidate} objects as needed.
         *
         * @param br The scanner to read the CSV file.
         * @param vote The voting system instance where the parsed party and candidates will be added.
         * @return Always returns null as this implementation does not need to return the party directly.
         */
        @Override
        public MPOParty parseParty(Scanner br, Voting vote){
            return null;
        }
        /**
         * Parses ballot data from the CSV file, updating vote counts for parties and candidates.
         *
         * @param br The scanner to read the CSV file.
         * @param num_ballots The number of ballots to process.
         * @param vote_length The number of options per ballot.
         * @param vote The voting system where the ballot data will be processed.
         * @throws IOException If an I/O error occurs while reading the file.
         */
        @Override
        public void parseBallots(Scanner br, int num_ballots, int vote_length, Voting vote) throws IOException {
        }
        /**
         * Parses the CSV file to construct the MPO voting system.
         *
         * @param file The scanner to read the CSV file.
         * @return The constructed Voting system based on the parsed data.
         * @throws IOException If an I/O error occurs while reading the file.
         */
        @Override
        public Voting parseCSV(Scanner file) throws IOException {//abstract
            return null;
        }

        /**
         * Creates a new {@link Voting} instance to represent the voting system for MPO.
         *
         * @return A new instance of {@link Voting}.
         */
        @Override
        public Voting makeVote() {
            return null;
        }
        /**
         * Creates a new {@link Party} with the specified party name.
         *
         * @param partyName The name of the party to be created.
         * @return A new instance of {@link Party} with the specified name.
         */
        @Override
        public Party makeParty(String partyName) {
            return null;
        }
        /**
         * Creates a new {@link Candidate} with the specified name and party affiliation.
         *
         * @param candidateName The name of the candidate to be created.
         * @param partyAffiliation The party the candidate is affiliated with.
         * @return A new instance of {@link Candidate} with the specified name and party affiliation.
         */

        // test
        @Override
        public Candidate makeCandidate(String candidateName, String partyAffiliation) {
            return null;
        }
    }