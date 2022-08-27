package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.models.Game;
import src.main.models.Team;

public class GameTest {
    Game game;

    @Before
    public void beforeTest() {
        Team home = new Team("GRYFFINDOR", "Oliver", "Harry",
                new String[] { "Angelina", "Ginny", "Katie" });
        Team away = new Team("SLYTHERIN", "Vincent", "Draco",
                new String[] { "Bridget", "Harper", "Malcolm" });
        game = new Game(home, away);
    }

    @Test
    public void getPlaceholderTest() {
        assertEquals("chaser", game.getPlaceholder("<chaser> is now efweffefe"));
    }

    @Test
    public void replacePlaceholderTest() {
        assertEquals("Katie gets the next pass", game.replacePlaceholder("<chaser> gets the next pass", "Katie", "<chaser>"));
    }

    @Test
    public void quaffleScoreTest() {
    
        Team team = game.getTeam("GRYFFINDOR");

        game.quaffleScore(team);
        game.quaffleScore(team);
        assertEquals(20, game.getScore(team));
    }

    @Test
    public void catchSnitchTest() {
        Team team = game.getTeam("SLYTHERIN");
        game.catchSnitch(team);
        assertEquals(150, game.getScore(team));
    }

}
