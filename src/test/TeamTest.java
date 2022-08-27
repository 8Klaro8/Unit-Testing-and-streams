package src.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.models.Team;

public class TeamTest {

    String[] chasers;
    
    @Before
    public void initialize() {
        chasers = new String[]{"efef", "fefe", " "};
    }

    @Test
    public void isNullTest() {
        assertTrue(Team.chasersIsNull(chasers));
    }

    @Test
    public void hasBlankTest() {
        assertTrue(Team.hasBlank(chasers));
    }

    
}
