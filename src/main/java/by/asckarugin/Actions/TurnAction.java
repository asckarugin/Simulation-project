package by.asckarugin.Actions;

import by.asckarugin.Area.Area;
import by.asckarugin.Area.AreaRender;
import by.asckarugin.Entities.Creature;
import by.asckarugin.Entities.Entity;

import java.util.ArrayList;

public class TurnAction extends Action {
    private Area area;
    private int turnCount;
    private AreaRender renderer;
    private boolean isPaused;
    private Object lock;

    public TurnAction(Area area, AreaRender renderer) {
        this.area = area;
        this.renderer = renderer;
        isPaused = false;
        lock = new Object();
    }

    @Override
    public void perform() {
        turnCount++;

        if (!isPaused) {
            for (Entity entity : new ArrayList<>(area.getEntities().values())) {
                if (entity instanceof Creature) {
                    Creature creature = (Creature) entity;
                    creature.makeMove();
                }
            }

            renderer.renderArea(area);
            System.out.println("Turn count: " + turnCount);
        }
    }

    public void pauseSimulation() {
        isPaused = true;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void resumeSimulation() {
        isPaused = false;
        synchronized (lock) {
            lock.notify();
        }
    }
}
