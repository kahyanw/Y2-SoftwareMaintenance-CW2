package com.neet.MapViewer.Helper;

import javafx.scene.image.Image;
import com.neet.MapViewer.Helper.Map;
import com.neet.MapViewer.TileMap.TileMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Img {
	
    public static Image tileSet;
    private static int numTilesAcross;
    public static final int TILESIZE = 16;

    /**
     * This method load image of each tile from resource file.
     * @param tilesetImage contains images of each tile
     */
    public static void loadImagesFiles(String tilesetImage) {
        try {
            tileSet = new Image(Img.class.getResourceAsStream(tilesetImage));
            numTilesAcross = (int)tileSet.getWidth() / TILESIZE;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getWidth() {
    	return 0;
    }
    
    public static int getNumTilesAcross() {
    	return numTilesAcross;
    }
}
