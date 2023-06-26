package controller;

import java.util.HashMap;
import java.util.PriorityQueue;
import main.Event;
import main.EventListener;
import main.Main;
import model.Model;


/**
 *
 * @author josep
 */
class RodState implements Comparable<RodState> {
    public final int cx, cy, rot, nSteps; // Center x coord., center y coord & rotation
    public int heurValue;
    
    public RodState (int cx, int cy, int rot, int nSteps) {
        this.cx = cx;
        this.cy = cy;
        this.rot = rot;
        this.nSteps = nSteps;
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
    
    private void init () {
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        PriorityQueue<RodState> heap = new PriorityQueue<>();
        
        RodState parent = new RodState(1, 0, 0, 0);
        heap.add(parent);
        
        while (!heap.isEmpty()) {
            parent = heap.poll();
            
            
        }
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
