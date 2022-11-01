package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private final int width;
    private final int height;


    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    private List<Animal> animals;
    private MapVisualizer mapVisualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new LinkedList<Animal>();
        this.mapVisualizer = new MapVisualizer(this);

        this.upperRight = new Vector2d(width,height);
        this.lowerLeft = new Vector2d(0,0);


    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.precedes(position) && upperRight.follows(position) && !isOccupied(position);

    }

    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition())){
            return false;
        }
        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal curAnimal: animals){
            if (curAnimal.isAt(position)){
                return true;
            };
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal curAnimal: animals){
            if (curAnimal.isAt(position)){
                return curAnimal;
            };
        }
        return null;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

}
