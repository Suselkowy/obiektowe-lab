package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        System.out.println("Start");
        Direction[] newArgs =  convertToDirection(args);
        run(newArgs);
        System.out.println("Stop");
        //Vector2d
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println(position1.upperRight(position1));
        //MapDirection
        MapDirection d1 = MapDirection.NORTH;
        MapDirection d2 = MapDirection.EAST;
        MapDirection d3 = MapDirection.SOUTH;
        MapDirection d4 = MapDirection.WEST;

        System.out.println(d1.next());
        System.out.println(d2.previous());
        System.out.println(d3);
        System.out.println(d4.toUnitVector());

    }
    static void run(Direction[] args){
        for(Direction arg : args){
            String message = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD ->  "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT ->  "Zwierzak skręca w lewo" ;
            };
            System.out.print(message +"\n");
        }
    }

    static Direction[] convertToDirection(String[] args){
        Direction[] directions= new Direction[args.length];
        int i = 0;
        for (String arg:args) {
            switch (arg) {
                case "f" -> directions[i] = Direction.FORWARD;
                case "b" -> directions[i] = Direction.BACKWARD;
                case "r" -> directions[i] = Direction.RIGHT;
                case "l" -> directions[i] = Direction.LEFT;
                default -> --i;
            }
            ++i;
        }
        return Arrays.copyOfRange(directions, 0, i);
        }
    }
