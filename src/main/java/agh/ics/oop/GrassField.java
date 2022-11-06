package agh.ics.oop;

import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    //seed for testing
    private int seed = 12345;

    private final double limit;
    private Random randomGenerator;
    public GrassField(int countGrass){
        super(Integer.MAX_VALUE,Integer.MAX_VALUE);

        this.limit = sqrt(countGrass*10);
        this.randomGenerator = new Random(this.seed);

        placeGrass(countGrass);
    }

    private int randomNum(){
        return (int)(this.randomGenerator.nextDouble() * limit + 1);
    }

    public void placeGrass(int n){
        for (int i = 0; i < n; i++) {
            Vector2d newPosition;
            do{
                newPosition = new Vector2d(randomNum(), randomNum());
            }while (this.objectAt(newPosition) != null);
            elements.add(new Grass(newPosition));
        }
    }


    @Override
    public Vector2d[] printLimit() {
        int[] dynamicUpperRight = {0,0};
        int[] dynamicLowerLeft = {upperRight.x, upperRight.y};
        for (IMapElement curObj: elements) {
            Vector2d curPos = curObj.getPosition();
            comparePositions(curPos, dynamicUpperRight, dynamicLowerLeft);
        }
        return new Vector2d[]{new Vector2d(dynamicLowerLeft[0], dynamicLowerLeft[1]), new Vector2d(dynamicUpperRight[0], dynamicUpperRight[1])};
    }

    public boolean deleteGrass(Vector2d position){
        if(objectAt(position) instanceof Grass){
            elements.remove(objectAt(position));
            return true;
        }
        return false;
    }


    private void comparePositions(Vector2d curPos, int[] dynamicUpperRight, int[] dynamicLowerLeft){
        if(curPos.x > dynamicUpperRight[0]){
            dynamicUpperRight[0] = curPos.x;
        }
        if(curPos.y > dynamicUpperRight[1]){
            dynamicUpperRight[1] = curPos.y;
        }
        if(curPos.x < dynamicLowerLeft[0]){
            dynamicLowerLeft[0] = curPos.x;
        }
        if(curPos.y < dynamicLowerLeft[1]){
            dynamicLowerLeft[1] = curPos.y;
        }
    }


}

