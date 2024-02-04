package by.asckarugin;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.Coordinates;
import by.asckarugin.Entities.Creature;
import by.asckarugin.Entities.Entity;

import java.util.*;

public class PathFinder {

    public List<Coordinates> findPath(Coordinates startCoordinates, Creature creature, Area area) {
        Deque<Coordinates> coordinatesDeque = new ArrayDeque<>();
        Set<Coordinates> visitedCoordinates = new HashSet<>();

        HashMap<Coordinates, Coordinates> parents = new HashMap<>();
        coordinatesDeque.add(startCoordinates);
        Coordinates targetPosition = null;
        boolean foundTarget = false;

        while (!coordinatesDeque.isEmpty() && !foundTarget) {
            Coordinates currentCoordinates = coordinatesDeque.remove();
            visitedCoordinates.add(currentCoordinates);
            List<Coordinates> neighborCoordinates = getNeighborCoordinates(currentCoordinates, area.getWidth(), area.getHeight());

            for (Coordinates isNeighborCoordinates : neighborCoordinates) {
                if (area.isValidPosition(isNeighborCoordinates) && !visitedCoordinates.contains(isNeighborCoordinates)) {
                    Entity entityAtLocation = area.getEntityAtLocation(isNeighborCoordinates);
                    if (creature.isTarget(entityAtLocation)) {
                        targetPosition = isNeighborCoordinates;
                        parents.put(isNeighborCoordinates, currentCoordinates);
                        foundTarget = true;
                        break;
                    }
                    if (entityAtLocation == null) {
                        coordinatesDeque.add(isNeighborCoordinates);
                        parents.put(isNeighborCoordinates, currentCoordinates);
                    }
                }
            }
        }

        List<Coordinates> path = new LinkedList<>();
        if (targetPosition != null) {
            path.add(targetPosition);
            Coordinates tempPosition = targetPosition;
            while (path.get(path.size() - 1) != startCoordinates) {
                tempPosition = parents.get(tempPosition);
                path.add(tempPosition);
            }
            Collections.reverse(path);
        }

        return path;
    }

    public Coordinates findNearestEntity(Creature creature, Area area) {
        Coordinates currentPosition = creature.getCoordinates();
        Coordinates nearestEntity = null;
        double nearestDistance = Double.POSITIVE_INFINITY;

        for (Coordinates coordinates : area.getEntities().keySet()) {
            Entity entity = area.getEntityAtLocation(coordinates);

            if (creature.isTarget(entity)) {
                double distance = calculateDistance(currentPosition, coordinates);

                if (distance <= nearestDistance) {
                    nearestEntity = coordinates;
                    nearestDistance = distance;
                }
            }
        }

        return nearestEntity;
    }

    private double calculateDistance(Coordinates coordinates1, Coordinates coordinates2) {
        int deltaX = coordinates2.getX() - coordinates1.getX();
        int deltaY = coordinates2.getY() - coordinates1.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private List<Coordinates> getNeighborCoordinates(Coordinates coordinates, int mapWidth, int mapHeight) {
        int width;
        int height;
        List<Coordinates> neighborCoordinates = new ArrayList<>();

        if ((width = coordinates.getX() - 1) >= 0) {
            neighborCoordinates.add(new Coordinates(width, coordinates.getY()));
        }
        if ((width = coordinates.getX() + 1) < mapWidth) {
            neighborCoordinates.add(new Coordinates(width, coordinates.getY()));
        }
        if ((height = coordinates.getY() - 1) >= 0) {
            neighborCoordinates.add(new Coordinates(coordinates.getX(), height));
        }
        if ((height = coordinates.getY() + 1) < mapHeight) {
            neighborCoordinates.add(new Coordinates(coordinates.getX(), height));
        }

        return neighborCoordinates;
    }
}
