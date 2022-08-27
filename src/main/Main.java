package src.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import src.main.models.Game;
import src.main.models.Team;

public class Main {

        static Game game;
        static final String TEAMS_FILE = "src\\main\\teams.txt";
        static final String PLAYS_FILE = "src\\main\\plays.txt";

        public static void main(String[] args) throws FileNotFoundException, InterruptedException {

                Team teamGriffindor = creatTeam(0);
                Team teamSlyderin = creatTeam(1);

                Game game = new Game(teamGriffindor, teamSlyderin);

                startGame(game);
                printResult(game, teamGriffindor, teamSlyderin);
        }


        // Gets the data with the team inside
        public static String[][] getData() throws FileNotFoundException {
                int loopCounter = 0;
                File teamFile = new File(TEAMS_FILE);
                Scanner teamFileScanner = new Scanner(teamFile);

                // initalizing the String 2D array
                String[][] myStringArray = new String[2][6];

                // Scanning file
                while (teamFileScanner.hasNextLine()) {
                        String currentLine = teamFileScanner.nextLine();
                        String[] splittedLine = currentLine.split(",");
                        for (int i = 0; i < splittedLine.length; i++) {
                                myStringArray[loopCounter][i] = splittedLine[i];
                        }
                        // Add one to the loop counter to index the row in "myStringArray"
                        loopCounter++;
                }
                // Return the result
                return myStringArray;
        }

        // returns a Team object with the selected team from the txt file.
        private static Team creatTeam(int rowToChoose) throws FileNotFoundException {
                String[][] my2DArray = getData();
                String[] firstRow = my2DArray[rowToChoose];

                String currHouse = "";
                String currKeeper = "";
                String currSeeker = "";
                String[] currChasers = new String[3];

                for (int i = 0; i < firstRow.length; i++) {
                        switch (i) {
                                case 0:
                                        currHouse = firstRow[i];
                                        break;
                                case 1:
                                        currKeeper = firstRow[i];
                                        break;
                                case 2:
                                        currSeeker = firstRow[i];
                                        break;
                                case 3:
                                        currChasers[0] = firstRow[i];
                                        break;
                                case 4:
                                        currChasers[1] = firstRow[i];
                                        break;
                                case 5:
                                        currChasers[2] = firstRow[i];
                                        break;
                        }
                }
                return new Team(currHouse, currKeeper, currSeeker, currChasers);
        }

        public static void startGame(Game game) throws FileNotFoundException, InterruptedException {

                File playFile = new File("src\\main\\plays.txt");
                Scanner playScanner = new Scanner(playFile);

                while (playScanner.hasNextLine()) {
                        String currPlay = playScanner.nextLine();
                        if (!(playScanner.hasNextLine())) {
                                sleepMethod(2);
                        }
                        else{
                                sleepMethod(1);
                        }
                        System.out.println(game.simulate(currPlay));
                }
        }

        public static void printResult(Game game, Team griffindor, Team slyderin) {
                System.out.println("\n" + griffindor.getHouse() + "'s score: " + game.getScore(griffindor));
                System.out.println(slyderin.getHouse() + "'s score: " + game.getScore(slyderin));

                Team winnerTeam = game.getScore(griffindor) > game.getScore(slyderin) ? griffindor : slyderin;
                System.out.println("\nThe winner is: " + winnerTeam.getHouse() + "\n");
        }

        public static void sleepMethod(int timeSleep) throws InterruptedException{
                TimeUnit.SECONDS.sleep(timeSleep);
        }

}
