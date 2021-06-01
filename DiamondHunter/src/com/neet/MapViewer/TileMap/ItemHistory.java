package com.neet.MapViewer.TileMap;

public class ItemHistory {
    private String item;
    private int x;
    private int y;

    public ItemHistory(String item, int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
    }

    public String getItem() {
        return item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
