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
	
	public static List<Block> getLevelTwoBlocks() {
		List<Block> blocks = new ArrayList<Block>();
		
		// Draw a super bad ass turtles character that 
		
		float originX = ((Settings.windowWidth - Settings.SCOREBOARD_THICKNESS + Settings.WALL_THICKNESS - (Settings.LEVEL1_COLS * (Settings.BLOCK_WIDTH + Settings.BLOCK_SPACING)) - Settings.BLOCK_SPACING) / 2) + (Settings.BLOCK_WIDTH / 2);
		float originY = Settings.windowHeight - Settings.LEVEL2_ORIGIN_Y;
		
		// Top row
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 2nd row
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 3rd row
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		
		// 4th row
		blocks.add(new Block(new Point2D(originX + 0 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 1 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));

		// 5th row
		blocks.add(new Block(new Point2D(originX + 1 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLACK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLACK, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		
		// 6th row
		blocks.add(new Block(new Point2D(originX + 0 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 1 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 7th row
		blocks.add(new Block(new Point2D(originX + 0 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 8th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 9th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 10th row
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.RED, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		// 11th row
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FOREST, 5));
		
		return blocks;
	}
	
	public static Box[] getLevelTwoWalls() {
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
	
	public static Box[] getLevelThreeWalls() {
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
	
	public static List<Block> getLevelThreeBlocks() {
		List<Block> blocks = new ArrayList<Block>();
		
		float originX = ((Settings.windowWidth - Settings.SCOREBOARD_THICKNESS + Settings.WALL_THICKNESS - (Settings.LEVEL1_COLS * (Settings.BLOCK_WIDTH + Settings.BLOCK_SPACING)) - Settings.BLOCK_SPACING) / 2) + (Settings.BLOCK_WIDTH / 2);
		float originY = Settings.windowHeight - Settings.LEVEL2_ORIGIN_Y;
		
		// Top row
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
	
		// 2nd row
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		// 3rd row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		// 4th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
	
		// 5th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.BLUE, 5));
		
		// 6th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.WHITE, 5));
		
		// 7th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
	
		// 8th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		// 9th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 3 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 6 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 8 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
	
		// 10th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 9 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		// 11th row

		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 10 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		// 12th row
		blocks.add(new Block(new Point2D(originX + 2 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 11 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 4 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 11 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 5 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 11 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		blocks.add(new Block(new Point2D(originX + 7 * (Settings.BLOCK_SPACING + Settings.BLOCK_WIDTH), originY - 11 * (Settings.BLOCK_SPACING + Settings.BLOCK_HEIGHT)), new Vector2D(Settings.BLOCK_WIDTH, Settings.BLOCK_HEIGHT), Color.FIREBRICK, 5));
		
		
		return blocks;
	}
}
