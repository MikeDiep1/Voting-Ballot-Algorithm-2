# repo-Team14
(Riandy Setiadi, Vivian Tsang, Michael Diep, and Bao Ha)

Dependencies for running unit tests:

1. junit-4.13.2

Download by running this command in the src directory: 
wget https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar

2. hamcrest-core-1.3

Download by running this command in the src directory: 
wget https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

How to run:
1. Compile by running this command: javac -cp .:junit-4.13.2.jar *.java
2. Run using this command from the terminal: java Main file1.csv file2.csv

where file1.csv and file2.csv are names of files in the testing directory. >= 1 file must be entered to work properly. If there are multiple files, they must have the same information for the header except for the line where the number of ballots is specified. 

3. To run the unit tests, execute this command from the terminal:
 java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore UnitTestsAgile
