package by.asckarugin.Area;

import by.asckarugin.Entities.Entity;
import by.asckarugin.Entities.Grass;
import by.asckarugin.Entities.Herbivore;

import java.util.HashMap;
import java.util.Map;

public class Area {
    private final int height = 15;
    private final int width = (int) (height*1.5);
    private Map<Coordinates, Entity> entities = new HashMap<>();

    public Area() {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public Entity getEntityAtLocation(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean isValidPosition(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < width && coordinates.getY() >= 0 && coordinates.getY() < height;
    }

    public Map<Coordinates, Entity> getEntities() {
        return new HashMap<>(entities);
    }

    public void removeEntityAtLocation(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public <T extends Entity> boolean hasEntity(Class<T> entityClass) {
        for (Entity entity : entities.values()) {
            if (entity.getClass() == entityClass) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGrass() {
        return hasEntity(Grass.class);
    }

    public boolean hasHerbivore() {
        return hasEntity(Herbivore.class);
    }
}
