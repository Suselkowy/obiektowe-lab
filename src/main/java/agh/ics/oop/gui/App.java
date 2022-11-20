package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    IWorldMap map;

    @Override
    public void init() throws Exception {
        try{
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
//            System.out.print(map);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        Vector2d[] limits = ((AbstractWorldMap) map).printLimit();

        int rowCount = limits[1].y-limits[0].y+2;
        int columnCount = limits[1].x-limits[0].x+2;
        int width = 20;
        int height = 20;

        for (int i = 0; i < rowCount; i++) {
            grid.getRowConstraints().add(new RowConstraints(height));
        }
        for (int i = 0; i < columnCount; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(width));
        }

        for (int i = limits[0].x; i < limits[1].x + 2; i++) {
            for (int j = limits[0].y; j < limits[1].y + 2; j++) {
                Label label =  new Label("");

                if(j == limits[0].y && i == limits[0].x){
                    label = new Label("y/x");
                }
                else if( j == limits[0].y){
                 label = new Label(Integer.toString(i-1));
                }
                else if(i == limits[0].x){
                    label = new Label(Integer.toString(limits[1].y + limits[0].y +1 - j));
                }

                Vector2d pos = new Vector2d(i-1, limits[1].y + limits[0].y +1 -j);
                if(map.isOccupied(pos)){
                    label = new Label(map.objectAt(pos).toString());
                }
                GridPane.setHalignment(label, HPos.CENTER);
                grid.add(label, i-limits[0].x, j-limits[0].y);
            }
        }
        Scene scene = new Scene(grid, width*columnCount, height*rowCount);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
