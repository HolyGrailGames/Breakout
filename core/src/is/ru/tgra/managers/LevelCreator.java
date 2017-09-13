package is.ru.tgra.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.objects.Block;
import is.ru.tgra.shapes.Box;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Vector2D;

public class LevelCreator {
	
	public static List<Block> getLevelOneBlocks() {
		List<Block> blocks = new ArrayList<Block>();
		
		float originX = ((Settings.windowWidth - Settings.SCOREBOARD_THICKNESS + Settings.WALL_THICKNESS - (Settings.LEVEL1_COLS * (Settings.BLOCK_WIDTH + Settings.BLOCK_SPACING)) - Settings.BLOCK_SPACING) / 2) + (Settings.BLOCK_WIDTH / 2);
		float originY = Settings.windowHeight - Settings.LEVEL1_ORIGIN_Y;
		for (int i = 0; i < Settings.LEVEL1_COLS; i++) {
			for(int j = 0; j < Settings.LEVEL1_ROWS; j++) {
				// Spawn new block, then lower the point of origin to the next row

				blocks.add(new Block(new Point2D(originX, originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.GOLDENROD, 5));
				originY -= Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT;

			}
			// Set origin to the next column and reset the height
			originX += Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH;
			originY = Settings.windowHeight - Settings.LEVEL1_ORIGIN_Y;
		}
		
		return blocks;
	}
	
	public static Box[] getLevelOneWalls() {
		Box[] walls = new Box[3];
		// Left wall
		walls[0] = new Box(new Point2D(Settings.WALL_THICKNESS/2, (Settings.windowHeight-Settings.WALL_THICKNESS)/2), 
						   new Vector2D(Settings.WALL_THICKNESS, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
		// Top wall
		walls[1] = new Box(new Point2D(Settings.windowWidth/2, Settings.windowHeight-Settings.WALL_THICKNESS/2), 
						   new Vector2D(Settings.windowWidth, Settings.WALL_THICKNESS), 0.0f, Settings.LIGHT_GREEN);
		// Right wall
		walls[2] = new Box(new Point2D(Settings.windowWidth - 125.0f, (Settings.windowHeight-Settings.WALL_THICKNESS)/2), 
						   new Vector2D(250.0f, Settings.windowHeight), 0.0f, Settings.LIGHT_GREEN);
		return walls;
	}
}
