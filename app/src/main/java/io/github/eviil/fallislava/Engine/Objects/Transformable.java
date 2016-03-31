package io.github.eviil.fallislava.Engine.Objects;

import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by p14137485 on 16/03/2016.
 */
public class Transformable {

    private Vector2 position;
    private Vector2 size;

    public Transformable(Vector2 position, Vector2 size){
        this.position = position;
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

	public void setSize(Vector2 size){
		this.size = size;
	}

    public void move(Vector2 position){
        this.position = position;
    }

    public void move(float x, float y){
        position.add(x, y);
    }
}
