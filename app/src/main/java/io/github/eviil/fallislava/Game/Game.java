package io.github.eviil.fallislava.Game;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.Vector2;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class Game {
	public static final String TAG = "ImmersiveModeFragment";

    public Game(Engine engine){

        final Player player = new Player(new Vector2(10,10), new Vector2(10,10), engine);

        player.setOnGameTickUpdateListener(new GameEvents.onGameTickUpdate() {
            @Override
            public void update() {
	            player.update();
            }
        });
    }
}
