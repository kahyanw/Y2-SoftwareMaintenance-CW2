package com.neet.MapViewer.TileMap;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.neet.MapViewer.Main.Launcher;
import com.neet.MapViewer.Main.WindowController;
import com.neet.MapViewer.Helper.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Stack;

public class TileMapController {

    private static int xCursor;
    private static int yCursor;

    //initialized to default location
    public static int xAxe;
    public static int yAxe;
    public static int xBoat;
    public static int yBoat;

    public static Stack history;

    public void initialize() {
        history = new Stack();
        try {
            BufferedReader br = new BufferedReader(new FileReader("itemmap.txt"));
            xAxe = Integer.parseInt(br.readLine());
            yAxe = Integer.parseInt(br.readLine());
            xBoat = Integer.parseInt(br.readLine());
            yBoat = Integer.parseInt(br.readLine());
        }
        catch (Exception e) {
            yAxe = 26;
            xAxe = 37;
            yBoat = 12;
            xBoat = 4;
        }
    }

    public static int getxCursor() {
        return xCursor;
    }

    public static int getyCursor() {
        return yCursor;
    }

    @FXML
    public void showCoordinate(MouseEvent event) {
        System.out.println((int) event.getX() / 16);
        System.out.println((int) event.getY() / 16);
    }

    @FXML
    public void highlightCursor(MouseEvent event) {
        xCursor = (int) event.getX() / 16;
        yCursor = (int) event.getY() / 16;
        Launcher.tileMap.render();
    }
    
    @FXML
    public void setItems(MouseEvent event) {
    	xCursor = (int) event.getX() / 16;
        yCursor = (int) event.getY() / 16;
        
        if (WindowController.axeSet == 1 || WindowController.boatSet == 1) {
	        if (Map.isBlocked(yCursor, xCursor) == 1) {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	        	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/icons8-diamond-48.png"));
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Cannot place item on that tile!");
	            alert.showAndWait();
	            return;
	        }
        }
	        
    	if (WindowController.axeSet == 1) {
    	    ItemHistory hist = new ItemHistory("axe", xAxe, yAxe);
    	    history.push(hist);
    		xAxe = xCursor;
    		yAxe = yCursor;
    	}
    	else if (WindowController.boatSet == 1) {
            ItemHistory hist = new ItemHistory("boat", xBoat, yBoat);
            history.push(hist);
    		xBoat = xCursor;
    		yBoat = yCursor;
    	}
    	else if (WindowController.treeSet == 1){
             Map.setTile(xCursor,yCursor,20);
        }
    	else if (WindowController.deadtreeSet == 1) {
    	    Map.setTile(xCursor,yCursor, 21);
        }
    	else if (WindowController.grassSet == 1){
    	    Map.setTile(xCursor,yCursor, 1);
        }
    	else if (WindowController.waterSet == 1) {
    	    Map.setTile(xCursor, yCursor, 22);
        }
    	Launcher.tileMap.render();
    }

    
}
