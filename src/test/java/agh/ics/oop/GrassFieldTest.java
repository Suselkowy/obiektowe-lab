package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    public List<Animal> testEngine(String[] args, IWorldMap map, Vector2d[] positions) throws IllegalArgumentException{
        MoveDirection[] directions = new OptionsParser().parse(args);
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        return engine.getAnimalsToTest();
    }

    @Test
    public void initialTest(){
        IWorldMap map = new GrassField(4);

        String[] input1 = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        List<Animal> ans = testEngine(input1,map, positions);

        assertEquals(ans.get(0).getPosition(), new Vector2d(2,-1));
        assertEquals(ans.get(0).toString(), "S");

        assertEquals(ans.get(1).getPosition(), new Vector2d(3,7));
        assertEquals(ans.get(1).toString(), "N");
    }

    @Test
    public  void getObjectTest(){
        // is animal returned prior to grass
        IWorldMap map = new GrassField(10);

        String[] input1 = new String[]{"f", "f", "r", "f"};
        Vector2d[] positions = { new Vector2d(2,2)};
        List<Animal> ans = testEngine(input1,map, positions);


        assertEquals(map.objectAt(new Vector2d(3,4)), ans.get(0));
        // Test below works only for version where animal dont eat grass
        //        //is grass returned
        //        IWorldMap map2 = new GrassField(10);
        //
        //        String[] input2 = new String[]{};
        //        Vector2d[] positions2 = { new Vector2d(2,2)};
        //        List<Animal> ans2 = testEngine(input1,map, positions);
        //
        //        assertTrue(map2.objectAt(new Vector2d(3,4)) instanceof Grass);
    }

    @Test
    public  void voidDynamicSizeTest(){
        IWorldMap map = new GrassField(10);

        String[] input1 = new String[]{"r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = { new Vector2d(2,2)};
        List<Animal> ans = testEngine(input1,map, positions);

        Vector2d[] printLimit = ((GrassField)map ).printLimit();
        assertEquals(printLimit[0], new Vector2d(1,1));
        assertEquals(printLimit[1], new Vector2d(17,10));

    }

    @Test
    public void exceptionTest(){
        IWorldMap map = new GrassField(10);

        String[] input1 = new String[]{ "r", "12wadwdasd",};
        Vector2d[] positions = { new Vector2d(2,2)};
        try{
            List<Animal> ans = testEngine(input1,map, positions);
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "12wadwdasd is not legal move specification");
        }


    }


}

