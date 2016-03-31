package io.github.eviil.fallislava.Game.Walls;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

import io.github.eviil.fallislava.Engine.Engine;
import io.github.eviil.fallislava.Engine.Events.EngineEventListener;
import io.github.eviil.fallislava.Engine.Events.GameEvents;
import io.github.eviil.fallislava.Engine.Objects.Drawable;
import io.github.eviil.fallislava.Engine.Util.Utils;
import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by p14137485 on 16/03/2016.
 */
public class Wall implements Drawable, EngineEventListener.onGameTickUpdateListener{

    public static final int LEFT_SIDE_WALL = 1;
    public static final int RIGHT_SIDE_WALL = 2;
    private GameEvents.onGameTickUpdate tickListener;

    private ArrayList<WallBlock> wallBlocks = new ArrayList<>();


    public Wall(Vector2 screenSize, Vector2 wallPieceSize, int side){


	    Engine.getInstance().addNewDrawable(this);


        float  wallPieces =  (screenSize.getY() / wallPieceSize.getY()) * 1.5f;

        for(int i = 0; i < wallPieces; i++){

            float pos = 0 + (wallPieceSize.getY() * i) - wallPieceSize.getY();

            if(side == LEFT_SIDE_WALL) {
                wallBlocks.add(new WallBlock(new Vector2(0.0f, pos), wallPieceSize));
            }
            if(side == RIGHT_SIDE_WALL){
                wallBlocks.add(new WallBlock(new Vector2(screenSize.getX() - wallPieceSize.getX(), pos),
                        wallPieceSize));
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for( WallBlock wallBlock: wallBlocks){
            wallBlock.draw(canvas);
        }
    }

    @Override
    public void setOnGameTickUpdateListener(GameEvents.onGameTickUpdate listener) {
        tickListener = listener;
        Engine.getInstance().addNewTickListener(listener);
    }

    public void update(){
	    for( WallBlock wallBlock: wallBlocks){
		    if(wallBlock instanceof SpikeWallBlock){
			    ((SpikeWallBlock) wallBlock).update();
		    }
		    wallBlock.move(0, 5);
	    }

        boolean needsNew = false;
        Iterator<WallBlock> i = wallBlocks.iterator();

        while(i.hasNext()){
            WallBlock block = i.next();
            if(block.getPosition().getY() > Engine.getInstance().getScreenSize().getY() + block
                    .getSize().getY()){
                i.remove();

                needsNew = true;
            }
        }

        if(needsNew) {
	        int randomNumber = Utils.randInt(1, 10);
	        if (randomNumber == 5) {
		        wallBlocks.add(0, new SpikeWallBlock(new Vector2(wallBlocks.get(0).getPosition()
				        .getX(),
				        wallBlocks.get(0).getPosition().getY() - wallBlocks.get(0).getSize().getY
						        ()), wallBlocks.get(0).getSize()));
	        } else {
		        wallBlocks.add(0, new WallBlock(new Vector2(wallBlocks.get(0).getPosition().getX(),
				        wallBlocks.get(0).getPosition().getY() - wallBlocks.get(0).getSize().getY
						        ()), wallBlocks.get(0).getSize()));
	        }

        }
    }

    public GameEvents.onGameTickUpdate getTickListener() {
        return tickListener;
    }
}
