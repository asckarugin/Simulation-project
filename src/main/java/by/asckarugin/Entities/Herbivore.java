package by.asckarugin.Entities;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.Coordinates;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, Area area) {
        super(coordinates, 80, area);
    }

    @Override
    public boolean isTarget(Entity entity) {
        return entity instanceof Grass;
    }

    @Override
    public void performAction(Coordinates nextCoordinate) {
        Entity entityAtNextCoordinates = area.getEntityAtLocation(nextCoordinate);

        if (entityAtNextCoordinates instanceof Grass) {
            eatGrass(nextCoordinate);
        }
    }

    public void eatGrass(Coordinates coordinates) {
        area.removeEntityAtLocation(coordinates);
        changeHealth(5);
    }
}
