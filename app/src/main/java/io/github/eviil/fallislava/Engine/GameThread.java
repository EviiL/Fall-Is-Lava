package io.github.eviil.fallislava.Engine;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class GameThread implements Runnable{

    int runState = 1;
    public static final int RUNNING = 1;
    public static final int PAUSED = 2;

    private long sleepTime;

    private long delay = 10;

    private SurfaceHolder mSurfaceHolder;
    private Context mContext;

    public GameThread(SurfaceHolder surfaceHolder, Context context){
        this.mContext = context;
        mSurfaceHolder = surfaceHolder;
        mSurfaceHolder.setFormat(0x00000004);
    }

    @Override
    public void run(){

        while(runState == RUNNING) {

            long currentTime = System.nanoTime();

            //Update Game.

	        Engine.getInstance().update();

            //Draw the Game.
            Canvas c = null;
            try {
                c = mSurfaceHolder.lockCanvas(null);

                synchronized (mSurfaceHolder){
	                Engine.getInstance().draw(c);
                }
            } finally {
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }

            sleepTime = delay - ((System.nanoTime() - currentTime) / 1000000L);

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunState(int runState){
        this.runState = runState;
    }
}
