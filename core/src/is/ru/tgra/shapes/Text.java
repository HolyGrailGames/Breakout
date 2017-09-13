package is.ru.tgra.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.utils.Point2D;

public class Text {
	private SpriteBatch batch = new SpriteBatch();
	//private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/8bit16.ttf"), false);
	private BitmapFont font = new BitmapFont();
	private String text;
	private Point2D position;
	
	public Text(String text, Point2D position, Color color) {
		font.setColor(color);
		this.text = text;
		this.position = position;
	}

	public void draw() {
		batch.begin();
		font.draw(batch, text, position.x, position.y);
		batch.end();
		Gdx.gl.glUseProgram(GraphicsEnvironment.renderingProgramID);
		Gdx.gl.glEnableVertexAttribArray(GraphicsEnvironment.positionLoc);
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
