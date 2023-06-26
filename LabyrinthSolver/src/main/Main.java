
package main;

import model.Model;
import view.View;

/**
 *
 * @author josep
 */
public class Main implements EventListener {
    private View view;
    private Model model;
    
    public static void main(String[] args) {
        (new Main()).init();
    }
    
    @Override
    public void notify (Event e) {
        switch (e.eventType) {
            case VIEW:
                break;
            case MODEL:
                this.model.notify(e);
                break;
            case CONTROLLER:
                
        }
    }
    
    public void init() {
        this.view = new View(this);
        this.model = new Model(0, 0, 0);
    }
}
