// import static org.junit.Assert.*;
// import org.junit.Test;
//
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Scanner;
//
// import static org.junit.Assert.*;
// import org.junit.Test;
//
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.Scanner;
// import org.junit.Before;
// import org.junit.Test;
// import java.io.ByteArrayOutputStream;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.PrintStream;
// import java.nio.charset.StandardCharsets;
// import static org.junit.Assert.assertTrue;
//
// public class UnitTests {
//     @Test
//     public void getNumSeatsTest() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         OPLManager man = new OPLManager();
//         int expect = 2;
//         int actual = man.getNumSeats(filescanner);
//         assertEquals("Number of seats did not match", expect, actual);
//     }
//
//     @Test
//     public void getNumBallotsTest() throws FileNotFoundException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         filescanner.nextLine();
//
//         OPLManager man = new OPLManager();
//         int expect = 9;
//         int actual = man.getNumBallots(filescanner);
//         assertEquals("Number of ballots did not match", expect, actual);
//     }
//
//     @Test
//     public void getNumObjsTest() throws FileNotFoundException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         filescanner.nextLine();
//
//         String line = filescanner.nextLine();
//         OPLManager man = new OPLManager();
//         int expect = 6;
//         int actual = man.getNumObjs(filescanner);
//         assertEquals("Number of objects did not match", expect, actual);
//     }
//     @Test
//     public void parsePartyTestMPO() throws FileNotFoundException {
//         Scanner filescanner = new Scanner(new File("../testing/MPO_test.txt"));
//         for (int i = 0; i < 4; i++) {
//             filescanner.nextLine();
//         }
//         OPLManager man = new MPOManager();
//         OpenPartyList vote = new MultiplePopularityOnly();
//         OPLParty opl = man.parseParty(filescanner, vote);
//         OPLParty expect = new MPOParty();
//         expect.setPartyName("Democrat");
//         expect.addCandidate("Pike");
//
//         OPLParty actual = opl;
//         assertEquals("Party wrong", expect.getPartyName(), actual.getPartyName());
//         assertEquals("Candidate list wrong", expect.getCandidateList(), actual.getCandidateList());
//
//     }
//
//     @Test
//     public void parsePartyTestOPL() throws FileNotFoundException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         for (int i = 0; i < 4; i++) {
//             filescanner.nextLine();
//         }
//         OPLManager man = new OPLManager();
//         OpenPartyList vote = new OpenPartyList();
//         OPLParty opl = man.parseParty(filescanner, vote);
//         OPLParty expect = new OPLParty();
//         expect.setPartyName("Democrat");
//         expect.addCandidate("Pike");
//
//         OPLParty actual = opl;
//         assertEquals("Party wrong", expect.getPartyName(), actual.getPartyName());
//         assertEquals("Candidate list wrong", expect.getCandidateList(), actual.getCandidateList());
//
//     }
//
//     @Test
//     public void parsePartyTestOPL2() throws FileNotFoundException {//new
//         String line = "Democrat, Pike";
//         String[] elements;
//         String partyName = "";
//         OPLParty party = null;
//         elements = line.split(",");
//         partyName = elements[0].trim();
//         Voting vote = new OpenPartyList();
//         Manager man = new OPLManager();
//         if (vote.getPartyDictionary().get(partyName) != null) {
//             party = (OPLParty) vote.getPartyDictionary().get(partyName);
//         } else
//             party = (OPLParty) man.makeParty(partyName);
//
//         assertEquals("Party wrong", "Democrat", party.getPartyName());
//
//     }
//
//     @Test
//     public void parsePartyTestOPL3() throws FileNotFoundException {//new
//         String line = "Democrat, Pike";
//         String[] elements;
//         String partyName = "";
//         OPLParty party = null;
//         elements = line.split(",");
//         partyName = elements[0].trim();
//         Voting vote = new OpenPartyList();
//         Manager man = new OPLManager();
//         if (vote.getPartyDictionary().get(partyName) != null) {
//             party = (OPLParty) vote.getPartyDictionary().get(partyName);
//         } else
//             party = (OPLParty) man.makeParty(partyName);
//         String c_name;
//         for (int i = 1; i < elements.length; i++) {
//             c_name = elements[i].trim();
//             OPLCandidate candidate = (OPLCandidate) man.makeCandidate(c_name, party.getPartyName());
//             party.addCandidate(candidate.getCandidateName());
//             vote.addCandidate(candidate);
//         }
//         assertEquals("Wrong number of candidates", 1, vote.getCandidatesDictionary().size());
//
//     }
//
//     @Test
//     public void parseBallotsTestOPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         OPLManager man = new OPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//
//         for (int i = 0; i < num_objs; i++) {
//             OPLParty p = man.parseParty(filescanner, vote);
//             vote.addParty(p);
//         }
//         man.parseBallots(filescanner, num_ballots, num_objs, vote);
//
//         assertEquals("Party votes incorrect", 3, vote.getPartyDictionary().get("Democrat").getPartyVotes());
//         assertEquals("Party votes incorrect", 4, vote.getPartyDictionary().get("Republican").getPartyVotes());
//         assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Independent1").getPartyVotes());
//
//         assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Pike").getCandidateVotes());
//         assertEquals("Candidate votes incorrect", 1, vote.getCandidatesDictionary().get("Lucy").getCandidateVotes());
//         assertEquals("Candidate votes incorrect", 0, vote.getCandidatesDictionary().get("Beiye").getCandidateVotes());
//         assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Etta").getCandidateVotes());
//         assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Alawa").getCandidateVotes());
//         assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Sasha").getCandidateVotes());
//     }
//
//     @Test
//     public void parseBallotsTestOPL2() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         OPLManager man = new OPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//             assertNotNull(line);
//         }
//     }
//
//     @Test
//     public void parseBallotsTestOPL3() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         OPLManager man = new OPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//         int vote_length = num_objs;
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//
//             for (int j = 0; j < vote_length; j++) {
//                 assertNotNull(line.charAt(j));
//             }
//         }
//     }
//
//     @Test
//     public void parseBallotsTestOPL4() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         OPLManager man = new OPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//         int vote_length = num_objs;
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         boolean one = false;
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//             one = false;
//
//             for (int j = 0; j < vote_length; j++) {
//                 if (line.charAt(j) == '1') {
//                     one = true;
//                     assertTrue(one);
//
//                 }
//             }
//         }
//     }
//     @Test
//     public void makeVoteTestMPO() {
//         Manager man = new MPOManager();
//         Voting test = man.makeVote();
//
//         assertNotEquals("Vote object not created", null, test);
//     }
//
//     @Test
//     public void makePartyTestMPO() {
//         Manager man = new MPOManager();
//         MPOParty p = new MPOParty();
//         p.setPartyName("qwerty");
//         MPOParty test = (MPOParty) man.makeParty("qwerty");
//
//         assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
//     }
//
//     @Test
//     public void makeCandidateTestMPO() {
//         Manager man = new MPOManager();
//         MPOCandidate c = new MPOCandidate();
//         c.setName("cname");
//         c.setParty("pname");
//         MPOCandidate test = (MPOCandidate) man.makeCandidate("cname", "pname");
//
//         assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
//         assertEquals("Party is wrong", c.getParty(), test.getParty());
//     }
//
//     @Test
//     public void parseCSVTestMPO() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/MPO_test.txt"));
//         filescanner.nextLine();
//         Manager man = new MPOManager();
//         Voting test = man.parseCSV(filescanner);
//         assertEquals("Num ballots is wrong", 9, test.getNumBallots());
//         assertEquals("Seat numbers is wrong", 3, test.getSeatNumbers());
//         assertEquals("Num candidates is wrong", 6, test.getCandidatesDictionary().size());
//         assertEquals("Num parties is wrong", 3, test.getNumParties());
//
//     }
//
//     @Test
//     public void makeVoteTestOPL() {
//         Manager man = new OPLManager();
//         Voting test = man.makeVote();
//
//         assertNotEquals("Vote object not created", null, test);
//     }
//
//     @Test
//     public void makePartyTestOPL() {
//         Manager man = new OPLManager();
//         OPLParty p = new OPLParty();
//         p.setPartyName("qwerty");
//         OPLParty test = (OPLParty) man.makeParty("qwerty");
//
//         assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
//     }
//
//     @Test
//     public void makeCandidateTestOPL() {
//         Manager man = new OPLManager();
//         OPLCandidate c = new OPLCandidate();
//         c.setName("cname");
//         c.setParty("pname");
//         OPLCandidate test = (OPLCandidate) man.makeCandidate("cname", "pname");
//
//         assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
//         assertEquals("Party is wrong", c.getParty(), test.getParty());
//     }
//
//     @Test
//     public void parseCSVTestOPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting test = man.parseCSV(filescanner);
//         assertEquals("Num ballots is wrong", 9, test.getNumBallots());
//         assertEquals("Seat numbers is wrong", 2, test.getSeatNumbers());
//         assertEquals("Num candidates is wrong", 6, test.getCandidatesDictionary().size());
//         assertEquals("Num parties is wrong", 3, test.getNumParties());
//
//     }
//
//     @Test
//     public void parseCSVTest2OPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting test = man.parseCSV(filescanner);
//
//         assertEquals("Num parties is wrong", 3, test.getNumParties());
//         assertNotNull(test.getPartyDictionary().get("Democrat"));
//         assertNotNull(test.getPartyDictionary().get("Republican"));
//         assertNotNull(test.getPartyDictionary().get("Independent1"));
//
//
//     }
//
//     @Test
//     public void parseCSVTestOPL3() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting vote = man.makeVote();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//
//         int num_objs = man.getNumObjs(filescanner);
//         for (int i = 0; i < num_objs; i++) {
//             OPLParty p = (OPLParty) man.parseParty(filescanner, vote);
//             vote.addParty(p);//abstract method again
//
//         }
//         assertTrue(vote.getPartyDictionary().size() == 3);
//
//
//     }
//
//     @Test
//     public void parsePartyTestCPL() throws FileNotFoundException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         for (int i = 0; i < 4; i++) {
//             filescanner.nextLine();
//         }
//         CPLManager man = new CPLManager();
//         ClosedPartyList vote = new ClosedPartyList();
//         CPLParty cpl = man.parseParty(filescanner, vote);
//         CPLParty expect = new CPLParty();
//         expect.setPartyName("Democratic");
//         expect.addCandidate("Joe");
//         expect.addCandidate("Sally");
//         expect.addCandidate("Ahmed");
//
//
//         CPLParty actual = cpl;
//         assertEquals("Party wrong", expect.getPartyName(), actual.getPartyName());
//         assertEquals("Candidate list wrong", expect.getCandidateList(), actual.getCandidateList());
//
//     }
//
//     @Test
//     public void parsePartyTestCPL2() throws FileNotFoundException {//new
//         String line = "Democrat, Pike, Etta, Alawa";
//         String[] elements;
//         String partyName = "";
//         CPLParty party = null;
//         elements = line.split(",");
//         partyName = elements[0].trim();
//         Voting vote = new ClosedPartyList();
//         Manager man = new CPLManager();
//         if (vote.getPartyDictionary().get(partyName) != null) {
//             party = (CPLParty) vote.getPartyDictionary().get(partyName);
//         } else
//             party = (CPLParty) man.makeParty(partyName);
//
//         assertEquals("Party wrong", "Democrat", party.getPartyName());
//
//     }
//
//     @Test
//     public void parsePartyTestCPL3() throws FileNotFoundException {//new
//         String line = "Democrat, Pike, Etta, Alawa";
//         String[] elements;
//         String partyName = "";
//         CPLParty party = null;
//         elements = line.split(",");
//         partyName = elements[0].trim();
//         Voting vote = new ClosedPartyList();
//         Manager man = new CPLManager();
//         if (vote.getPartyDictionary().get(partyName) != null) {
//             party = (CPLParty) vote.getPartyDictionary().get(partyName);
//         } else
//             party = (CPLParty) man.makeParty(partyName);
//         String c_name;
//         for (int i = 1; i < elements.length; i++) {
//             c_name = elements[i].trim();
//             CPLCandidate candidate = (CPLCandidate) man.makeCandidate(c_name, party.getPartyName());
//             party.addCandidate(candidate.getCandidateName());
//             vote.addCandidate(candidate);
//         }
//         assertEquals("Wrong number of candidates", 3, vote.getCandidatesDictionary().size());
//
//     }
//
//     @Test
//     public void parseBallotsTestCPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         CPLManager man = new CPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//
//         for (int i = 0; i < num_objs; i++) {
//             CPLParty p = man.parseParty(filescanner, vote);
//             vote.addParty(p);
//         }
//         man.parseBallots(filescanner, num_ballots, num_objs, vote);
//
//         assertEquals("Party votes incorrect", 3, vote.getPartyDictionary().get("Democratic").getPartyVotes());
//         assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Republican").getPartyVotes());
//         assertEquals("Party votes incorrect", 0, vote.getPartyDictionary().get("New Wave").getPartyVotes());
//         assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Reform").getPartyVotes());
//         assertEquals("Party votes incorrect", 1, vote.getPartyDictionary().get("Green").getPartyVotes());
//         assertEquals("Party votes incorrect", 1, vote.getPartyDictionary().get("Independent").getPartyVotes());
//
//     }
//
//     @Test
//     public void parseBallotsTestCPL2() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         CPLManager man = new CPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//             assertNotNull(line);
//         }
//     }
//
//     @Test
//     public void parseBallotsTestCPL3() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         CPLManager man = new CPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//         int vote_length = num_objs;
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//
//             for (int j = 0; j < vote_length; j++) {
//                 assertNotNull(line.charAt(j));
//             }
//         }
//     }
//
//     @Test
//     public void parseBallotsTestCPL4() throws FileNotFoundException {
//
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         OPLManager man = new OPLManager();
//         Voting vote = man.makeVote();
//         filescanner.nextLine();
//         int num_seats = man.getNumSeats(filescanner);
//         int num_ballots = man.getNumBallots(filescanner);
//         int num_objs = man.getNumObjs(filescanner);
//         int vote_length = num_objs;
//         vote.setSeatNumbers(num_seats);
//         vote.setNumBallots(num_ballots);
//         String line;
//         String[] elements;
//
//         boolean one = false;
//         ArrayList<String> order = vote.order;//candidate order
//         for (int i = 0; i < num_ballots; i++) {
//             line = filescanner.nextLine();
//             one = false;
//
//             for (int j = 0; j < vote_length; j++) {
//                 if (line.charAt(j) == '1') {
//                     one = true;
//                     assertTrue(one);
//
//                 }
//             }
//         }
//     }
//
//     @Test
//     public void makeVoteTestCPL() {
//         Manager man = new CPLManager();
//         Voting test = man.makeVote();
//
//         assertNotEquals("Vote object not created", null, test);
//     }
//
//     @Test
//     public void makePartyTestCPL() {
//         Manager man = new CPLManager();
//         CPLParty p = new CPLParty();
//         p.setPartyName("qwerty");
//         CPLParty test = (CPLParty) man.makeParty("qwerty");
//
//         assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
//     }
//
//     @Test
//     public void makeCandidateTestCPL() {
//         Manager man = new CPLManager();
//         CPLCandidate c = new CPLCandidate();
//         c.setName("cname");
//         c.setParty("pname");
//         CPLCandidate test = (CPLCandidate) man.makeCandidate("cname", "pname");
//
//         assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
//         assertEquals("Party is wrong", c.getParty(), test.getParty());
//     }
//
//     @Test
//     public void parseCSVTestCPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting test = man.parseCSV(filescanner);
//         assertEquals("Num ballots is wrong", 9, test.getNumBallots());
//         assertEquals("Seat numbers is wrong", 3, test.getSeatNumbers());
//         assertEquals("Num parties is wrong", 6, test.getNumParties());
//
//     }
//
//     @Test
//     public void parseCSVTest2CPL() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting test = man.parseCSV(filescanner);
//
//         assertEquals("Num parties is wrong", 6, test.getNumParties());
//         assertNotNull(test.getPartyDictionary().get("Democratic"));
//         assertNotNull(test.getPartyDictionary().get("Republican"));
//         assertNotNull(test.getPartyDictionary().get("Independent"));
//         assertNotNull(test.getPartyDictionary().get("New Wave"));
//         assertNotNull(test.getPartyDictionary().get("Reform"));
//         assertNotNull(test.getPartyDictionary().get("Green"));
//     }
//     @Test
//     public void getSeatPercentageTest() {
//         OpenPartyList nVoting = new OpenPartyList();
//         OPLParty nParty = new OPLParty();
//
//         nParty.setAllocatedSeats(2);
//         nVoting.setSeatNumbers(10);
//         String result = nVoting.getSeatPercentage(nParty);
//
//         assertEquals("20.0%", result);
//     }
//
//     @Test
//     public void getVotePercentageTest() {
//         OpenPartyList nVoting = new OpenPartyList();
//         OPLParty nParty = new OPLParty();
//
//         nParty.addVote();
//         nVoting.setNumBallots(10);
//         String result = nVoting.getVotePercentage(nParty);
//
//         assertEquals("10.0%", result);
//     }
//
//     @Test
//     public void OPLgiveSeatsRoutineTest() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//
//         assertTrue(nVoting.getCandidatesDictionary().get("Pike").getGotSeat());     //Tie between Alawa and Etta
//         assertTrue((nVoting.getCandidatesDictionary().get("Etta").getGotSeat() && !nVoting.getCandidatesDictionary().get("Alawa").getGotSeat()
//                 || (nVoting.getCandidatesDictionary().get("Alawa").getGotSeat() && !nVoting.getCandidatesDictionary().get("Etta").getGotSeat())));
//         assertFalse(nVoting.getCandidatesDictionary().get("Sasha").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Beiye").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Lucy").getGotSeat());
//     }
//
//     @Test
//     public void OPLaddPartyTest() {
//         OpenPartyList nVoting = new OpenPartyList();
//
//         OPLParty party1 = new OPLParty();
//         party1.setPartyName("party1");
//         OPLParty party2 = new OPLParty();
//         party2.setPartyName("party1"); // should not add same name party
//         OPLParty party3 = new OPLParty();
//         party3.setPartyName("party3");
//
//         nVoting.addParty(party1);
//         nVoting.addParty(party2);
//         nVoting.addParty(party3);
//
//         assertEquals(2, nVoting.parties.size());
//     }
//
//     @Test
//     public void OPLaddCandidateTest() {
//         OpenPartyList nVoting = new OpenPartyList();
//
//         OPLParty party1 = new OPLParty();
//         party1.setPartyName("party1");
//         nVoting.addParty(party1);
//
//         OPLCandidate firstCan = new OPLCandidate();
//         firstCan.setName("cand1");
//         nVoting.addCandidate(firstCan);
//
//         OPLCandidate secCan = new OPLCandidate(); //shouldn't add same name candidate
//         secCan.setName("cand1");
//         nVoting.addCandidate(secCan);
//
//         assertEquals(1, nVoting.candidates.size());
//         assertEquals(1, nVoting.order.size());
//     }
//
//     @Test
//     public void CPLgiveSeatsRoutineTest() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//
//         assertTrue(nVoting.getCandidatesDictionary().get("Joe").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Sally").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Ahmed").getGotSeat());
//         assertTrue(nVoting.getCandidatesDictionary().get("Allen").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Nikki").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Taihui").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Sarah").getGotSeat());
//         assertTrue(nVoting.getCandidatesDictionary().get("Xinyue").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Nikita").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Bethany").getGotSeat());
//         assertFalse(nVoting.getCandidatesDictionary().get("Mike").getGotSeat());
//     }
//
//     @Test
//     public void CPLaddPartyTest() {
//         ClosedPartyList nVoting = new ClosedPartyList();
//
//         CPLParty party1 = new CPLParty();
//         party1.setPartyName("party1");
//         CPLParty party2 = new CPLParty();
//         party2.setPartyName("party1"); //should not add same name party
//         CPLParty party3 = new CPLParty();
//         party3.setPartyName("party3");
//
//         nVoting.addParty(party1);
//         nVoting.addParty(party2);
//         nVoting.addParty(party3);
//
//         assertEquals(2, nVoting.parties.size());
//         assertEquals(2, nVoting.order.size());
//     }
//
//     @Test
//     public void CPLaddCandidateTest() {
//         ClosedPartyList nVoting = new ClosedPartyList();
//
//         CPLParty party1 = new CPLParty();
//         party1.setPartyName("party1");
//         nVoting.addParty(party1);
//
//         CPLCandidate firstCan = new CPLCandidate();
//         firstCan.setName("cand1");
//         nVoting.addCandidate(firstCan);
//
//         CPLCandidate secCan = new CPLCandidate(); //shouldn't add same name candidate
//         secCan.setName("cand1");
//         nVoting.addCandidate(secCan);
//
//         assertEquals(1, nVoting.candidates.size());
//
//     }
//     @Test
//     public void testBreakTieTest() {
//         Voting nVoting = new OpenPartyList();
//
//         String result = nVoting.breakTie("A", "B");
//         assertTrue(result.equals("A") || result.equals("B"));
//         assertNotEquals(("C"), result);
//     }
//
//     @Test
//     // Will have a bug, because allocateSeats() calls giveSeats() at the end, and that will look for candidates
//     public void allocateSeatsRoutineTest() {
//         Voting nVoting = new OpenPartyList();
//         nVoting.setNumBallots(10);
//         nVoting.setSeatNumbers(3);
//
//         Party party1 = new OPLParty();
//         party1.setPartyName("party1");
//         OPLParty party2 = new OPLParty();
//         party2.setPartyName("party2");
//         OPLParty party3 = new OPLParty();
//         party3.setPartyName("party3");
//
//         nVoting.addParty(party1);
//         nVoting.addParty(party2);
//         nVoting.addParty(party3);
//         for (int i = 0; i < 6; i++) {
//             party1.addVote();
//         }
//         for (int i = 0; i < 3; i++) {
//             party2.addVote();
//         }
//         for (int i = 0; i < 1; i++) {
//             party3.addVote();
//         }
//
//         nVoting.allocateSeats();
//         assertEquals(2, party1.getAllocatedSeats());
//         assertEquals(1, party2.getAllocatedSeats());
//         assertEquals(0, party3.getAllocatedSeats());
//     }
//
//     @Test
// //     Democratic and republican tie the quota. Will have tie breaker to see who will get 2 seats and who will get 1 seat
// //     No remainders, because all three parties match the quota.
// //     Dem. should get 1-2, Rep. should get 1-2, and Green should get 0-1, when only 4 seats are available
//     public void firstAllocRoutineTest() throws IOException {
//         Scanner filescanner = new Scanner(new File("../testing/CPL_testing_tiebreaker1.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         int seatsGiven = nVoting.firstAlloc(2, 4);
//
//         assertTrue((nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2)
//                 || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 1 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2
//                 || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 1);     //Tie between Alawa and Etta
//         assertTrue(nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 0
//                 || nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 1);
//         assertEquals(4, seatsGiven);
//     }
//
//     @Test
//     public void secondAllocRoutineTest() throws IOException { //first alloc. Reform matched quota and got 1 of two seats
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.firstAlloc(3, 2);   //second alloc tests New Wave, Repub. and Dem. remainder
//         nVoting.secondAlloc(3, 1);  //Repub. largest remainder = 2, will get seat
//
//         assertEquals(1, nVoting.getPartyDictionary().get("Republican").getSecondAllocation());
//         assertNotEquals(1, nVoting.getPartyDictionary().get("Democratic").getSecondAllocation());
//         assertNotEquals(1, nVoting.getPartyDictionary().get("New Wave").getSecondAllocation());
//     }
//     @Test
//     public void forLoopAllocateSeatsTest() throws IOException { //first alloc. Reform matched quota, got 1 of two seats
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//         int iterations = 0;
//         int partyGot = 0;
//         for(String party : nVoting.parties.keySet()) {
//             iterations++;
//             Party partyObj = nVoting.parties.get(party);
//             if (partyObj.getAllocatedSeats() != 0) {
//                 partyGot++;
//             }
//         }
//         assertEquals(4, iterations); //for loop iterates all 4 parties
//         assertEquals(2, partyGot); //body of for loop sets allocated seats
//     }
//     @Test
//     public void forLoopFirstAllocTest() throws IOException { //Checks first for loop iteration and body containing conditionals
//         Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.firstAlloc(3, 2);
//         int iterations = 0;
//         int partyGot = 0;
//         for(String party : nVoting.parties.keySet()) {
//             iterations++;
//             Party partyObj = nVoting.parties.get(party);
//
//             if (partyObj.getFirstAllocation() != 0) {
//                 partyGot++;
//             }
//         }
//         assertEquals(4, iterations); //for loop iterates through 4 parties
//         assertEquals(1, partyGot); //checks if statement in body of for loop to see that the Reform party votes larger than quota, so reform first allocation is set
//         assertEquals(2, nVoting.getPartyDictionary().get("Republican").getRemainder()); //body of for loop for if the party votes is less than quota, set remainder to number of votes
//     }
//     @Test
//     public void whileLoopFirstALlocTest() throws IOException { //Checks the first for loop iteration and body containing conditionals
//         Scanner filescanner = new Scanner(new File("../testing/CPL_testing_tiebreaker1.txt"));
//         filescanner.nextLine();
//         Manager man = new CPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.firstAlloc(2, 4);
//         int onePartyTie = 0;
//         for(String party : nVoting.parties.keySet()) {
//             Party partyObj = nVoting.parties.get(party);
//             if (partyObj.getPartyVotes() == 4) { //Dem. and Repub. have 4 votes, call tiebreaker see who gets 2nd first allocation seat
//                 onePartyTie++;
//             }
//         }
//         assertEquals(2, onePartyTie); //checks how many first allocation party ties, between Democratic and Republican
//         assertTrue((nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2)
//                 || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 1 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2
//                 || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 1);     //Tie between Alawa and Etta
//         assertTrue(nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 0
//                 || nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 1);
//     }
//     @Test
//     public void whileSecondALlocTest() throws IOException { //Checks first while loop iteration and body containing conditionals
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.firstAlloc(4, 2);
//         nVoting.secondAlloc(4, 1);
//         int whileIteration = 0;
//         int forIteration = 0;
//         int seatsBeganWith = 2;
//         for(String party : nVoting.parties.keySet()) {
//             Party partyObj = nVoting.parties.get(party);
//             forIteration++;
//             if (partyObj.getFirstAllocation() != 0) { // checks first while loop iteration, is 1, because only one seat left
//                 seatsBeganWith -= partyObj.getFirstAllocation();
//             }
//         }
//         whileIteration = seatsBeganWith;
//
//         assertEquals(1, whileIteration); //checks while loop iterations
//         assertEquals(3, forIteration); //checks for loop iterations
//         assertEquals(1, nVoting.getPartyDictionary().get("Democrat").getSecondAllocation()); //conditionals in while in for loop evaluate Democrat as largest remainder
//         assertEquals(0, nVoting.getPartyDictionary().get("Republican").getSecondAllocation()); // conditionals add second allocation to Democrat,not Republican/Independent
//         assertEquals(0, nVoting.getPartyDictionary().get("Independent1").getSecondAllocation());
//     }
//     @Test
//     public void forOPLGiveSeatsTest() throws IOException { //Checks number of iterations for first loop
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//         int forIteration = 0;
//         for(String party : nVoting.parties.keySet()) {
//             Party partyObj = nVoting.parties.get(party);
//             forIteration++;
//         }
//         assertEquals(3, forIteration); //checks for loop iterations
//     }
//     @Test
//     public void whileOPLGiveSeatsTest() throws IOException { //Checks number of iterations for while loop inside for loop
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//
//         int whileIteration = nVoting.getPartyDictionary().get("Democrat").getAllocatedSeats();
//
//         assertEquals(1, whileIteration); //checks while loop iterations
//     }
//     @Test
//     public void for2OPLGiveSeatsTest() throws IOException { //Checks number of iterations for second for loop
//         Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//         int numPartyCand = nVoting.getPartyDictionary().get("Democrat").getCandidateList().size();
//
//         assertEquals(3, numPartyCand); //checks while loop iterations
//     }
//     @Test
//     public void conditionalOPLGiveSeatsTest() throws IOException { //Checks conditionals, because tie in the remainder between Republican and New Wave
//         Scanner filescanner = new Scanner(new File("../testing/OPL_testing_tiebreaker1.txt"));
//         filescanner.nextLine();
//         Manager man = new OPLManager();
//         Voting nVoting = man.parseCSV(filescanner);
//         nVoting.allocateSeats();
//         nVoting.giveSeats();
//
//         assertEquals(1, nVoting.getPartyDictionary().get("Democratic").getAllocatedSeats()); //Dem. largest remainder will definitely receive seat
//         assertTrue((nVoting.getPartyDictionary().get("New Wave").getAllocatedSeats() == 1 && nVoting.getPartyDictionary().get("Republican").getAllocatedSeats() == 0)
//                 || (nVoting.getPartyDictionary().get("New Wave").getAllocatedSeats() == 0 && nVoting.getPartyDictionary().get("Republican").getAllocatedSeats() == 1));
//         //Repub. and New Wave remainder tied, random tie breaker is called
//     }
// }
//
//
// //import static org.junit.Assert.*;
// //import org.junit.Test;
// //
// //import java.io.File;
// //import java.io.FileNotFoundException;
// //import java.io.IOException;
// //import java.util.ArrayList;
// //import java.util.Scanner;
// //
// //import static org.junit.Assert.*;
// //import org.junit.Test;
// //
// //import java.io.File;
// //import java.io.FileNotFoundException;
// //import java.io.IOException;
// //import java.util.Scanner;
// //import org.junit.Before;
// //import org.junit.Test;
// //import java.io.ByteArrayOutputStream;
// //import java.io.File;
// //import java.io.FileInputStream;
// //import java.io.PrintStream;
// //import java.nio.charset.StandardCharsets;
// //import static org.junit.Assert.assertTrue;
// //
// //public class UnitTests {
// //    @Test
// //    public void getNumSeatsTest() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        OPLManager man = new OPLManager();
// //        int expect = 2;
// //        int actual = man.getNumSeats(filescanner);
// //        assertEquals("Number of seats did not match", expect, actual);
// //    }
// //
// //    @Test
// //    public void getNumBallotsTest() throws FileNotFoundException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        filescanner.nextLine();
// //
// //        OPLManager man = new OPLManager();
// //        int expect = 9;
// //        int actual = man.getNumBallots(filescanner);
// //        assertEquals("Number of ballots did not match", expect, actual);
// //    }
// //
// //    @Test
// //    public void getNumObjsTest() throws FileNotFoundException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        filescanner.nextLine();
// //
// //        String line = filescanner.nextLine();
// //        OPLManager man = new OPLManager();
// //        int expect = 6;
// //        int actual = man.getNumObjs(filescanner);
// //        assertEquals("Number of objects did not match", expect, actual);
// //    }
// //
// //    @Test
// //    public void parsePartyTestOPL() throws FileNotFoundException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        for (int i = 0; i < 4; i++) {
// //            filescanner.nextLine();
// //        }
// //        OPLManager man = new OPLManager();
// //        OpenPartyList vote = new OpenPartyList();
// //        OPLParty opl = man.parseParty(filescanner, vote);
// //        OPLParty expect = new OPLParty();
// //        expect.setPartyName("Democrat");
// //        expect.addCandidate("Pike");
// //
// //        OPLParty actual = opl;
// //        assertEquals("Party wrong", expect.getPartyName(), actual.getPartyName());
// //        assertEquals("Candidate list wrong", expect.getCandidateList(), actual.getCandidateList());
// //
// //    }
// //
// //    @Test
// //    public void parsePartyTestOPL2() throws FileNotFoundException {//new
// //        String line = "Democrat, Pike";
// //        String[] elements;
// //        String partyName = "";
// //        OPLParty party = null;
// //        elements = line.split(",");
// //        partyName = elements[0].trim();
// //        Voting vote = new OpenPartyList();
// //        Manager man = new OPLManager();
// //        if (vote.getPartyDictionary().get(partyName) != null) {
// //            party = (OPLParty) vote.getPartyDictionary().get(partyName);
// //        } else
// //            party = (OPLParty) man.makeParty(partyName);
// //
// //        assertEquals("Party wrong", "Democrat", party.getPartyName());
// //
// //    }
// //
// //    @Test
// //    public void parsePartyTestOPL3() throws FileNotFoundException {//new
// //        String line = "Democrat, Pike";
// //        String[] elements;
// //        String partyName = "";
// //        OPLParty party = null;
// //        elements = line.split(",");
// //        partyName = elements[0].trim();
// //        Voting vote = new OpenPartyList();
// //        Manager man = new OPLManager();
// //        if (vote.getPartyDictionary().get(partyName) != null) {
// //            party = (OPLParty) vote.getPartyDictionary().get(partyName);
// //        } else
// //            party = (OPLParty) man.makeParty(partyName);
// //        String c_name;
// //        for (int i = 1; i < elements.length; i++) {
// //            c_name = elements[i].trim();
// //            OPLCandidate candidate = (OPLCandidate) man.makeCandidate(c_name, party.getPartyName());
// //            party.addCandidate(candidate.getCandidateName());
// //            vote.addCandidate(candidate);
// //        }
// //        assertEquals("Wrong number of candidates", 1, vote.getCandidatesDictionary().size());
// //
// //    }
// //
// //    @Test
// //    public void parseBallotsTestOPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        OPLManager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //
// //        for (int i = 0; i < num_objs; i++) {
// //            OPLParty p = man.parseParty(filescanner, vote);
// //            vote.addParty(p);
// //        }
// //        man.parseBallots(filescanner, num_ballots, num_objs, vote);
// //
// //        assertEquals("Party votes incorrect", 3, vote.getPartyDictionary().get("Democrat").getPartyVotes());
// //        assertEquals("Party votes incorrect", 4, vote.getPartyDictionary().get("Republican").getPartyVotes());
// //        assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Independent1").getPartyVotes());
// //
// //        assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Pike").getCandidateVotes());
// //        assertEquals("Candidate votes incorrect", 1, vote.getCandidatesDictionary().get("Lucy").getCandidateVotes());
// //        assertEquals("Candidate votes incorrect", 0, vote.getCandidatesDictionary().get("Beiye").getCandidateVotes());
// //        assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Etta").getCandidateVotes());
// //        assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Alawa").getCandidateVotes());
// //        assertEquals("Candidate votes incorrect", 2, vote.getCandidatesDictionary().get("Sasha").getCandidateVotes());
// //    }
// //
// //    @Test
// //    public void parseBallotsTestOPL2() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        OPLManager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //            assertNotNull(line);
// //        }
// //    }
// //
// //    @Test
// //    public void parseBallotsTestOPL3() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        OPLManager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //        int vote_length = num_objs;
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //
// //            for (int j = 0; j < vote_length; j++) {
// //                assertNotNull(line.charAt(j));
// //            }
// //        }
// //    }
// //
// //    @Test
// //    public void parseBallotsTestOPL4() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        OPLManager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //        int vote_length = num_objs;
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        boolean one = false;
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //            one = false;
// //
// //            for (int j = 0; j < vote_length; j++) {
// //                if (line.charAt(j) == '1') {
// //                    one = true;
// //                    assertTrue(one);
// //
// //                }
// //            }
// //        }
// //    }
// //
// //    @Test
// //    public void makeVoteTestOPL() {
// //        Manager man = new OPLManager();
// //        Voting test = man.makeVote();
// //
// //        assertNotEquals("Vote object not created", null, test);
// //    }
// //
// //    @Test
// //    public void makePartyTestOPL() {
// //        Manager man = new OPLManager();
// //        OPLParty p = new OPLParty();
// //        p.setPartyName("qwerty");
// //        OPLParty test = (OPLParty) man.makeParty("qwerty");
// //
// //        assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
// //    }
// //
// //    @Test
// //    public void makeCandidateTestOPL() {
// //        Manager man = new OPLManager();
// //        OPLCandidate c = new OPLCandidate();
// //        c.setName("cname");
// //        c.setParty("pname");
// //        OPLCandidate test = (OPLCandidate) man.makeCandidate("cname", "pname");
// //
// //        assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
// //        assertEquals("Party is wrong", c.getParty(), test.getParty());
// //    }
// //
// //    @Test
// //    public void parseCSVTestOPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting test = man.parseCSV(filescanner);
// //        assertEquals("Num ballots is wrong", 9, test.getNumBallots());
// //        assertEquals("Seat numbers is wrong", 2, test.getSeatNumbers());
// //        assertEquals("Num candidates is wrong", 6, test.getCandidatesDictionary().size());
// //        assertEquals("Num parties is wrong", 3, test.getNumParties());
// //
// //    }
// //
// //    @Test
// //    public void parseCSVTest2OPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting test = man.parseCSV(filescanner);
// //
// //        assertEquals("Num parties is wrong", 3, test.getNumParties());
// //        assertNotNull(test.getPartyDictionary().get("Democrat"));
// //        assertNotNull(test.getPartyDictionary().get("Republican"));
// //        assertNotNull(test.getPartyDictionary().get("Independent1"));
// //
// //
// //    }
// //
// //    @Test
// //    public void parseCSVTestOPL3() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //
// //        int num_objs = man.getNumObjs(filescanner);
// //        for (int i = 0; i < num_objs; i++) {
// //            OPLParty p = (OPLParty) man.parseParty(filescanner, vote);
// //            vote.addParty(p);//abstract method again
// //
// //        }
// //        assertTrue(vote.getPartyDictionary().size() == 3);
// //
// //
// //    }
// //
// //    @Test
// //    public void parsePartyTestCPL() throws FileNotFoundException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        for (int i = 0; i < 4; i++) {
// //            filescanner.nextLine();
// //        }
// //        CPLManager man = new CPLManager();
// //        ClosedPartyList vote = new ClosedPartyList();
// //        CPLParty cpl = man.parseParty(filescanner, vote);
// //        CPLParty expect = new CPLParty();
// //        expect.setPartyName("Democratic");
// //        expect.addCandidate("Joe");
// //        expect.addCandidate("Sally");
// //        expect.addCandidate("Ahmed");
// //
// //
// //        CPLParty actual = cpl;
// //        assertEquals("Party wrong", expect.getPartyName(), actual.getPartyName());
// //        assertEquals("Candidate list wrong", expect.getCandidateList(), actual.getCandidateList());
// //
// //    }
// //
// //    @Test
// //    public void parsePartyTestCPL2() throws FileNotFoundException {//new
// //        String line = "Democrat, Pike, Etta, Alawa";
// //        String[] elements;
// //        String partyName = "";
// //        CPLParty party = null;
// //        elements = line.split(",");
// //        partyName = elements[0].trim();
// //        Voting vote = new ClosedPartyList();
// //        Manager man = new CPLManager();
// //        if (vote.getPartyDictionary().get(partyName) != null) {
// //            party = (CPLParty) vote.getPartyDictionary().get(partyName);
// //        } else
// //            party = (CPLParty) man.makeParty(partyName);
// //
// //        assertEquals("Party wrong", "Democrat", party.getPartyName());
// //
// //    }
// //
// //    @Test
// //    public void parsePartyTestCPL3() throws FileNotFoundException {//new
// //        String line = "Democrat, Pike, Etta, Alawa";
// //        String[] elements;
// //        String partyName = "";
// //        CPLParty party = null;
// //        elements = line.split(",");
// //        partyName = elements[0].trim();
// //        Voting vote = new ClosedPartyList();
// //        Manager man = new CPLManager();
// //        if (vote.getPartyDictionary().get(partyName) != null) {
// //            party = (CPLParty) vote.getPartyDictionary().get(partyName);
// //        } else
// //            party = (CPLParty) man.makeParty(partyName);
// //        String c_name;
// //        for (int i = 1; i < elements.length; i++) {
// //            c_name = elements[i].trim();
// //            CPLCandidate candidate = (CPLCandidate) man.makeCandidate(c_name, party.getPartyName());
// //            party.addCandidate(candidate.getCandidateName());
// //            vote.addCandidate(candidate);
// //        }
// //        assertEquals("Wrong number of candidates", 3, vote.getCandidatesDictionary().size());
// //
// //    }
// //
// //    @Test
// //    public void parseBallotsTestCPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        CPLManager man = new CPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //
// //        for (int i = 0; i < num_objs; i++) {
// //            CPLParty p = man.parseParty(filescanner, vote);
// //            vote.addParty(p);
// //        }
// //        man.parseBallots(filescanner, num_ballots, num_objs, vote);
// //
// //        assertEquals("Party votes incorrect", 3, vote.getPartyDictionary().get("Democratic").getPartyVotes());
// //        assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Republican").getPartyVotes());
// //        assertEquals("Party votes incorrect", 0, vote.getPartyDictionary().get("New Wave").getPartyVotes());
// //        assertEquals("Party votes incorrect", 2, vote.getPartyDictionary().get("Reform").getPartyVotes());
// //        assertEquals("Party votes incorrect", 1, vote.getPartyDictionary().get("Green").getPartyVotes());
// //        assertEquals("Party votes incorrect", 1, vote.getPartyDictionary().get("Independent").getPartyVotes());
// //
// //    }
// //
// //    @Test
// //    public void parseBallotsTestCPL2() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        CPLManager man = new CPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //            assertNotNull(line);
// //        }
// //    }
// //
// //    @Test
// //    public void parseBallotsTestCPL3() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        CPLManager man = new CPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //        int vote_length = num_objs;
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //
// //            for (int j = 0; j < vote_length; j++) {
// //                assertNotNull(line.charAt(j));
// //            }
// //        }
// //    }
// //
// //    @Test
// //    public void parseBallotsTestCPL4() throws FileNotFoundException {
// //
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        OPLManager man = new OPLManager();
// //        Voting vote = man.makeVote();
// //        filescanner.nextLine();
// //        int num_seats = man.getNumSeats(filescanner);
// //        int num_ballots = man.getNumBallots(filescanner);
// //        int num_objs = man.getNumObjs(filescanner);
// //        int vote_length = num_objs;
// //        vote.setSeatNumbers(num_seats);
// //        vote.setNumBallots(num_ballots);
// //        String line;
// //        String[] elements;
// //
// //        boolean one = false;
// //        ArrayList<String> order = vote.order;//candidate order
// //        for (int i = 0; i < num_ballots; i++) {
// //            line = filescanner.nextLine();
// //            one = false;
// //
// //            for (int j = 0; j < vote_length; j++) {
// //                if (line.charAt(j) == '1') {
// //                    one = true;
// //                    assertTrue(one);
// //
// //                }
// //            }
// //        }
// //    }
// //
// //    @Test
// //    public void makeVoteTestCPL() {
// //        Manager man = new CPLManager();
// //        Voting test = man.makeVote();
// //
// //        assertNotEquals("Vote object not created", null, test);
// //    }
// //
// //    @Test
// //    public void makePartyTestCPL() {
// //        Manager man = new CPLManager();
// //        CPLParty p = new CPLParty();
// //        p.setPartyName("qwerty");
// //        CPLParty test = (CPLParty) man.makeParty("qwerty");
// //
// //        assertEquals("Party object not created", p.getPartyName(), test.getPartyName());
// //    }
// //
// //    @Test
// //    public void makeCandidateTestCPL() {
// //        Manager man = new CPLManager();
// //        CPLCandidate c = new CPLCandidate();
// //        c.setName("cname");
// //        c.setParty("pname");
// //        CPLCandidate test = (CPLCandidate) man.makeCandidate("cname", "pname");
// //
// //        assertEquals("Name is wrong", c.getCandidateName(), test.getCandidateName());
// //        assertEquals("Party is wrong", c.getParty(), test.getParty());
// //    }
// //
// //    @Test
// //    public void parseCSVTestCPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting test = man.parseCSV(filescanner);
// //        assertEquals("Num ballots is wrong", 9, test.getNumBallots());
// //        assertEquals("Seat numbers is wrong", 3, test.getSeatNumbers());
// //        assertEquals("Num parties is wrong", 6, test.getNumParties());
// //
// //    }
// //
// //    @Test
// //    public void parseCSVTest2CPL() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting test = man.parseCSV(filescanner);
// //
// //        assertEquals("Num parties is wrong", 6, test.getNumParties());
// //        assertNotNull(test.getPartyDictionary().get("Democratic"));
// //        assertNotNull(test.getPartyDictionary().get("Republican"));
// //        assertNotNull(test.getPartyDictionary().get("Independent"));
// //        assertNotNull(test.getPartyDictionary().get("New Wave"));
// //        assertNotNull(test.getPartyDictionary().get("Reform"));
// //        assertNotNull(test.getPartyDictionary().get("Green"));
// //    }
// //    @Test
// //    public void getSeatPercentageTest() {
// //        OpenPartyList nVoting = new OpenPartyList();
// //        OPLParty nParty = new OPLParty();
// //
// //        nParty.setAllocatedSeats(2);
// //        nVoting.setSeatNumbers(10);
// //        String result = nVoting.getSeatPercentage(nParty);
// //
// //        assertEquals("20.0%", result);
// //    }
// //
// //    @Test
// //    public void getVotePercentageTest() {
// //        OpenPartyList nVoting = new OpenPartyList();
// //        OPLParty nParty = new OPLParty();
// //
// //        nParty.addVote();
// //        nVoting.setNumBallots(10);
// //        String result = nVoting.getVotePercentage(nParty);
// //
// //        assertEquals("10.0%", result);
// //    }
// //
// //    @Test
// //    public void OPLgiveSeatsRoutineTest() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //
// //        assertTrue(nVoting.getCandidatesDictionary().get("Pike").getGotSeat());     //Tie between Alawa and Etta
// //        assertTrue((nVoting.getCandidatesDictionary().get("Etta").getGotSeat() && !nVoting.getCandidatesDictionary().get("Alawa").getGotSeat()
// //                || (nVoting.getCandidatesDictionary().get("Alawa").getGotSeat() && !nVoting.getCandidatesDictionary().get("Etta").getGotSeat())));
// //        assertFalse(nVoting.getCandidatesDictionary().get("Sasha").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Beiye").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Lucy").getGotSeat());
// //    }
// //
// //    @Test
// //    public void OPLaddPartyTest() {
// //        OpenPartyList nVoting = new OpenPartyList();
// //
// //        OPLParty party1 = new OPLParty();
// //        party1.setPartyName("party1");
// //        OPLParty party2 = new OPLParty();
// //        party2.setPartyName("party1"); // should not add same name party
// //        OPLParty party3 = new OPLParty();
// //        party3.setPartyName("party3");
// //
// //        nVoting.addParty(party1);
// //        nVoting.addParty(party2);
// //        nVoting.addParty(party3);
// //
// //        assertEquals(2, nVoting.parties.size());
// //    }
// //
// //    @Test
// //    public void OPLaddCandidateTest() {
// //        OpenPartyList nVoting = new OpenPartyList();
// //
// //        OPLParty party1 = new OPLParty();
// //        party1.setPartyName("party1");
// //        nVoting.addParty(party1);
// //
// //        OPLCandidate firstCan = new OPLCandidate();
// //        firstCan.setName("cand1");
// //        nVoting.addCandidate(firstCan);
// //
// //        OPLCandidate secCan = new OPLCandidate(); //shouldn't add same name candidate
// //        secCan.setName("cand1");
// //        nVoting.addCandidate(secCan);
// //
// //        assertEquals(1, nVoting.candidates.size());
// //        assertEquals(1, nVoting.order.size());
// //    }
// //
// //    @Test
// //    public void CPLgiveSeatsRoutineTest() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //
// //        assertTrue(nVoting.getCandidatesDictionary().get("Joe").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Sally").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Ahmed").getGotSeat());
// //        assertTrue(nVoting.getCandidatesDictionary().get("Allen").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Nikki").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Taihui").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Sarah").getGotSeat());
// //        assertTrue(nVoting.getCandidatesDictionary().get("Xinyue").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Nikita").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Bethany").getGotSeat());
// //        assertFalse(nVoting.getCandidatesDictionary().get("Mike").getGotSeat());
// //    }
// //
// //    @Test
// //    public void CPLaddPartyTest() {
// //        ClosedPartyList nVoting = new ClosedPartyList();
// //
// //        CPLParty party1 = new CPLParty();
// //        party1.setPartyName("party1");
// //        CPLParty party2 = new CPLParty();
// //        party2.setPartyName("party1"); //should not add same name party
// //        CPLParty party3 = new CPLParty();
// //        party3.setPartyName("party3");
// //
// //        nVoting.addParty(party1);
// //        nVoting.addParty(party2);
// //        nVoting.addParty(party3);
// //
// //        assertEquals(2, nVoting.parties.size());
// //        assertEquals(2, nVoting.order.size());
// //    }
// //
// //    @Test
// //    public void CPLaddCandidateTest() {
// //        ClosedPartyList nVoting = new ClosedPartyList();
// //
// //        CPLParty party1 = new CPLParty();
// //        party1.setPartyName("party1");
// //        nVoting.addParty(party1);
// //
// //        CPLCandidate firstCan = new CPLCandidate();
// //        firstCan.setName("cand1");
// //        nVoting.addCandidate(firstCan);
// //
// //        CPLCandidate secCan = new CPLCandidate(); //shouldn't add same name candidate
// //        secCan.setName("cand1");
// //        nVoting.addCandidate(secCan);
// //
// //        assertEquals(1, nVoting.candidates.size());
// //
// //    }
// //    @Test
// //    public void testBreakTieTest() {
// //        Voting nVoting = new OpenPartyList();
// //
// //        String result = nVoting.breakTie("A", "B");
// //        assertTrue(result.equals("A") || result.equals("B"));
// //        assertNotEquals(("C"), result);
// //    }
// //
// //    @Test
// //    // Will have a bug, because allocateSeats() calls giveSeats() at the end, and that will look for candidates
// //    public void allocateSeatsRoutineTest() {
// //        Voting nVoting = new OpenPartyList();
// //        nVoting.setNumBallots(10);
// //        nVoting.setSeatNumbers(3);
// //
// //        Party party1 = new OPLParty();
// //        party1.setPartyName("party1");
// //        OPLParty party2 = new OPLParty();
// //        party2.setPartyName("party2");
// //        OPLParty party3 = new OPLParty();
// //        party3.setPartyName("party3");
// //
// //        nVoting.addParty(party1);
// //        nVoting.addParty(party2);
// //        nVoting.addParty(party3);
// //        for (int i = 0; i < 6; i++) {
// //            party1.addVote();
// //        }
// //        for (int i = 0; i < 3; i++) {
// //            party2.addVote();
// //        }
// //        for (int i = 0; i < 1; i++) {
// //            party3.addVote();
// //        }
// //
// //        nVoting.allocateSeats();
// //        assertEquals(2, party1.getAllocatedSeats());
// //        assertEquals(1, party2.getAllocatedSeats());
// //        assertEquals(0, party3.getAllocatedSeats());
// //    }
// //
// //    @Test
// ////     Democratic and republican tie the quota. Will have tie breaker to see who will get 2 seats and who will get 1 seat
// ////     No remainders, because all three parties match the quota.
// ////     Dem. should get 1-2, Rep. should get 1-2, and Green should get 0-1, when only 4 seats are available
// //    public void firstAllocRoutineTest() throws IOException {
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_testing_tiebreaker1.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        int seatsGiven = nVoting.firstAlloc(2, 4);
// //
// //        assertTrue((nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2)
// //                || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 1 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2
// //                || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 1);     //Tie between Alawa and Etta
// //        assertTrue(nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 0
// //                || nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 1);
// //        assertEquals(4, seatsGiven);
// //    }
// //
// //    @Test
// //    public void secondAllocRoutineTest() throws IOException { //first alloc. Reform matched quota and got 1 of two seats
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.firstAlloc(3, 2);   //second alloc tests New Wave, Repub. and Dem. remainder
// //        nVoting.secondAlloc(3, 1);  //Repub. largest remainder = 2, will get seat
// //
// //        assertEquals(1, nVoting.getPartyDictionary().get("Republican").getSecondAllocation());
// //        assertNotEquals(1, nVoting.getPartyDictionary().get("Democratic").getSecondAllocation());
// //        assertNotEquals(1, nVoting.getPartyDictionary().get("New Wave").getSecondAllocation());
// //    }
// //    @Test
// //    public void forLoopAllocateSeatsTest() throws IOException { //first alloc. Reform matched quota, got 1 of two seats
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //        int iterations = 0;
// //        int partyGot = 0;
// //        for(String party : nVoting.parties.keySet()) {
// //            iterations++;
// //            Party partyObj = nVoting.parties.get(party);
// //            if (partyObj.getAllocatedSeats() != 0) {
// //                partyGot++;
// //            }
// //        }
// //        assertEquals(4, iterations); //for loop iterates all 4 parties
// //        assertEquals(2, partyGot); //body of for loop sets allocated seats
// //    }
// //    @Test
// //    public void forLoopFirstAllocTest() throws IOException { //Checks first for loop iteration and body containing conditionals
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_test4.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.firstAlloc(3, 2);
// //        int iterations = 0;
// //        int partyGot = 0;
// //        for(String party : nVoting.parties.keySet()) {
// //            iterations++;
// //            Party partyObj = nVoting.parties.get(party);
// //
// //            if (partyObj.getFirstAllocation() != 0) {
// //                partyGot++;
// //            }
// //        }
// //        assertEquals(4, iterations); //for loop iterates through 4 parties
// //        assertEquals(1, partyGot); //checks if statement in body of for loop to see that the Reform party votes larger than quota, so reform first allocation is set
// //        assertEquals(2, nVoting.getPartyDictionary().get("Republican").getRemainder()); //body of for loop for if the party votes is less than quota, set remainder to number of votes
// //    }
// //    @Test
// //    public void whileLoopFirstALlocTest() throws IOException { //Checks the first for loop iteration and body containing conditionals
// //        Scanner filescanner = new Scanner(new File("../testing/CPL_testing_tiebreaker1.txt"));
// //        filescanner.nextLine();
// //        Manager man = new CPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.firstAlloc(2, 4);
// //        int onePartyTie = 0;
// //        for(String party : nVoting.parties.keySet()) {
// //            Party partyObj = nVoting.parties.get(party);
// //            if (partyObj.getPartyVotes() == 4) { //Dem. and Repub. have 4 votes, call tiebreaker see who gets 2nd first allocation seat
// //                onePartyTie++;
// //            }
// //        }
// //        assertEquals(2, onePartyTie); //checks how many first allocation party ties, between Democratic and Republican
// //        assertTrue((nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2)
// //                || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 1 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 2
// //                || nVoting.getPartyDictionary().get("Democratic").getFirstAllocation() == 2 && nVoting.getPartyDictionary().get("Republican").getFirstAllocation() == 1);     //Tie between Alawa and Etta
// //        assertTrue(nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 0
// //                || nVoting.getPartyDictionary().get("Green").getFirstAllocation() == 1);
// //    }
// //    @Test
// //    public void whileSecondALlocTest() throws IOException { //Checks first while loop iteration and body containing conditionals
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.firstAlloc(4, 2);
// //        nVoting.secondAlloc(4, 1);
// //        int whileIteration = 0;
// //        int forIteration = 0;
// //        int seatsBeganWith = 2;
// //        for(String party : nVoting.parties.keySet()) {
// //            Party partyObj = nVoting.parties.get(party);
// //            forIteration++;
// //            if (partyObj.getFirstAllocation() != 0) { // checks first while loop iteration, is 1, because only one seat left
// //                seatsBeganWith -= partyObj.getFirstAllocation();
// //            }
// //        }
// //        whileIteration = seatsBeganWith;
// //
// //        assertEquals(1, whileIteration); //checks while loop iterations
// //        assertEquals(3, forIteration); //checks for loop iterations
// //        assertEquals(1, nVoting.getPartyDictionary().get("Democrat").getSecondAllocation()); //conditionals in while in for loop evaluate Democrat as largest remainder
// //        assertEquals(0, nVoting.getPartyDictionary().get("Republican").getSecondAllocation()); // conditionals add second allocation to Democrat,not Republican/Independent
// //        assertEquals(0, nVoting.getPartyDictionary().get("Independent1").getSecondAllocation());
// //    }
// //    @Test
// //    public void forOPLGiveSeatsTest() throws IOException { //Checks number of iterations for first loop
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //        int forIteration = 0;
// //        for(String party : nVoting.parties.keySet()) {
// //            Party partyObj = nVoting.parties.get(party);
// //            forIteration++;
// //        }
// //        assertEquals(3, forIteration); //checks for loop iterations
// //    }
// //    @Test
// //    public void whileOPLGiveSeatsTest() throws IOException { //Checks number of iterations for while loop inside for loop
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //
// //        int whileIteration = nVoting.getPartyDictionary().get("Democrat").getAllocatedSeats();
// //
// //        assertEquals(1, whileIteration); //checks while loop iterations
// //    }
// //    @Test
// //    public void for2OPLGiveSeatsTest() throws IOException { //Checks number of iterations for second for loop
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_test.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //        int numPartyCand = nVoting.getPartyDictionary().get("Democrat").getCandidateList().size();
// //
// //        assertEquals(3, numPartyCand); //checks while loop iterations
// //    }
// //    @Test
// //    public void conditionalOPLGiveSeatsTest() throws IOException { //Checks conditionals, because tie in the remainder between Republican and New Wave
// //        Scanner filescanner = new Scanner(new File("../testing/OPL_testing_tiebreaker1.txt"));
// //        filescanner.nextLine();
// //        Manager man = new OPLManager();
// //        Voting nVoting = man.parseCSV(filescanner);
// //        nVoting.allocateSeats();
// //        nVoting.giveSeats();
// //
// //        assertEquals(1, nVoting.getPartyDictionary().get("Democratic").getAllocatedSeats()); //Dem. largest remainder will definitely receive seat
// //        assertTrue((nVoting.getPartyDictionary().get("New Wave").getAllocatedSeats() == 1 && nVoting.getPartyDictionary().get("Republican").getAllocatedSeats() == 0)
// //                || (nVoting.getPartyDictionary().get("New Wave").getAllocatedSeats() == 0 && nVoting.getPartyDictionary().get("Republican").getAllocatedSeats() == 1));
// //        //Repub. and New Wave remainder tied, random tie breaker is called
// //    }
// //}
//
