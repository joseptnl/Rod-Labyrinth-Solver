package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import main.Event;
import main.EventListener;
import main.Main;
import model.Model;
import view.ViewEvent;


/**
 *
 * @author josep
 */

/**
 * This class represents a state of the rod during its pathwalk.
 */
class RodState implements Comparable<RodState> {
    public final int cx, cy, rot, nSteps; // Center x coord., center y coord & rotation
    public int heurValue;
    public String key; // For the hash
    
    public RodState (int cx, int cy, int rot, int nSteps) {
        this.cx = cx;
        this.cy = cy;
        this.rot = rot;
        this.nSteps = nSteps;
        key = cx+"-"+cy+"-"+rot;
    }
    
    @Override
    public int compareTo(RodState state) {
        // f(x) > f'(x)?
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
        
        Set<String> closed = new HashSet<String> ();
        PriorityQueue<RodState> opened = new PriorityQueue<>();
        
        // Create initial state at up-left corner horizontaly
        parent = new RodState(1, 0, 0, 0);
        opened.add(parent);
        
        while (!opened.isEmpty()) {
            parent = opened.poll();
            closed.add(parent.key);
            
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
                
                if (closed.contains(son.key)) continue;

                // Check solution
                if (son.heurValue == 0) {
                    // Notify the problem is finished
                    main.notify(new ViewEvent(son.nSteps));
                    return;
                }
                
                opened.add(son);
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
                
                if (closed.contains(son.key)) continue;

                opened.add(son);
            }
        }
        main.notify(new ViewEvent(-1));
    }

    @Override
    public void notify(Event e) {
        ControllerEvent event = (ControllerEvent) e;
        switch (event.controllerEventType) {
            case START_TO_SOLVE: // Event triggered by View
                init();
                break;
        }
    }
    
}
