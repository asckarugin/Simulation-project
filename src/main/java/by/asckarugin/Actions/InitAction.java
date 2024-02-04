package by.asckarugin.Actions;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.Coordinates;
import by.asckarugin.Entities.*;

import java.util.Map;
import java.util.Random;

public class InitAction extends Action {
    private Area area;
    private Random random = new Random();

    public InitAction(Area area) {
        this.area = area;
    }

    @Override
    public void perform() {
        setRandomEntities(7, 15, 25, 15, 15);
    }

    private void setRandomEntities(int numPredators, int numHerbivores, int numGrass, int numRocks, int numTrees) {
        for (int i = 0; i < numPredators; i++) {
            Coordinates coordinates = generateRandomPosition();
            area.addEntity(coordinates, new Predator(coordinates,area));
        }

        for (int i = 0; i < numHerbivores; i++) {
            Coordinates coordinates = generateRandomPosition();
            area.addEntity(coordinates, new Herbivore(coordinates,area));
        }

        for (int i = 0; i < numGrass; i++) {
            Coordinates coordinates = generateRandomPosition();
            area.addEntity(coordinates, new Grass(coordinates));
        }

        for (int i = 0; i < numRocks; i++) {
            Coordinates coordinates = generateRandomPosition();
            area.addEntity(coordinates, new Rock(coordinates));
        }

        for (int i = 0; i < numTrees; i++) {
            Coordinates coordinates = generateRandomPosition();
            area.addEntity(coordinates, new Tree(coordinates));
        }
    }

    private Coordinates generateRandomPosition() {
        Coordinates coordinates;
        Map<Coordinates, Entity> entities = area.getEntities();
        do {
            int x = random.nextInt(area.getWidth());
            int y = random.nextInt(area.getHeight());
            coordinates = new Coordinates(x, y);
        } while (entities.containsKey(coordinates));
        return coordinates;
    }
}