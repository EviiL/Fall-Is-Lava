package io.github.eviil.fallislava.Engine.Objects;

import io.github.eviil.fallislava.Engine.Util.Vector2;

/**
 * Created by P14137485 on 09/03/2016.
 */
public class PhysicsObject extends Transformable {

    public PhysicsObject(Vector2 position, Vector2 size){
        super(position, size);
    }

    public double getWideCollisionRadius(){
        return getSize().getX() > getSize().getY() ? getSize().getX() * 3 : getSize().getY() * 3;
    }

    public boolean checkWithinRange(Vector2 position){
        double radius = getWideCollisionRadius();

        double distanceToPosition = Math.sqrt((position.getX() - getPosition().getX()) * (position
                .getX() -
                getPosition().getX())
                + (position.getY() - getPosition().getY()) * (position.getY() - getPosition().getY()));

        return distanceToPosition < radius;
    }


}
