package src.main.models;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINTS = 150;

    // Consts.
    public Game(Team home, Team away){
        scoreboard = new HashMap<Team, Integer>();
        scoreboard.put(new Team(home), 0);
        scoreboard.put(new Team(away), 0);
        gameCount++;
    }
    public Integer getScore(Team team) {
        return this.scoreboard.get(new Team(team));
    }

    public void setScore(Team team, Integer score) {
        if (team == null || score == null || score < 0) {
            throw new IllegalArgumentException();
        }
        scoreboard.put(team, score);
    }
    public static int getGameCount() {
        return gameCount;
    }

    public String getPlaceholder(String play) {
        return play.substring(play.indexOf("<") + 1, play.indexOf(">"));
    }

    public String replacePlaceholder(String placeholderText, String replacer, String toReplace) {
       
        return placeholderText.replace("<" + toReplace + ">", replacer);
        // if (placeholderText.contains("<chaser>") || placeholderText.contains("<keeper>") || placeholderText.contains("<seeker>")) {
        //     return placeholderText.replace("<chaser>", replacer);
        // }
    }

    public int catchSnitch(Team team) {
        return scoreboard.put(team, (scoreboard.get(team) + SNITCH_POINTS));
    }

    public int quaffleScore(Team team) {
        scoreboard.put(team, (scoreboard.get(team) + QUAFFLE_POINTS));
        return scoreboard.get(team);
    }

    public String simulate(String play) {
        // Retrives the placeholder from play
        // String palceholder = play.substring(play.indexOf("<" + 1), play.indexOf(">"));
        String placehold = play.substring(play.indexOf("<") + 1, play.indexOf(">"));
        // Get random team from 'hashmap'.
        Team myRandomTeam = getRandomTeam();

        if (placehold.equalsIgnoreCase(Team.getPositionChaser())) {
            quaffleScore(myRandomTeam);
            String ranadomChaser = myRandomTeam.getChasers()[randNum(myRandomTeam.getChasers().length)];
            return replacePlaceholder(play, ranadomChaser, Team.getPositionChaser());

        }else if(placehold.equalsIgnoreCase(Team.getPositionSeeker())){
            catchSnitch(myRandomTeam);
            return replacePlaceholder(play, myRandomTeam.getSeeker(), Team.getPositionSeeker());
        }else {
            return replacePlaceholder(play, myRandomTeam.getKeeper(), Team.getPositionKeeper());
        }
    }

    public int randNum(int range) {
        Random rand = new Random();
        return rand.nextInt(range);
    }

    public Team getRandomTeam() {

        Team thisTeam = (Team)scoreboard.keySet().toArray()[randNum(scoreboard.keySet().toArray().length)];
        return thisTeam;

        // ArrayList myRandomArrayList = new ArrayList<Team>();
        // for (int i = 0; i < scoreboard.size(); i++) {
        //     myRandomArrayList.add(scoreboard.keySet().toArray()[i]);
        // }
        // return (Team)myRandomArrayList.get(random(myRandomArrayList.size()));
    }

    public int random(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Team getTeam(String teamName) {
        // Filter using stream!
        // return this.scoreboard.keySet().stream().filter(currTeam ->
        // currTeam.getHouse().equalsIgnoreCase(teamName)).findFirst().orElse(null);
        
        for (Team key: scoreboard.keySet()) {
            if (key.getHouse().equalsIgnoreCase(teamName)) {
                return key;
            }
        }
        return null;

        
    }
    
}
