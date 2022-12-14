package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class SimulationEngine implements IEngine, Runnable{

    private MoveDirection[] directions;
    private IWorldMap map;
    private int animalCount;
    private List<Animal> animals;
    private Integer moveDelay = 300;
    private IAnimalMoveObserver observer;

    public SimulationEngine(MoveDirection[] directions,IWorldMap map, Vector2d[] positions, IAnimalMoveObserver obs) throws IllegalArgumentException{
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
        this.observer = obs;
    }

    @Override
    public void run() {
        int counter = 0;
        for (MoveDirection direction : directions) {
            animals.get(counter).move(direction);
//            System.out.println(counter+ ": " + animals.get(counter).getPosition() + " " + animals.toString());
            counter = (counter + 1) % animalCount;
            observer.positionChanged();
            try{
                Thread.sleep(moveDelay);
            }catch (Exception e){
                System.out.println("błąd w silniku!");
            }

        }
    }

    // Only for testing puroposes
    public List<Animal> getAnimalsToTest(){
        return animals;
    }
}
