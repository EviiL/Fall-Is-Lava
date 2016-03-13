package io.github.eviil.fallislava.Game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.GameView;

public class GameActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Engine engine = new Engine(this);
		GameView gameView = new GameView(this, engine);
		setContentView(gameView);

		Game game = new Game(engine);
	}
}
