package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private MoveDirection[] directions;
    private IWorldMap map;
    private int animalCount;
    private List<Animal> animals;

    public SimulationEngine(MoveDirection[] directions,IWorldMap map, Vector2d[] positions) throws IllegalArgumentException{
        this.animalCount = 0;
        this.animals = new LinkedList<Animal>();
        for ( Vector2d position: positions) {
            Animal currAnimal = new Animal(map, position);
            if(map.place(currAnimal)){
                animalCount += 1;
                animals.add(currAnimal);
            };
        }
        this.directions = directions;
        this.map = map;

    }

    @Override
    public void run() {
        int counter = 0;
        for (MoveDirection direction : directions) {
            animals.get(counter).move(direction);
//            System.out.println(counter+ ": " + animals.get(counter).getPosition() + " " + animals.toString());
            counter = (counter + 1) % animalCount;
        }
    }

    // Only for testing puroposes
    public List<Animal> getAnimalsToTest(){
        return animals;
    }
}
