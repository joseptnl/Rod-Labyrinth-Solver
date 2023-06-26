
package model;

import main.Event;
import main.EventListener;
import main.Main;

/**
 *
 * @author josep
 */
public class Model implements EventListener {
    private Main main;
    
    static final int[] ROD_SIZE = {1, 3}; // Width and Lenght
    static final char WALL = '#';
    static final char EMPTY = '.';
    
    private char[][] labyrinth;
    
    private final int [] movementX = {0, 1, 0, -1};
    private final int [] movementY = {-1, 0, 1, 0};
    
    public Model (Main main) {
        this.main = main;
    }
    
    public int getMovementX (int i) {
        return this.movementX[i];
    }
    
    public int getMovementY (int i) {
        return this.movementY[i];
    }
    
    public int getNMovements () {
        return 4;
    }
    
    public boolean isAppliableMov (int x, int y, int rot) { 
        for (int i = -1; i < 2; i++) {
            if (rot == 0) {
                if (isOutOfBounds(x + i, y)) return false;
                if (labyrinth[y][x+i] == WALL) return false;
            } else {
                if (isOutOfBounds(x, y + i)) return false;
                if (labyrinth[y+i][x] == WALL) return false;
            }
        }
        return true;
    }
    
    public boolean isOutOfBounds (int x, int y) {
        return x < 0 || x == labyrinth[0].length || y < 0 || y == labyrinth.length;
    }
    
    public int calculateHeuristic (int x, int y, int rot) {
        int xdiff = 0, ydiff = 0;
        if (rot == 0) {
            xdiff = x + 1 - (labyrinth[0].length - 1);
            ydiff = y - (labyrinth.length - 1);
        } else {
            xdiff = x - (labyrinth[0].length - 1);
            ydiff = y + 1 - (labyrinth.length - 1);
        }

        return Math.abs(xdiff) + Math.abs(ydiff);
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        switch (event.viewEventType) {
            case INIT:
                this.labyrinth = new char[event.labyrinth.length][event.labyrinth[0].length];
                for (int i = 0; i < this.labyrinth.length; i++) {
                    for (int j = 0; j < this.labyrinth[0].length; j++) {
                        this.labyrinth[i][j] = event.labyrinth[i][j];
                    }
                }
                break;
        }
    }
}
