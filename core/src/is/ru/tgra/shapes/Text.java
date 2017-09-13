package is.ru.tgra.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import is.ru.tgra.graphics.GraphicsEnvironment;
import is.ru.tgra.utils.Point2D;

public class Text {
	private SpriteBatch batch;
	private BitmapFont font;
	private String text;
	private Point2D position;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	
	public Text(String text, Point2D position, Color color, int fontSize) {
		this.text = text;
		this.position = position;
		batch = new SpriteBatch();
	
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/game_boy.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		parameter.size = fontSize;
		parameter.color = color;
		font = generator.generateFont(parameter);
		generator.dispose();
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
	
	public void setColor(float r, float g, float b, float a) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/game_boy.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		
		parameter.size = 64;
		parameter.color = new Color(r, g, b, a);
		font = generator.generateFont(parameter);
		generator.dispose();
	}
}
