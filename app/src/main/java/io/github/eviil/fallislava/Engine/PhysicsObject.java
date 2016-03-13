package io.github.eviil.fallislava.Engine;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class PhysicsObject {

    private Vector2 position;
    private Vector2 size;

    public PhysicsObject(Vector2 position, Vector2 size){
        this.position = position;
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public double getWideCollisionRadius(){
        return size.x > size.y ? size.x * 3 : size.y * 3;
    }

    public boolean checkWithinRange(Vector2 position){
        double radius = getWideCollisionRadius();

        double distanceToPosition = Math.sqrt((position.x - this.position.x) * (position.x - this
                .position.x) + (position.y - this.position.y) * (position.y - this.position.y));

        return distanceToPosition < radius;
    }


}
