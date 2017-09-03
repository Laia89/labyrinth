package labyrinth;

import java.util.List;

/**
 * Builds a Labyrinth from different sources.
 */
public class LabyrinthReader {

	// Note: `static final` is for constants in Java
	public static final char TILE_PLAYER = '·';
	public static final char TILE_WALL = '@';
	public static final char TILE_SPACE = ' ';

	/**
	 * Builds a Labyrinth from a list of strings.
	 * It must follow the constraints specified in {@link Labyrinth(List)} constructor.
	 * The map must also contain a special {@link #TILE_PLAYER} that represents
	 * the starting point of the player.
	 */
	public Game startGame (List<String> map) {
		if (labyrinthHasExit(map)) {
			Position startPosition = extractPlayerPosition(map);
			Labyrinth labyrinth = new Labyrinth(map);
			Game game = new Game(startPosition, labyrinth);
			return game;
		}

		throw new IllegalArgumentException("the map doesn't have an exit, that's not fair!");
	}

	/**
	 * Extracts the player position in the map.
	 * Removes the player from the map.
	 */
	private Position extractPlayerPosition(List<String> map) {

		for (int i = 0; i < map.size(); i++) {
			int index = map.get(i).indexOf(TILE_PLAYER);
			if (index >= 0) {
				Position startPosition = new Position(index, i);
				// Remove player from map
				String rowWithoutPlayer = map.get(i).replace(TILE_PLAYER, Labyrinth.TILE_SPACE);
				map.set(i, rowWithoutPlayer);
				return startPosition;
			}
		}

		throw new IllegalArgumentException("the map doesn't contain the player starting point");
	}

	/**
	 * The map has to have at least one exit.
	 *  Otherwise it should throw an exception saying "the map doesn't have an exit, that's not fair!".
	 *  We are going to check if there is at least one space on the walls.
	 *  And that after the "exit" is found there is another space inside the map and perpendicular to the exit.
	 * */
	public Boolean labyrinthHasExit(List<String> map) {
		for (int i = 0; i < map.size(); i++) {
			String rowLabyrinth = map.get(i);
			int indexSpaceAllColumns = rowLabyrinth.indexOf(TILE_SPACE);
			int indexSpaceLastColumn = rowLabyrinth.indexOf(TILE_SPACE, rowLabyrinth.length()-1);
			Boolean isUpOrDownWall = i == 0 || i == map.size()-1;
			int ahah = rowLabyrinth.length();
			if (isUpOrDownWall && indexSpaceAllColumns >=0) {
				return true;
			} else if (!isUpOrDownWall && (indexSpaceAllColumns == 0 || indexSpaceLastColumn == rowLabyrinth.length()-1)) {
				return true;
			}
		}

		return false;
	}
}
