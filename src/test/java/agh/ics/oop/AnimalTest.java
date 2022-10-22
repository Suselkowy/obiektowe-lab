package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    public Animal testAnimal(String[] args){
        Animal animal = new Animal();
        MoveDirection[] moveDirections = OptionsParser.parse(args);
        for (MoveDirection direction: moveDirections) {
            animal.move(direction);
        }
        return animal;
    }

    @Test
    public void orientationTest(){
        String[] input1 = new String[]{"f","l","l","l","l","b"};
        String[] input2 = new String[]{"f","r","r","r","r","b"};
        String[] input3 = new String[]{"f","r","r","l","l","b"};

        String[] input4 = new String[]{"l", "f"};
        String[] input5 = new String[]{"l", "b", "l", "f", "l", "f", "f", "r", "f"};

        assertTrue(testAnimal(input1).isAt(new Vector2d(2,2)));
        assertTrue(testAnimal(input2).isAt(new Vector2d(2,2)));
        assertTrue(testAnimal(input3).isAt(new Vector2d(2,2)));

        assertTrue(testAnimal(input4).isAt(new Vector2d(1,2)));
        assertTrue(testAnimal(input5).isAt(new Vector2d(4, 0)));

    }

    @Test
    public void positionTest(){
        String[] input1 = new String[]{"f","f","b","b","f","f","r","r","f","f"};
        String[] input2 = new String[]{"f","r","f","r", "b"};

        //edges check (1- top right 2- top left 3 - bottom left 4- bottom right) with boundaries check
        String[] input3 = new String[]{"f","f","f","f","f","r","f","f","f","f","f"};
        String[] input4 = new String[]{"f","f","f","f","f","l","f","f","f","f","f"};
        String[] input5 = new String[]{"r","r","f","f","f","f","f","r","f","f","f","f","f"};
        String[] input6 = new String[]{"r","r","f","f","f","f","f","l","f","f","f","f","f"};

        assertTrue( testAnimal(input1).isAt(new Vector2d(2,2)));
        assertTrue( testAnimal(input2).isAt(new Vector2d(3,4)));

        assertTrue( testAnimal(input3).isAt(new Vector2d(4,4)));
        assertTrue( testAnimal(input4).isAt(new Vector2d(0,4)));
        assertTrue( testAnimal(input5).isAt(new Vector2d(0,0)));
        assertTrue( testAnimal(input6).isAt(new Vector2d(4,0)));
    }

    @Test
    public void parserTest(){
        String[] input1 = new String[]{"f", "asdas","_","awd","bl","d","c","n","asdawd","-_++=%$&#4$%#^29dksoa", "f"};

        assertTrue( testAnimal(input1).isAt(new Vector2d(2,4)));
    }

}
