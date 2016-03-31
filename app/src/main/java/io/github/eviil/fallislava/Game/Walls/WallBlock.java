package io.github.eviil.fallislava.Game.Walls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Objects.Drawable;
import io.github.eviil.fallislava.Engine.Objects.Transformable;
import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by p14137485 on 16/03/2016.
 */
public class WallBlock extends Transformable implements Drawable {

    Paint p = new Paint();
	private Rect drawRect;

    public WallBlock(Vector2 position, Vector2 size){
        super(position, size);
        p.setARGB(255, 231, 76, 60);
	    drawRect = new Rect((int)position.getX() ,(int) position.getY(),(int) position.getX() + (int)size.getX(),
			    (int)position.getY() + (int)size.getY());

    }

    @Override
    public void draw(Canvas canvas) {


	    drawRect.set((int)getPosition().getX() ,(int) getPosition().getY(),(int) getPosition().getX() + (int)
					    getSize().getX(),
			    (int)getPosition().getY() + (int)getSize().getY());

		canvas.drawBitmap(Engine.getInstance().getBitmap(0), null, drawRect, p);
    }
}
