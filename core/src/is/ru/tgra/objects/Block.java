package is.ru.tgra.objects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;

import is.ru.tgra.shapes.Box;
import is.ru.tgra.utils.Point2D;
import is.ru.tgra.utils.Settings;
import is.ru.tgra.utils.Utils;
import is.ru.tgra.utils.Vector2D;

public class Block {
	private Point2D position;
	private Vector2D scale;
	private Color color;
	private ArrayList<Box> subBoxes;
	private int subdivisions;
	private boolean exploded = false;
	private Random random = new Random();
	private Point2D[] points = new Point2D[4];
	
	/**
	 * Constructor.
	 * @param position Global position.
	 * @param scale Global scale.
	 * @param color Color of Block.
	 * @param subdivisions How many times to subdivide the Block into smaller blocks.
	 * 					   Minimum = 0, Maximum = 8. Will be clamped between those values.
	 */
	public Block(Point2D position, Vector2D scale, Color color, int subdivisions) {
		this.position = position;
		this.scale = scale;
		this.color = color;
		this.subdivisions = (int)Utils.Clamp(subdivisions, 0, 8);
		initializeSubBoxes();
		initalizePoints();
	}
	
	public void update(float deltaTime) {
		for (Box box : subBoxes) {
			box.update(deltaTime);
		}
	}
	
	public void draw() {
		for (Box box : subBoxes) {
			box.draw();
		}
	}
	
	public boolean pointIsInside(float mouseX, float mouseY) {
		if (exploded) {
			return false;
		}
		if (mouseX <= position.x+scale.x/2 && mouseX >= position.x-scale.x/2 &&
			mouseY <= position.y+scale.y/2 && mouseY >= position.y-scale.y/2) {
			exploded = true;
			return true;
		}
		return false;
	}
	
	public void explode() {
		exploded = true;
		for (Box box : subBoxes) {
			box.setMoving(true);
			box.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f));
		}
	}
	
	private void initializeSubBoxes() {
		subBoxes = subdivide(subdivisions, Settings.HORIZONTAL, position.x, position.y, scale.x, scale.y);
	}
	
	private ArrayList<Box> subdivide(int numDivisionsLeft, String direction, float Px, float Py, float Sx, float Sy) {
		if (numDivisionsLeft == 0) {
			ArrayList<Box> list =  new ArrayList<Box>();
			list.add(new Box(new Point2D(Px, Py), new Vector2D(Sx, Sy), color));
			return list;
		}
		else if (direction == Settings.HORIZONTAL) {					
				ArrayList<Box> list = new ArrayList<Box>(
						subdivide(numDivisionsLeft-1, Settings.VERTICAL, Px-Sx/4, Py, Sx/2, Sy));
				list.addAll(
						subdivide(numDivisionsLeft-1, Settings.VERTICAL, Px+Sx/4, Py, Sx/2, Sy));
				return list;
		}
		else { 
			ArrayList<Box> list = new ArrayList<Box>(
					subdivide(numDivisionsLeft-1, Settings.HORIZONTAL, Px, Py-Sy/4, Sx, Sy/2));
			
			list.addAll(
					subdivide(numDivisionsLeft-1, Settings.HORIZONTAL, Px, Py+Sy/4, Sx, Sy/2));
			return list;
		}
	}
	
	public Point2D[] getPoints() {
		return this.points;
	}
	
	private void initalizePoints() {
		points[0] = new Point2D(position.x - (scale.x/2), position.y - (scale.y/2));
		points[1] = new Point2D(position.x - (scale.x/2), position.y + (scale.y/2));
		points[2] = new Point2D(position.x + (scale.x/2), position.y + (scale.y/2));
		points[3] = new Point2D(position.x + (scale.x/2), position.y - (scale.y/2));
	}
	
	public boolean getExploded() {
		return this.exploded;
	}
}
