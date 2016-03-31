package io.github.eviil.fallislava.Game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Events.EngineEventListener;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.Objects.Drawable;
import io.github.eviil.fallislava.Engine.Objects.PhysicsObject;
import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by Thomas Dotters on 23/11/2015.
 */
public class Player extends PhysicsObject implements EngineEventListener
        .onGameTickUpdateListener, Drawable, EngineEventListener.onTouchEvent {

    Paint p = new Paint();
    private boolean isJumping;
    private Path jumpPath;
    int currStep = 0;
    boolean fromLeft = true;
    float afP[] = {0f, 0f};
    PathMeasure pm;
    float segment;

    Rect playerSprite;

    float startX;
    float startY;
    private GameEvents.onGameTickUpdate tickListener;
    private GameEvents.onTouchEvent touchListener;

    public Player(Vector2 position, Vector2 size) {
        super(position, size);
        p.setARGB(255, 0, 0, 0);

	    startX = position.getX();
        startY = position.getY();

        jumpPath = new Path();

        playerSprite = new Rect((int) startX,  (int) startY,  (int) startX + (int)size.getX(), (int)
                startY + (int)size.getY());

        jumpPath.moveTo(startX, startY);
        jumpPath.quadTo((Engine.getInstance().getScreenSize().getX() / 2), startY - 300, Engine
                        .getInstance()
                        .getScreenSize()
                        .getX() - size.getX() - startX,
                startY);

        pm = new PathMeasure(jumpPath, false);
        segment = pm.getLength() / 40;
	    Engine.getInstance().addNewDrawable(this);
	    Engine.getInstance().addNewPhysicsObject(this);
    }

    public void update(){
       // move(10, 0);
        if(isJumping){
	        setSize(new Vector2(72,98));
            if(fromLeft){
                if(currStep <= 40){
                    pm.getPosTan(segment * currStep, afP, null);
                    pm.getPosTan(segment * currStep, afP, null);
                    move(new Vector2(afP[0], afP[1]));
                    currStep++;
                }else{
                    currStep = 40;
                    fromLeft = false;
                    isJumping = false;
	                setSize(new Vector2(48,98));
                }
            }else{
                if(currStep >= 0){
                    pm.getPosTan(segment * currStep, afP, null);
                    move(new Vector2(afP[0], afP[1]));
                    currStep--;
                }else{
                    currStep = 0;
                    fromLeft = true;
                    isJumping = false;
	                setSize(new Vector2(48,98));
                }
            }
        }
    }

    public void jump(){
        isJumping = true;
    }


    @Override
    public void setOnGameTickUpdateListener(GameEvents.onGameTickUpdate listener) {
        tickListener = listener;
        Engine.getInstance().addNewTickListener(listener);
    }

    @Override
    public void draw(Canvas canvas) {
        playerSprite.set((int) getPosition().getX(), (int) getPosition().getY(), (int)
		        getPosition().getX() + (int)
		        getSize()
				        .getX
						        (), (int)
		        getPosition().getY() + (int) getSize().getY());
	    if(isJumping){
		    if(fromLeft){
			    canvas.drawBitmap(Engine.getInstance().getBitmap(5), null, playerSprite,p);
		    }else{
			    canvas.drawBitmap(Engine.getInstance().getBitmap(4), null, playerSprite,p);
		    }
	    }else {
		    if (fromLeft) {
			    canvas.drawBitmap(Engine.getInstance().getBitmap(2), null, playerSprite, p);
		    } else {
			    canvas.drawBitmap(Engine.getInstance().getBitmap(3), null, playerSprite, p);
		    }
	    }
    }

    @Override
    public void setOnTouchEvent(GameEvents.onTouchEvent listener) {
        touchListener = listener;
        Engine.getInstance().addNewTouchListener(listener);
    }

    public GameEvents.onGameTickUpdate getTickListener() {
        return tickListener;
    }

    public GameEvents.onTouchEvent getTouchListener() {
        return touchListener;
    }
}
