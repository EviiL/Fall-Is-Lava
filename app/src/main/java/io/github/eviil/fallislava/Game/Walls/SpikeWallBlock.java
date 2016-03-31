package io.github.eviil.fallislava.Game.Walls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by Thomas Dotters on 21/03/2016.
 */
public class SpikeWallBlock extends WallBlock{
//	Path path = new Path();
    private Rect spikeRect;
    Paint paint = new Paint();

	public SpikeWallBlock(Vector2 position, Vector2 size) {
		super(position, size);
//		path.setFillType(Path.FillType.EVEN_ODD);
        spikeRect = new Rect((int)position.getX() + (int )getSize().getX() ,(int) position.getY(),
                (int) position.getX() +
                (int)
                        (getSize().getX() * 2),
                (int)position.getY() + (int)getSize().getY());
	}

	public void update(){
//		path.rewind();
//		path.moveTo(getPosition().getX() + getSize().getX(), getPosition().getY());
//		path.lineTo(getPosition().getX() + getSize().getX() * 2, getPosition().getY() + (getSize
//				().getY() / 2));
//		path.lineTo(getPosition().getX() + getSize().getX(), getPosition().getY() + getSize()
//				.getY());
//		path.lineTo(getPosition().getX() + getSize().getX(), getPosition().getY());

        spikeRect.set((int)getPosition().getX() + (int )getSize().getX() ,(int) getPosition().getY(),
                (int) getPosition().getX() +
                        (int)
                                (getSize().getX() * 2),
                (int)getPosition().getY() + (int)getSize().getY());
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);



		paint.setColor(android.graphics.Color.RED);
		paint.setAntiAlias(true);

//		canvas.drawPath(path, paint);

        canvas.drawBitmap(Engine.getInstance().getBitmap(6), null, spikeRect, p);

    }
}
