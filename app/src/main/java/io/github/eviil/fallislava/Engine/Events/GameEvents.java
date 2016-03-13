package io.github.eviil.fallislava.Engine.Events;

import android.view.MotionEvent;

/**
 * Created by P14137485 on 09/03/2016.
 */
public interface GameEvents {

    interface onGameTickUpdate{
        void update();
    }

    interface onTouchEvent{
        void onTouchEvent(MotionEvent event);
    }
}
