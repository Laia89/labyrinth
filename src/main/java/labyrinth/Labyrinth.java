package labyrinth;


import java.util.List;

public class Labyrinth {

	public static final char TILE_SPACE = ' ';

	private List<String> rows;

	/**
	 * Creates a Labyrinth from a list of strings.
	 * The {@link Labyrinth#TILE_SPACE} are ways where the player can move around.
	 * The map must be a rectangle (all map lines have the same length).
	 */
	public Labyrinth(List<String> rows) {
		this.rows = rows;
	}

	// Note: These are convenience methods to make it clear that we're getting the map size

	public int getMapHeight() {
		return rows.size();
	}

	public int getMapWidth() {
		List<String> rows = this.rows;
		return rows.get(0).length();
	}

	public List<String> getMapRows() {
		return rows;
	}
}
