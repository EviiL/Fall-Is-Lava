package io.github.eviil.fallislava.Engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

import io.github.eviil.fallislava.Engine.Events.GameEvents;

/**
 * Created by P14137485 on 09/03/2016.
 *
 * Welcome to Thomas the Tank Engine.
 */
public class Engine {

	private ArrayList<GameEvents.onGameTickUpdate> tickUpdateArrayList = new ArrayList<>();
	private ArrayList<GameEvents.onTouchEvent> touchEventArrayList = new ArrayList<>();
	private ArrayList<PhysicsObject> physicsObjects = new ArrayList<>();
    private Context mContext;

    public Engine(Context context){
        mContext = context;
    }

    public void draw(Canvas canvas){
		Paint p = new Paint();
	    p.setARGB(100,255,255,255);
	    canvas.drawPaint(p);
    }

    public void update(){


	    if(tickUpdateArrayList.size() != 0){
			for(GameEvents.onGameTickUpdate tickEvent : tickUpdateArrayList){
				tickEvent.update();
			}
		}

	    if(physicsObjects.size() > 1){
		    for(PhysicsObject physObj : physicsObjects){
				for(PhysicsObject physObj2 : physicsObjects){
					if(!physObj.equals(physObj2)){
						if(physObj.checkWithinRange(physObj2.getPosition())) {
							physObj.getWideCollisionRadius();
						}
					}
				}
			    //Do collision stuff here.
		    }
	    }
    }

	public synchronized ArrayList<GameEvents.onGameTickUpdate> getTickUpdateArrayList() {
		return tickUpdateArrayList;
	}

	public synchronized void addNewTickListener(GameEvents.onGameTickUpdate listener) {
		if(!tickUpdateArrayList.contains(listener)){
			tickUpdateArrayList.add(listener);
		}
	}

	public void notifyTouchEvent(MotionEvent event){
		if(touchEventArrayList.size() != 0){
			for(GameEvents.onTouchEvent touchEvent : touchEventArrayList){
				touchEvent.onTouchEvent(event);
			}
		}
	}

	public ArrayList<PhysicsObject> getPhysicsObjects() {
		return physicsObjects;
	}

	public void addNewPhysicsObject(PhysicsObject object) {
		if(!physicsObjects.contains(object)) physicsObjects.add(object);
	}

	public ArrayList<GameEvents.onTouchEvent> getTouchEventArrayList() {
		return touchEventArrayList;
	}

	public void addNewTouchListener(GameEvents.onTouchEvent listener) {
		if(!touchEventArrayList.contains(listener)) touchEventArrayList.add(listener);
	}
}
