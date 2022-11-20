package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class Animal implements IMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    private final IWorldMap map;
    public Animal(IWorldMap map){
        this.map = map;
    }

    private List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.observers = new LinkedList<>();

        this.addObserver((IPositionChangeObserver) map);
    }

    public String toString() {
        return switch(orientation){
            case NORTH -> "N";
            case WEST -> "W";
            case EAST -> "E";
            case SOUTH -> "S";
        };
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();

            //default handles forward and backward
            default -> {
                Vector2d newPosition = (direction.equals(MoveDirection.FORWARD)) ?
                        position.add(orientation.toUnitVector()) : position.add(orientation.toUnitVector().opposite());
                if (map.canMoveTo(newPosition)){
                    Vector2d oldPosition = this.position;
                    boolean isFieldWithGrass = map.objectAt(newPosition) instanceof Grass;
                    position = newPosition;
                    positionChanged(oldPosition, newPosition);
                    if(isFieldWithGrass){
                        ((GrassField) map).placeGrass(1);
                    }
                }
            }
        }
    }

    public Vector2d getPosition(){
        return this.position;
    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
