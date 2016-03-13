package io.github.eviil.fallislava.Game;

import android.graphics.Canvas;

import io.github.eviil.fallislava.Engine.Drawable;
import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Events.EngineEventListener;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.PhysicsObject;
import io.github.eviil.fallislava.Engine.Vector2;

/**
 * Created by Thomas Dotters on 23/11/2015.
 */
public class Player extends PhysicsObject implements EngineEventListener
        .onGameTickUpdateListener, Drawable {

    private GameEvents.onGameTickUpdate onGameTickUpdate = null;
    private Engine engine;

    public Player(Vector2 position, Vector2 size, Engine engine) {
        super(position, size);
	    this.engine = engine;
    }

    public void update(){

    }

    @Override
    public void setOnGameTickUpdateListener(GameEvents.onGameTickUpdate listener) {
        onGameTickUpdate = listener;
	    engine.addNewTickListener(listener);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
