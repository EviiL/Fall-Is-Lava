package io.github.eviil.fallislava.Engine;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class Vector2 {

    protected double x;
    protected double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 multiply(Vector2 v, Vector2 v2){
        return new Vector2(v.x * v2.x, v.y * v2.y);
    }

    public Vector2 multiply(Vector2 v, double scalar){
        return new Vector2(v.x * scalar, v.y * scalar);
    }

}
