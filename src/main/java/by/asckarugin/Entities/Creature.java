package by.asckarugin.Entities;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.Coordinates;
import by.asckarugin.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private int health;
    protected Area area;
    protected PathFinder pathFinder;

    public Creature(Coordinates coordinates, int health, Area area) {
        super(coordinates);
        this.health = health;
        this.area = area;
        pathFinder = new PathFinder();
    }

    public void changeHealth(int amount) {
        health += amount;
    }

    public abstract boolean isTarget(Entity entity);

    public abstract void performAction(Coordinates nextPosition);

    public void makeMove() {
        Coordinates currentCoordinates = getCoordinates();

        changeHealth(-5);

        if (health <= 0) {
            area.removeEntityAtLocation(currentCoordinates);
            return;
        }

        Coordinates targetCoordinates = pathFinder.findNearestEntity(this, area);

        if (targetCoordinates != null) {
            List<Coordinates> path = pathFinder.findPath(currentCoordinates, this, area);

            if (!path.isEmpty()) {
                Coordinates nextPosition = path.get(1);
                performAction(nextPosition);

                area.removeEntityAtLocation(currentCoordinates);
                setCoordinates(nextPosition);
                area.addEntity(getCoordinates(), this);
            }
        }
    }
}
