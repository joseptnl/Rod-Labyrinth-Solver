package controller;

import java.util.HashMap;
import java.util.PriorityQueue;
import main.Event;
import main.EventListener;
import main.Main;
import model.Model;
import view.ViewEvent;


/**
 *
 * @author josep
 */
class RodState implements Comparable<RodState> {
    public final int cx, cy, rot, nSteps; // Center x coord., center y coord & rotation
    public int heurValue;
    public String key;
    
    public RodState (int cx, int cy, int rot, int nSteps) {
        this.cx = cx;
        this.cy = cy;
        this.rot = rot;
        this.nSteps = nSteps;
        key = cx+"-"+cy+"-"+rot;
    }
    
    @Override
    public int compareTo(RodState state) {
        return (this.nSteps + this.heurValue) > (state.nSteps + state.heurValue) ? 1 : -1;
    }
}

public class Controller implements EventListener {
    private Main main;
    private Model model;
    
    public Controller (Main main) {
        this.main = main;
        this.model = main.getModel();
    }
    
    /**
     * Function to execute the A* algorithm for path finding.
    */
    private void init () {
        int movx, movy;
        RodState parent, son;
        
        HashMap<String, Integer> closed = new HashMap<String, Integer>();
        PriorityQueue<RodState> opened = new PriorityQueue<>();
        
        // Create initial state at up-left corner
        parent = new RodState(1, 0, 0, 0);
        opened.add(parent);
        
        while (!opened.isEmpty()) {
            parent = opened.poll();
            
            // Generate offspring
            // Try all movements
            for (int m = 0; m < model.getNMovements(); m++) {
                // Get movement
                movx = model.getMovementX(m);
                movy = model.getMovementY(m);

                movx += parent.cx;
                movy += parent.cy;

                if (!model.isAppliableMov(movx, movy, parent.rot)) continue;

                // Create son
                son = new RodState(
                        movx,
                        movy,
                        parent.rot,
                        parent.nSteps + 1
                );
                son.heurValue = model.calculateHeuristic(son.cx, son.cy, son.rot);

                // Check solution
                if (son.heurValue == 0) {
                    // Notify the problem is finished
                    main.notify(new ViewEvent(son.nSteps));
                    return;
                }
                
                // If son already exists is updated in the hash and put in the 
                // queue if it faster to get there
                if (closed.containsKey(son.key)) {
                    if (son.nSteps < closed.get(son.key)) {
                        closed.replace(son.key, son.nSteps);
                        opened.add(son);
                    }
                // If son doesn't will be added to the strutures
                } else {
                    closed.put(son.key, son.nSteps);
                    opened.add(son);
                }
            }
            // Try rotation
            int rotation = parent.rot == 0 ? 1 : 0;
            if (model.isAppliableMov(parent.cx, parent.cy, rotation)) {
                // Create son
                son = new RodState(
                        parent.cx,
                        parent.cy,
                        rotation,
                        parent.nSteps + 1
                );
                son.heurValue = model.calculateHeuristic(son.cx, son.cy, son.rot);
                
                // If son already exists is updated in the hash and put in the 
                // queue if it faster to get there
                if (closed.containsKey(son.key)) {
                    if (son.nSteps < closed.get(son.key)) {
                        closed.replace(son.key, son.nSteps);
                        opened.add(son);
                    }
                // If son doesn't will be added to the strutures
                } else {
                    closed.put(son.key, son.nSteps);
                    opened.add(son);
                }
            }
        }
        main.notify(new ViewEvent(-1));
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        switch (event.controllerEventType) {
            case START_TO_SOLVE:
                init();
                break;
        }
    }
    
}
