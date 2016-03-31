package io.github.eviil.fallislava.Game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Objects.Drawable;

/**
 * Created by p14137485 on 18/03/2016.
 */
public class Background implements Drawable {

    private Rect rect;
    Paint p = new Paint();

    public Background(){
        rect = new Rect(0,0,(int)Engine.getInstance().getScreenSize().getX(),(int) Engine
                .getInstance()
                .getScreenSize().getY() + 200);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(Engine.getInstance().getBitmap(1), null, rect, p);
    }
}
