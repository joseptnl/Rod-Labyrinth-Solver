
package main;

import view.View;

/**
 *
 * @author josep
 */
public class Main implements EventListener {
    private View view;
    
    public static void main(String[] args) {
        (new Main()).init();
    }
    
    @Override
    public void notify (Event e) {
        switch (e.eventType) {
            case VIEW:
                break;
            case MODEL:
                break;
            case CONTROLLER:
                
        }
    }
    
    public void init() {
        this.view = new View(this);
    }
}
