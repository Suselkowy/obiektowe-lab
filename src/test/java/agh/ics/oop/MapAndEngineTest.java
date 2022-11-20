package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MapAndEngineTest {

    public List<Animal> testEngine(String[] args,IWorldMap map, Vector2d[] positions){
        MoveDirection[] directions = new OptionsParser().parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        return ((SimulationEngine) engine).getAnimalsToTest();
    }

    @Test
    public void initialTest(){
        IWorldMap map = new RectangularMap(10, 5);

        String[] input1 = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        List<Animal> ans = testEngine(input1,map, positions);

        assertEquals(ans.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(ans.get(0).toString(), "S");

        assertEquals(ans.get(1).getPosition(), new Vector2d(3,5));
        assertEquals(ans.get(1).toString(), "N");
    }

    @Test
    public void cornerTest(){
        IWorldMap map = new RectangularMap(10, 5);

        String[] input1 = new String[]{"l", "f", "f", "r", "l", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "r", "l", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3), new Vector2d(3,3), new Vector2d(3,2) };
        List<Animal> ans = testEngine(input1,map, positions);

        assertEquals(ans.get(0).getPosition(), new Vector2d(0,0));
        assertEquals(ans.get(0).toString(), "W");

        assertEquals(ans.get(1).getPosition(), new Vector2d(0,5));
        assertEquals(ans.get(1).toString(), "W");

        assertEquals(ans.get(2).getPosition(), new Vector2d(10,5));
        assertEquals(ans.get(2).toString(), "E");

        assertEquals(ans.get(3).getPosition(), new Vector2d(10,0));
        assertEquals(ans.get(3).toString(), "E");
    }

    @Test
    public void placeTest(){
        IWorldMap map = new RectangularMap(4, 4);

        String[] input1 = new String[]{"f", "f"};
        Vector2d[] positions = { new Vector2d(2,2)};
        List<Animal> ans = testEngine(input1,map, positions);

        assertEquals(ans.get(0).getPosition(), new Vector2d(2,4));
        assertEquals(ans.get(0).toString(), "N");
    }
    @Test
    public void placeTestException(){
        IWorldMap map = new RectangularMap(4, 4);

        String[] input1 = new String[]{"f", "f"};
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(5,5) };
        try{
            List<Animal> ans = testEngine(input1,map, positions);
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Cannot place animal on place (5,5)- out of bounds");
        }


        String[] input2 = new String[]{"f", "f"};
        Vector2d[] position2 = { new Vector2d(2,2), new Vector2d(2,2) };
        try{
            List<Animal> ans = testEngine(input2,map, position2);
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Cannot place animal on place (2,2)- field already occupied");
        }
    }

    @Test
    public void objAtTest(){
        IWorldMap map = new RectangularMap(4, 4);

        String[] input1 = new String[]{"f", "f"};
        Vector2d[] positions = { new Vector2d(2,2)};
        List<Animal> ans = testEngine(input1,map, positions);

        assertEquals(map.objectAt(new Vector2d(2,4)), ans.get(0));
        assertNull(map.objectAt(new Vector2d(2, 2)));
    }

}
