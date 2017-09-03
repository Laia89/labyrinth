import labyrinth.*;

import java.util.Arrays;
import java.util.List;

public class LabyrinthTest {

	public static void main(String[] args) {

		testMovePlayer();
		testMovePlayerMultipleTimes();
		testEscape();

		System.out.println("Tests OK!");
	}

	public static void testMovePlayer() {

		List<String> map = Arrays.asList(
			"@@@@@@@",
			"@@ @@  ",
			"@@ ·  @",
			"@@@@@@@"
		);

		LabyrinthReader labyrinthReader = new LabyrinthReader();
		Game game = labyrinthReader.startGame(map);
		game.movePlayer(Direction.RIGHT);

		Position playerPosition = game.getPlayerPosition();

		TestUtil.assertEquals(playerPosition.getX(), 4);
		TestUtil.assertEquals(playerPosition.getY(), 2);
		TestUtil.assertEquals(game.isPlayerOutsideOfMap(), false);
	}

	public static void testMovePlayerMultipleTimes() {

		List<String> map = Arrays.asList(
			"@ @@@@@",
			"@ @@ @@",
			"@    @@",
			"@@ @@ @",
			"@@  · @",
			"@@@@@@@"
		);

		// Note: we can inline the LabyrinthReader variable
		LabyrinthReader labyrinthReader = new LabyrinthReader();
		Game game = labyrinthReader.startGame(map);

		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.UP);
		game.movePlayer(Direction.UP);
		game.movePlayer(Direction.UP);
		game.movePlayer(Direction.RIGHT);

		Position playerPosition = game.getPlayerPosition();
		TestUtil.assertEquals(playerPosition.getX(), 3);
		TestUtil.assertEquals(playerPosition.getY(), 2);
		TestUtil.assertEquals(game.isPlayerOutsideOfMap(), false);
	}

	public static void testEscape() {

		List<String> map = Arrays.asList(
			"@@@@@@@",
			"@@ @@·@",
			"@@    @",
			"@@ @@@@"
		);

		LabyrinthReader labyrinthReader = new LabyrinthReader();
		Game game = labyrinthReader.startGame(map);

		game.movePlayer(Direction.DOWN);
		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.LEFT);
		game.movePlayer(Direction.DOWN);
		game.movePlayer(Direction.DOWN);

		Position playerPosition = game.getPlayerPosition();
		TestUtil.assertEquals(playerPosition.getX(), 2);
		TestUtil.assertEquals(playerPosition.getY(), 4);
		TestUtil.assertEquals(game.isPlayerOutsideOfMap(), true);
	}

	public static void testNoExit() {

		List<String> map = Arrays.asList(
				"@@@@@@@",
				"@@ @@ @",
				"@@ ·  @",
				"@@@@@@@"
		);

		LabyrinthReader labyrinthReader = new LabyrinthReader();

		Boolean labyrinthHasExit = labyrinthReader.labyrinthHasExit(map);

		TestUtil.assertEquals(labyrinthHasExit, false);
	}

	/**
     * Simple test util functions.
     */
    public static class TestUtil {

        /**
         * Checks that the actual value we have is equal to the one we expect.
         * In case they are different, throws an exception.
         */
        public static void assertEquals(Object actual, Object expected) {

            if (!actual.equals(expected)) {
                throw new RuntimeException("Actual value " + actual + " not equals to expected " + expected);
            }
        }
    }
}
