package by.asckarugin.Entities;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.Coordinates;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, Area area) {
        super(coordinates, 100, area);
    }

    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Herbivore;
    }

    @Override
    public void performAction(Coordinates nextCoordinates) {
        Entity entityAtNextCoordinates = area.getEntityAtLocation(nextCoordinates);

        if (entityAtNextCoordinates instanceof Herbivore) {
            eatHerbivore(nextCoordinates);
        }
    }

    public void eatHerbivore(Coordinates coordinates) {
        area.removeEntityAtLocation(coordinates);
        changeHealth(10);
    }
}
