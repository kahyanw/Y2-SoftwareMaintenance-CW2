package com.neet.MapViewer.TileMap;
 
import javafx.scene.canvas.Canvas;
import com.neet.MapViewer.Helper.*;
import com.neet.MapViewer.Main.WindowController;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class TileMap {
    private int[][] tileType;
    private int TILESIZE = Map.TILESIZE;
    
    private Image itemSprite;
    private Image diamondSprite;
    
    public Canvas mapCanvas;

    public TileMap() {
    	this.itemSprite = new Image("/Sprites/items.gif");
    	this.diamondSprite = new Image("/Sprites/diamond.gif");
    	this.mapCanvas = new Canvas(640, 640); //640 because there are 40 tile with tile size 16;
    }
    
    // initialize canvas for the tile map.
    public void render(){
        drawMap();
        drawItems();
        drawDiamonds();
        drawCursorHighlight(TileMapController.getxCursor(), TileMapController.getyCursor());
    }
 
    public void drawMap() {
        tileType = new int[Map.getNumRows()][Map.getNumCols()];
 
        for (int row = 0; row < Map.getNumRows(); row++) {
            for (int col = 0; col < Map.getNumCols(); col++) {
                if (Map.getTile(row, col) == 0) continue;
 
                int r = Map.getTile(row, col) / Img.getNumTilesAcross(); //if r == 1, tile is blocked
                int c = Map.getTile(row, col) % Img.getNumTilesAcross(); //row index of the Map starts at c == 0
 
                if (r == 0) {
                    mapCanvas.getGraphicsContext2D().drawImage(
                            Img.tileSet, c * Img.TILESIZE, 0, Img.TILESIZE, Img.TILESIZE,
                            col * Img.TILESIZE, row * Img.TILESIZE, Img.TILESIZE, Img.TILESIZE);
                    tileType[row][col] = 0;
                }
                else {
                    mapCanvas.getGraphicsContext2D().drawImage(
                            Img.tileSet, c * Img.TILESIZE, Img.TILESIZE, Img.TILESIZE, Img.TILESIZE,
                            col * Img.TILESIZE, row * Img.TILESIZE, Img.TILESIZE, Img.TILESIZE);
                    tileType[row][col] = 1;
                }
            }
        }
    }

    public void drawCursorHighlight(int xCo, int yCo) {
    	if (Map.isBlocked(yCo, xCo) == 1) {
    		mapCanvas.getGraphicsContext2D().setStroke(Color.CORAL);	
    	}
    	else {
    		mapCanvas.getGraphicsContext2D().setStroke(Color.BLUE);
    	}
        mapCanvas.getGraphicsContext2D().strokeRect(xCo * 16, yCo * 16, 16, 16);
    }
    
    public void drawItems() {
    	 //Draw the boat
        mapCanvas.getGraphicsContext2D().drawImage(itemSprite, 0, 16, TILESIZE, TILESIZE,
                (TileMapController.xBoat * TILESIZE), (TileMapController.yBoat * TILESIZE), TILESIZE, TILESIZE);

        //Draw the axe
        mapCanvas.getGraphicsContext2D().drawImage(itemSprite, 16, 16, TILESIZE, TILESIZE,
                (TileMapController.xAxe * TILESIZE), (TileMapController.yAxe * TILESIZE), TILESIZE, TILESIZE);

        // Highlight location of item placed
        mapCanvas.getGraphicsContext2D().setStroke(Color.BLACK);
        if (WindowController.boatSet == 1) {
        	mapCanvas.getGraphicsContext2D().strokeRect(TileMapController.xBoat * TILESIZE, TileMapController.yBoat * TILESIZE, 16, 16);

        }
        if (WindowController.axeSet == 1) {
        	mapCanvas.getGraphicsContext2D().strokeRect(TileMapController.xAxe * TILESIZE, TileMapController.yAxe * TILESIZE, 16, 16);
        }
    }
    
    public void drawDiamonds() {
        //Draw the diamonds
    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (20 * TILESIZE), (20 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (36 * TILESIZE), (12 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (4 * TILESIZE), (28 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (34 * TILESIZE), (4 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (19 * TILESIZE), (28 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (26 * TILESIZE), (35 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (36 * TILESIZE), (38 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (28 * TILESIZE), (27 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (30 * TILESIZE), (20 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (25 * TILESIZE), (14 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (21 * TILESIZE), (4 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (14 * TILESIZE), (9 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (3 * TILESIZE), (4 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (14 * TILESIZE), (20 * TILESIZE), TILESIZE, TILESIZE);

    	mapCanvas.getGraphicsContext2D().drawImage(diamondSprite, 0, 0, TILESIZE, TILESIZE,
                (20 * TILESIZE), (13 * TILESIZE), TILESIZE, TILESIZE);
    }
}