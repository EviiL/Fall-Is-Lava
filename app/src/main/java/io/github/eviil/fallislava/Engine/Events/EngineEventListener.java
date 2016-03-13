package io.github.eviil.fallislava.Engine.Events;

/**
 * Created by P14137485 on 09/03/2016.
 */
public interface EngineEventListener {



    interface onGameTickUpdateListener{

        void setOnGameTickUpdateListener(GameEvents.onGameTickUpdate listener);
    }

    interface onTouchEvent{
        void setOnTouchEvent(GameEvents.onTouchEvent listener);
    }
}
