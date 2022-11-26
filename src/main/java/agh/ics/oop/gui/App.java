package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observer;

public class App extends Application implements IAnimalMoveObserver {

    IWorldMap map;
    ThreadedSimulationEngine engine;
    GridPane grid = new GridPane();

    @Override
    public void init() throws Exception {
        try{
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

            this.engine = new ThreadedSimulationEngine(map, positions, this);

//            System.out.print(map);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateMap(){
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);

            Vector2d[] limits = ((AbstractWorldMap) map).printLimit();

            int rowCount = limits[1].y-limits[0].y+2;
            int columnCount = limits[1].x-limits[0].x+2;
            int width = 35;
            int height = 35;
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
                        Object elementAtPos = map.objectAt(pos);
                        if (elementAtPos instanceof Grass){
                            GridPane.setHalignment(label, HPos.CENTER);
                            grid.add(new GuiElementBox((Grass) elementAtPos), i-limits[0].x, j-limits[0].y);
                        }else{
                            GridPane.setHalignment(label, HPos.CENTER);
                            grid.add(new GuiElementBox((Animal) elementAtPos), i-limits[0].x, j-limits[0].y);
//                        label = new Label(elementAtPos.toString());
//                        GridPane.setHalignment(label, HPos.CENTER);
//                        grid.add(label, i-limits[0].x, j-limits[0].y);
                        }

                    }else{
                        GridPane.setHalignment(label, HPos.CENTER);
                        grid.add(label, i-limits[0].x, j-limits[0].y);
                    }

                }
            }

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        grid.setGridLinesVisible(true);
        int width = 35;
        int height = 35;
        Button startBtn = new Button("Start");
        TextField params = new TextField();
        startBtn.setOnAction(e ->{
            this.engine.setDirections(params.getText().split(" "));
            params.clear();
            new Thread(engine).start();
        });
        HBox nav =  new HBox();
        nav.getChildren().addAll(startBtn, params);
        VBox main = new VBox();
        main.getChildren().addAll(grid,nav);
        Scene scene = new Scene(main, width*20, height*20);
        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.runLater(this::updateMap);
    }

    @Override
    public void positionChanged() {
        Platform.runLater(this::updateMap);
    }
}
