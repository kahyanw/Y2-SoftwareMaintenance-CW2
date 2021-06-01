package com.neet.MapViewer.Main;

import java.io.*;

import com.neet.MapViewer.TileMap.TileMap;
import com.neet.MapViewer.Helper.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static Stage primaryStage;
    public static TileMap tileMap;

    public BorderPane rootLayout;
    public TilePane tilePanel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Launcher.primaryStage = primaryStage;
        Launcher.primaryStage.setTitle("Map Viewer");
        // Add a custom icon.
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/icons/icons8-diamond-48.png").toString()));
        Platform.setImplicitExit(false);
        initWindowFrame();

        primaryStage.setOnCloseRequest(event -> { Platform.setImplicitExit(true); });
    }

    public void initWindowFrame() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("newMap.map"));
            Map.loadMap(br);
        }
        catch (Exception e) {
            try {
                InputStream in = getClass().getResourceAsStream("/Maps/testmap.map");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                Map.loadMap(br);
            }
            catch (Exception e1) {

            }
        }


        tileMap = new TileMap();
        Img.loadImagesFiles("/Tilesets/testtileset.gif");
 
        try {
            //Initialize scene
            FXMLLoader loaderRootLayout = new FXMLLoader();
            loaderRootLayout.setLocation(Launcher.class.getResource("/com/neet/MapViewer/Main/MapViewWindow.fxml"));
            rootLayout = (BorderPane)loaderRootLayout.load();
            Scene scene = new Scene(rootLayout);

            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

            //Initialize Tile Panel
            FXMLLoader loaderTilePanel = new FXMLLoader();
            loaderTilePanel.setLocation(Launcher.class.getResource("/com/neet/MapViewer/TileMap/TileMapContainer.fxml"));
            tilePanel = (TilePane) loaderTilePanel.load();
            tilePanel.setPrefColumns(Map.getNumCols());
            tilePanel.setPrefRows(Map.getNumRows());
            tileMap.render();
            tilePanel.getChildren().add(tileMap.mapCanvas);

            rootLayout.setCenter(tilePanel);

        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}

