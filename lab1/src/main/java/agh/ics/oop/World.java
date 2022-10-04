package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
        System.out.println("System wystartował");
        Direction[] newArgs =  convertToDiraction(args);
        run(newArgs);
        System.out.println("System zakończył działanie");
    }
    static void run(Direction[] args){
        for (int i = 0; i < args.length; i++) {
            if(i > 0)
            {
                System.out.print(", ");
            }

            String message =switch (args[i]) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD ->  "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT ->  "Zwierzak skręca w lewo" ;
            };
            System.out.print(message);
        }
        System.out.print("\n");
    }

    static Direction[] convertToDiraction(String[] args){
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
