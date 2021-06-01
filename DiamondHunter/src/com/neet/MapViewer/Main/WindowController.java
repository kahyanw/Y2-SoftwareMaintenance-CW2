package com.neet.MapViewer.Main;

import com.neet.DiamondHunter.Main.Game;
import com.neet.MapViewer.Helper.Map;
import com.neet.MapViewer.TileMap.*;


import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class WindowController {

    public static int axeSet = 0;
    public static int boatSet = 0;
    public static int treeSet = 0;
    public static int grassSet = 0;
    public static int waterSet = 0;
    public static int deadtreeSet = 0;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void exit(ActionEvent event) {
        Platform.setImplicitExit(true);
        Launcher.primaryStage.hide();
    }

    @FXML
    void playGame(MouseEvent event) {
        Launcher.primaryStage.hide();
        Game.main(null);
    }

    @FXML
    void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/icons8-diamond-48.png"));
        alert.setTitle("About");
        alert.setHeaderText("About Us");
        alert.setContentText("This is our CourseWork 2 for the subject Software Maintenance.\n"
                + "Diamond Hunter Map Viewer (v0.2.1)\n"
                + "Developed By: \n"
                + "Gwee Kent Yuan (20103627) \n"
                + "Wong Kah Yan (20128822)\n"
                + "Lee Ka Shing (20129070) \n"
                + "Chloe San Hue Tung (20118169)\n"
        );
        alert.showAndWait();
        alert.setOnCloseRequest(event -> {alert.close();});
    }

    @FXML
    void undoItemPlacement() {
        axeSet = 0;
        boatSet = 0;
        if (!TileMapController.history.empty()) {
            ItemHistory hist = (ItemHistory) TileMapController.history.pop();
            if (hist.getItem() == "boat") {
                TileMapController.xBoat = hist.getX();
                TileMapController.yBoat = hist.getY();
                Launcher.tileMap.render();
            }
            else {
                TileMapController.xAxe = hist.getX();
                TileMapController.yAxe = hist.getY();
                Launcher.tileMap.render();
            }
        }

    }

    @FXML
    void saveItemLocation() {
        int xAxe = TileMapController.xAxe;
        int yAxe = TileMapController.yAxe;
        int xBoat = TileMapController.xBoat;
        int yBoat = TileMapController.yBoat;
        try {
            PrintWriter writer = new PrintWriter("itemmap.txt");
            writer.println(xAxe);
            writer.println(yAxe);
            writer.println(xBoat);
            writer.println(yBoat);
            writer.close();

            // save to new map
            PrintWriter mapWriter = new PrintWriter("newMap.map");
            mapWriter.println(Map.getNumRows());
            mapWriter.println(Map.getNumCols());
            for (int col=0; col<Map.getNumCols(); col++) {
                for (int row=0; row<Map.getNumRows(); row++) {
                    mapWriter.print(Map.mapMatrix[col][row]+ " ");
                }
                mapWriter.print("\n");
            }
            mapWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/icons8-diamond-48.png"));
        alert.setTitle("Save");
        alert.setHeaderText(null);
        alert.setContentText("Changes have been saved.");
        alert.showAndWait();
    }

    @FXML
    void showInstruction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/icons8-diamond-48.png"));
        DialogPane dialogPane = alert.getDialogPane();
    	dialogPane.getStylesheets().add(getClass().getResource("/icons/icons8-high-importance-48.png").toExternalForm());
        alert.setTitle("Instructions");
        alert.setHeaderText("Instructions of Map Viewer");
        alert.setContentText("1. Simply just click on the Boat and Axe to place it on the map.\n"
                + "2. Add trees, grass or other tile types to the map.\n"
        		+ "3. Click on the Undo button to undo changes.\n"
                + "4. Click on the Save button to save changes.\n"
        		+ "5. Press the Play button to play the game with the new positions of axe and boat.\n"
                );
        alert.showAndWait();
        alert.setOnCloseRequest(event -> {alert.close();});
    }

    @FXML
    public void resetItemLocation() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/icons8-diamond-48.png"));
    	alert.setTitle("Confirmation");
    	alert.setHeaderText("Reset cannot be undone, all your changes will be removed.");
    	alert.setContentText("Are you sure to proceed?");
    	alert.getDialogPane().setGraphic(new ImageView("/icons/icons8-high-importance-48.png"));
    	DialogPane dialogPane = alert.getDialogPane();
    	dialogPane.getStylesheets().add(getClass().getResource("/icons/icons8-high-importance-48.png").toExternalForm());
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
            try {
                File file = new File("itemmap.txt");
                File mapFile = new File("newMap.map");
                file.delete();
                mapFile.delete();
            }
            catch (Exception e) {

            }
			InputStream in = getClass().getResourceAsStream("/Maps/testmap.map");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
            Map.loadMap(br);

            TileMapController.yAxe = 26;
            TileMapController.xAxe = 37;
            TileMapController.yBoat = 12;
            TileMapController.xBoat = 4;
            
            Launcher.tileMap.render();
            while (!TileMapController.history.empty()) {
                TileMapController.history.pop();
            }
    	} 
    	else if (result.get() == ButtonType.CANCEL) {
    		
    	}
    }

    @FXML
    void initialize() {

    }
    
    @FXML
    public void addAxe() {
    	axeSet = 1;
        boatSet = treeSet = grassSet = waterSet = deadtreeSet = 0;;
    	Launcher.tileMap.render();
    }

    @FXML
    public void addBoat() {
    	boatSet = 1;
    	axeSet = treeSet = grassSet = waterSet = deadtreeSet = 0;
        Launcher.tileMap.render();
    }

    @FXML
    public void addTree(){
        treeSet = 1;
        axeSet = boatSet = grassSet = waterSet = deadtreeSet = 0;
    }

    @FXML
    public void addDeadTree(){
        deadtreeSet = 1;
        axeSet = boatSet = grassSet = treeSet = waterSet = 0;
    }

    @FXML
    public void addGrass(){
        grassSet = 1;
        axeSet = boatSet = treeSet = waterSet = deadtreeSet = 0;
    }

    @FXML
    public void addWater(){
        waterSet = 1;
        axeSet = boatSet = grassSet = treeSet = deadtreeSet = 0;
    }

}


