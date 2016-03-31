package io.github.eviil.fallislava.Game;

import android.view.MotionEvent;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.Util.BitmapHandler;
import io.github.eviil.fallislava.Engine.Util.Vector2;
import io.github.eviil.fallislava.Game.Walls.Wall;
import io.github.eviil.fallislava.R;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class Game {
    private Player player;

    private Wall leftWall;
    private Wall rightWall;
    boolean paused = false;

    public Game(){

	    Engine.getInstance().addBitmap(R.drawable.rock, 80, 80);
        Engine.getInstance().addBitmap(R.drawable.background, 500, 600);
        Engine.getInstance().addBitmap(R.drawable.player_left,24,48);
		Engine.getInstance().addBitmap(BitmapHandler.flip(Engine.getInstance().getBitmap(2)));
	    Engine.getInstance().addBitmap(R.drawable.player_jump_left, 48, 48);
	    Engine.getInstance().addBitmap(BitmapHandler.flip(Engine.getInstance().getBitmap(4)));
        Engine.getInstance().addBitmap(R.drawable.sharp_rock, 80, 80);

        Background background = new Background();

        Engine.getInstance().addNewDrawable(background);

        player = new Player(new Vector2(80, Engine.getInstance().getScreenSize().getY() - 200), new
                    Vector2
                    (48, 98));

        player.setOnGameTickUpdateListener(new GameEvents.onGameTickUpdate() {
            @Override
            public void update() {
                player.update();
            }
        });

        player.setOnTouchEvent(new GameEvents.onTouchEvent() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                player.jump();
            }
        });

       leftWall = new Wall(Engine.getInstance().getScreenSize(), new Vector2(80,160), 1);
        leftWall.setOnGameTickUpdateListener(new GameEvents.onGameTickUpdate() {
            @Override
            public void update() {
                leftWall.update();
            }
        });

        rightWall = new Wall(Engine.getInstance().getScreenSize(),  new Vector2(80,160), 2);
        rightWall.setOnGameTickUpdateListener(new GameEvents.onGameTickUpdate() {
            @Override
            public void update() {
                rightWall.update();
            }
        });
    }

    public void pause(){
        paused = true;
    }

    public void resume(){
        paused = false;
    }

    public void finish(){


        Engine.getInstance().removeTickListener(player.getTickListener());
        Engine.getInstance().removeTouchListener(player.getTouchListener());
        Engine.getInstance().removeTickListener(leftWall.getTickListener());
        Engine.getInstance().removeTickListener(rightWall.getTickListener());

        Engine.getInstance().removePhysicsObject(player);
        Engine.getInstance().removeDrawable(player);
        Engine.getInstance().removeDrawable(leftWall);
        Engine.getInstance().removeDrawable(rightWall);
    }
}
