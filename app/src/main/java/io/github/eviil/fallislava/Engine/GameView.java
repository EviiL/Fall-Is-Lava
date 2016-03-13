package io.github.eviil.fallislava.Engine;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by p14137485 on 09/03/2016.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Context mContext;

    private GameThread thread;
	private Handler handler;

    private Engine engine;

    public GameView(Context context, Engine engine) {
        super(context);

        mContext = context;
        getHolder().addCallback(this);

        setFocusable(true);

	    this.engine = engine;
		HandlerThread handlerThread = new HandlerThread("");
	    handlerThread.start();
	    handler = new Handler(handlerThread.getLooper());


    }


	@Override
	public boolean onTouchEvent(MotionEvent event){
		int action = event.getAction();
		engine.notifyTouchEvent(event);

		return true;
	}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
	    thread = new GameThread(getHolder(), mContext, this.engine);
	    handler.post(thread);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
