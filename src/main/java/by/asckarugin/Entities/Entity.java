package by.asckarugin.Entities;

import by.asckarugin.Area.Coordinates;

public abstract class Entity {

    public Coordinates coordinates;


    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


}
