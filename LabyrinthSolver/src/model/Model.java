
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
    private int rodRotation; // 0 horizontal 1 vertical
    private int[] iniRodPos; // Initial rod pos
    
    private final int [] movementX = {0, 1, 0, -1};
    private final int [] movementY = {-1, 0, 1, 0};
    
    public Model (Main main, int initi, int initj, int initRot) {
        this.main = main;
        this.iniRodPos = new int[2];
        this.iniRodPos[0] = initi;
        this.iniRodPos[1] = initj;
        this.rodRotation = initRot; 
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
            if (rot == 0 && ((x + i < 0 || x + i >= labyrinth[0].length) || labyrinth[x+i][y] == WALL)) return false;
            if (rot == 1 && ((y + i < 0 || y + i >= labyrinth.length) || labyrinth[x][y+i] == WALL)) return false;
        }
        return true;
    }
    
    public boolean isSolution (int x, int y) {
        if ((x + 1 == labyrinth[0].length - 1 && y == labyrinth.length - 1) ||
            (x == labyrinth[0].length - 1 && y + 1 == labyrinth.length - 1)) 
            return true;
        else return false;
    }
    
    public int calculateHeuristic (int x, int y, int rot) {
        int xdiff, ydiff;
        if (rot == 1) {
            xdiff = x + 1 - labyrinth[0].length - 1;
            ydiff = y - labyrinth.length - 1;
        } else {
            xdiff = x - labyrinth[0].length - 1;
            ydiff = y + 1 - labyrinth.length - 1;
        }

        return xdiff + ydiff;
    }

    @Override
    public void notify(Event e) {
        ModelEvent event = (ModelEvent) e;
        switch (event.viewEventType) {
            case INIT:
                this.labyrinth = event.labyrinth;
                break;
        }
    }
}
