package io.github.eviil.fallislava.Engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.util.ArrayList;

import io.github.eviil.fallislava.Engine.Application.App;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.Objects.Drawable;
import io.github.eviil.fallislava.Engine.Objects.PhysicsObject;
import io.github.eviil.fallislava.Engine.Util.BitmapHandler;
import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by P14137485 on 09/03/2016.
 *
 * Welcome to Thomas the Tank Engine. ^_^
 */
public class Engine {

	private static Engine ourInstance = new Engine(App.getContext());

	public static Engine getInstance() {
		return ourInstance;
	}


	private ArrayList<GameEvents.onGameTickUpdate> tickUpdateArrayList = new ArrayList<>();
	private ArrayList<GameEvents.onTouchEvent> touchEventArrayList = new ArrayList<>();
	private ArrayList<Drawable> drawables = new ArrayList<>();

    private ArrayList<PhysicsObject> physicsObjects = new ArrayList<>();

    private Vector2 screenSize;

	private BitmapHandler bitmapHandler;

    private Context mContext;
    Paint p = new Paint();

    public Engine(Context context){
        mContext = context;
        p.setARGB(255, 255, 255, 255);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenSize = new Vector2(size.x, size.y);

	    bitmapHandler = new BitmapHandler();
    }

    public void draw(Canvas canvas){
	    canvas.drawPaint(p);
		canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //Draw the drawables.
        for( Drawable drawable : drawables){
            drawable.draw(canvas);
        }
    }

    public void update(){

	    if(tickUpdateArrayList.size() != 0){
			for(GameEvents.onGameTickUpdate tickEvent : tickUpdateArrayList){
				tickEvent.update();
			}
		}
        //Do collision stuff here.

	    if(physicsObjects.size() > 1){
		    for(PhysicsObject physObj : physicsObjects){
				for(PhysicsObject physObj2 : physicsObjects){
					if(!physObj.equals(physObj2)){
						if(physObj.checkWithinRange(physObj2.getPosition())) {
							physObj.getWideCollisionRadius();
						}
					}
				}
		    }
	    }
    }

	public int addBitmap(int id, int reqWidth, int reqHeight){
		Bitmap bitmap = BitmapHandler.decodeSampledBitmapFromResource(mContext.getResources(), id,
				reqWidth,
				reqHeight);

		bitmapHandler.loadBitmapToStore(bitmap);
		return bitmapHandler.getLoadedBitmaps().indexOf(bitmap);
	}

	public int addBitmap(Bitmap bitmap){
		bitmapHandler.loadBitmapToStore(bitmap);
		return bitmapHandler.getLoadedBitmaps().indexOf(bitmap);
	}

	public Bitmap getBitmap(int position){
		return bitmapHandler.getLoadedBitmaps().get(position);
	}

    public void addNewDrawable(Drawable drawable) {
	    drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable){
        if(drawables.contains(drawable)){
            int index = drawables.indexOf(drawable);
            drawables.remove(index);
        }
    }

    public ArrayList<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    public void addNewPhysicsObject(PhysicsObject object) {
        if(!physicsObjects.contains(object)) physicsObjects.add(object);
    }

    public void removePhysicsObject(PhysicsObject object){
        if(physicsObjects.contains(object)){
            int index = physicsObjects.indexOf(object);
            physicsObjects.remove(index);
        }
    }


    //////////     Listeners Below     //////////

	public synchronized ArrayList<GameEvents.onGameTickUpdate> getTickUpdateArrayList() {
		return tickUpdateArrayList;
	}

	public synchronized void addNewTickListener(GameEvents.onGameTickUpdate listener) {
		if(!tickUpdateArrayList.contains(listener)){
			tickUpdateArrayList.add(listener);
		}
	}
    public void removeTickListener(GameEvents.onGameTickUpdate listener){
        if(tickUpdateArrayList.contains(listener)) tickUpdateArrayList.remove(listener);
    }

	public void notifyTouchEvent(MotionEvent event){
		if(touchEventArrayList.size() != 0){
			for(GameEvents.onTouchEvent touchEvent : touchEventArrayList){
				touchEvent.onTouchEvent(event);
			}
		}
	}

	public ArrayList<GameEvents.onTouchEvent> getTouchEventArrayList() {
		return touchEventArrayList;
	}

	public void addNewTouchListener(GameEvents.onTouchEvent listener) {
		if(!touchEventArrayList.contains(listener)) touchEventArrayList.add(listener);
	}

    public void removeTouchListener(GameEvents.onTouchEvent listener){
        if(touchEventArrayList.contains(listener)) touchEventArrayList.remove(listener);
    }

    //////////     Listeners End    //////////


    public Vector2 getScreenSize() {
        return screenSize;
    }
}
