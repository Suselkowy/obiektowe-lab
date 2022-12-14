package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.Arrays;

public class World {
    public static void main(String[] args){
            Application.launch(App.class, args);
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
