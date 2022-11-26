package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends VBox{
    public GuiElementBox(IMapElement element){
        try{
            Image image = new Image(new FileInputStream(element.getResourcePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            Label label = new Label("Trawa");
            if(element instanceof Animal){
                Vector2d position = element.getPosition();
                label = new Label("(" +position.x + "," + position.y + ")");
            }

            this.getChildren().addAll( imageView, label);
            this.setAlignment(Pos.CENTER);
        }catch (FileNotFoundException e){
            System.out.println("brak pliku");
        }
    }

}
