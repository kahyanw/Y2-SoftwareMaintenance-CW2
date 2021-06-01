package com.neet.MapViewer.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
    public static final int TILESIZE = 16;
    private static int numCols;
    private static int numRows;

    public static int[][] mapMatrix;

    public static void loadMap(BufferedReader br){
        try {
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());

            mapMatrix = new int [numRows][numCols];

            String delims = "\\s+"; //omit space or tab
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    mapMatrix[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getTile(int row, int col) {
        return mapMatrix[row][col];
    }
    public static void setTile(int row, int col, int tile) { mapMatrix[col][row] = tile; }

    public static int isBlocked(int row, int col) {
        try {
            if (mapMatrix[row][col] >= 20) { //tiles that are >= 20 is a blocked tile
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return 1;
        }
    }

    public static int getNumRows() {
        return numRows;
    }

    public static int getNumCols() {
        return numCols;
    }
}
