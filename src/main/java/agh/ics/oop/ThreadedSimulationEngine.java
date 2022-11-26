package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class ThreadedSimulationEngine implements IEngine, Runnable{

    private MoveDirection[] directions;
    private IWorldMap map;
    private int animalCount;
    private List<Animal> animals;
    private Integer moveDelay = 300;
    private IAnimalMoveObserver observer;

    public ThreadedSimulationEngine(IWorldMap map, Vector2d[] positions, IAnimalMoveObserver obs) throws IllegalArgumentException{
        this.animalCount = 0;
        this.animals = new LinkedList<Animal>();
        for ( Vector2d position: positions) {
            Animal currAnimal = new Animal(map, position);
            if(map.place(currAnimal)){
                animalCount += 1;
                animals.add(currAnimal);
            };
        }
        this.map = map;
        this.observer = obs;
    }

    public void setDirections(String[] directions) {
        this.directions = new OptionsParser().parse(directions);
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
}
