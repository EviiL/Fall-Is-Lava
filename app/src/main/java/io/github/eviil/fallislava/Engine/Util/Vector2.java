package io.github.eviil.fallislava.Engine.Util;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class Vector2 {

    protected volatile float x;
    protected volatile float y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2 multiply(Vector2 v, Vector2 v2){
        return new Vector2(v.x * v2.x, v.y * v2.y);
    }

    public Vector2 multiply(Vector2 v, float scalar){
        return new Vector2(v.x * scalar, v.y * scalar);
    }

    public void add(float x, float y){
        this.x += x;
        this.y += y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

}
