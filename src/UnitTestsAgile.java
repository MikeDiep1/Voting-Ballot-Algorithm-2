import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;

public class UnitTestsAgile {
    @Test
    public void overwriteVotesTestOPL() throws IOException {
        String name = "overwriteVotesTestOPL.csv";
        int totalBallots = 99;
        String type = "OPL";

        List<String> lines = new ArrayList<>();
//   setup stuff. code copied from Election.java with modified output filename for testing purposes
        try (BufferedReader reader = new BufferedReader(new FileReader("../testing/" + name))) {
            String line = reader.readLine();
            int lineNumber = 0;
            int ballotInd = -1;
            // Read all lines from the file and modify the third line (index 2).
            if (type.equals("OPL") || type.equals(("CPL"))) {
                ballotInd = 2;
            } else if (type.equals("MPO") || type.equals(("MV"))) {
                ballotInd = 4;
            }
            while (line != null) {
                if (lineNumber == ballotInd) {
                    // This is the third line where we need to update the total number of ballots.
                    lines.add(String.valueOf(totalBallots));
                } else {
                    // For other lines, just add them to the list as they are.
                    lines.add(line);
                }
                lineNumber++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error reading from the file: " + name, e);
        }
        // Now write the updated content back to the file using the writer.
        // Ensure that we flush and close the writer after writing to make sure all changes are committed.
        String newFile = "../testing/overwriteVotesTestOPLResult.csv";

        BufferedWriter newWriter = new BufferedWriter(new FileWriter(newFile));
        for (String line : lines) {
            newWriter.write(line);
            newWriter.newLine();
        }
        newWriter.close();
        try (BufferedReader r2 = new BufferedReader(new FileReader(newFile))) {
            String[] expected = {"OPL", "1", "99", "1", "stuff"};
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }

        }
    }

    @Test
    public void overwriteVotesTestMPO() throws IOException {
        String name = "overwriteVotesTestMPO.csv";
        int totalBallots = 99;
        String type = "MPO";

        List<String> lines = new ArrayList<>();
//   setup stuff. code copied from Election.java with modified output filename for testing purposes
        try (BufferedReader reader = new BufferedReader(new FileReader("../testing/" + name))) {
            String line = reader.readLine();
            int lineNumber = 0;
            int ballotInd = -1;
            // Read all lines from the file and modify the third line (index 2).
            if (type.equals("OPL") || type.equals(("CPL"))) {
                ballotInd = 2;
            } else if (type.equals("MPO") || type.equals(("MV"))) {
                ballotInd = 4;
            }
            while (line != null) {
                if (lineNumber == ballotInd) {
                    // This is the third line where we need to update the total number of ballots.
                    lines.add(String.valueOf(totalBallots));
                } else {
                    // For other lines, just add them to the list as they are.
                    lines.add(line);
                }
                lineNumber++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error reading from the file: " + name, e);
        }
        // Now write the updated content back to the file using the writer.
        // Ensure that we flush and close the writer after writing to make sure all changes are committed.
        String newFile = "../testing/overwriteVotesTestMPOResult.csv";

        BufferedWriter newWriter = new BufferedWriter(new FileWriter(newFile));
        File t = new File(newFile);
        System.out.println(t.getAbsolutePath());
        for (String line : lines) {
            newWriter.write(line);
            newWriter.newLine();
        }
        newWriter.close();
        try (BufferedReader r2 = new BufferedReader(new FileReader(newFile))) {
            String[] expected = {"MPO", "1", "1", "1", "99"};
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }

        }
    }

    @Test
    public void copyHeadersAndReturnBallotsTestMPO() throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../testing/copyHeaderReturnBallotsResultMPO.csv"))) {
            Election e = new Election();
            int ballots = e.copyHeadersAndReturnBallots("../testing/copyHeaderReturnBallotMPO.csv", writer, "MPO");
            assertEquals(6, ballots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/copyHeaderReturnBallotsResultMPO.csv"))) {
            String[] expected = {"MPO", "2","4", "[Allen, D], [Baker, R], [Clark, D], [Dunn, R]",
                    "6", "1,,,"};
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void copyHeadersAndReturnBallotsTestOPL() throws FileNotFoundException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../testing/copyHeaderReturnBallotResultOPL.csv"))) {
            Election e = new Election();
            int ballots = e.copyHeadersAndReturnBallots("../testing/copyHeaderReturnBallotOPL.csv", writer, "OPL");
            assertEquals(9, ballots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/copyHeaderReturnBallotOPL.csv"))) {
            String[] expected = {"OPL", "2", "9", "6",
                    "Democrat, Pike", "1,,,,,"};
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void appendBallotsOnlyTestOPL() throws FileNotFoundException {
//Note: for this test, the file appendBallotsOnlyResultOPL.csv needs to be reset every time to:
//OPL
//2
//9
//6
//Democrat, Pike
//1,,,,,

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../testing/appendBallotsOnlyResultOPL.csv", true))) {
            Election e = new Election();
            int ballots = e.appendBallotsOnly("../testing/appendBallotsOnlyOPL.csv", writer, "OPL");
            assertEquals(7, ballots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/appendBallotsOnlyResultOPL.csv"))) {
            String[] expected = {"OPL", "2", "9", "6",
                    "Democrat, Pike", "1,,,,,", ",1,,,,"};
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void appendBallotsOnlyTestMPO() throws FileNotFoundException {
//Note: for this test, the file appendBallotsOnlyResultMPO.csv needs to be reset every time to:
//MPO
//2
//4
//[Allen, D], [Baker, R], [Clark, D], [Dunn, R]
//6
//1,,,
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../testing/appendBallotsOnlyResultMPO.csv", true))) {
            Election e = new Election();
            int ballots = e.appendBallotsOnly("../testing/appendBallotsOnlyMPO.csv", writer, "MPO");
            assertEquals(7, ballots);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/appendBallotsOnlyResultMPO.csv"))) {
            String[] expected = {
                    "MPO",
                    "2",
                    "4",
                    "[Allen, D], [Baker, R], [Clark, D], [Dunn, R]",
                    "6",
                    "1,,,",
                    ",1,,"
            };
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateTotalBallotsTestMPO() throws IOException {
//Note: for this test, the file updateTotalBallotsMPO.csv needs to be reset every time to:
//MPO
//0
//0
//[Allen, D], [Baker, R], [Clark, D], [Dunn, R]
//0
//1,,,
        Election election = new Election();
        election.updateTotalBallots("../testing/updateTotalBallotsMPO.csv", 100, "MPO");

        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/updateTotalBallotsMPO.csv"))) {
            String[] expected = {
                    "MPO",
                    "0",
                    "0",
                    "[Allen, D], [Baker, R], [Clark, D], [Dunn, R]",
                    "100",
                    "1,,,"
            };
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateTotalBallotsTestOPL() throws IOException {
//Note: for this test, the file appendBallotsOnlyResultOPL.csv needs to be reset every time to:
//OPL
//0
//0
//0
//Democrat, Pike
//1,,,,,

        Election election = new Election();
        election.updateTotalBallots("../testing/updateTotalBallotsOPL.csv", 100, "OPL");

        try (BufferedReader r2 = new BufferedReader(new FileReader("../testing/updateTotalBallotsOPL.csv"))) {
            String[] expected = {
                    "OPL",
                    "0",
                    "100",
                    "0",
                    "Democrat, Pike",
                    "1,,,,,"
            };
            String line = r2.readLine();
            int ind = 0;
            while (line != null) {
                assertEquals(expected[ind], line);
                ind += 1;
                line = r2.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void pickRandomTest(){
        List<String> fruits = new ArrayList<String>() {{
            add("apple");
            add("banana");
            add("mango");
            add("peach");
            add("pineapple");
            add("orange");

        }};
        Voting vote = new OpenPartyList();
        List<String> test = vote.pickRandom(fruits, 4);

        Set<String> set = new HashSet<>(test);
        assertEquals("All elements must be unique", test.size(), set.size());

        assertEquals("Element length must be 4", 4, test.size());

        assertTrue("All elements must come from the expected list", fruits.containsAll(test));
        for (String fruit : test){
            System.out.println(fruit);
        }
    }
    @Test
    public void makeVoteTestMPO() {
        Manager man = new MPOManager();
        Voting test = man.makeVote();

        assertNotEquals("Vote object not created", null, test);
    }

    @Test
    public void makePartyTestMPO() {
        Manager man = new MPOManager();
        MPOParty p = new MPOParty();
        p.setPartyName("qwerty");
        MPOParty test = (MPOParty) man.makeParty("qwerty");

        assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
    }

    @Test
    public void makeCandidateTestMPO() {
        Manager man = new MPOManager();
        MPOCandidate c = new MPOCandidate();
        c.setName("cname");
        c.setParty("pname");
        MPOCandidate test = (MPOCandidate) man.makeCandidate("cname", "pname");

        assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
        assertEquals("Party is wrong", c.getParty(), test.getParty());
    }

    @Test
    public void parsePartyTestMPO() throws FileNotFoundException {
        ArrayList<String> Alice = new ArrayList<>();
        Alice.add("Alice");
        Scanner filescanner = new Scanner(new File("../testing/MPO_test1.csv"));
        for (int i = 0; i < 3; i++) {
            filescanner.nextLine();
        }
        MPOManager man = new MPOManager();
        MultiplePopularityOnly vote = new MultiplePopularityOnly();
        MPOParty mpo = man.parseParty(filescanner, vote);
        MPOParty expect = new MPOParty();
        expect.setPartyName("D");
        expect.addCandidate("Alice");

        MPOParty actual = mpo;
        assertEquals("Party wrong", expect.getPartyName(), "D");
        assertEquals("Candidate list wrong", expect.getCandidateList(), Alice);
    }
    @Test
    public void parseCSVTestMPO() throws IOException {
        Scanner filescanner = new Scanner(new File("../testing/MPO_test1.csv"));
        filescanner.nextLine();
        Manager man = new MPOManager();
        Voting test = man.parseCSV(filescanner);
        assertEquals("Num ballots is wrong", 3, test.getNumBallots());
        assertEquals("Seat numbers is wrong", 1, test.getSeatNumbers());
        assertEquals("Num candidates is wrong", 2, test.getCandidatesDictionary().size());
        assertEquals("Num parties is wrong", 2, test.getNumParties());
    }
}

