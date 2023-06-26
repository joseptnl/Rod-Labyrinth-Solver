
package main;

import controller.Controller;
import model.Model;
import view.View;

/**
 *
 * @author josep
 */
public class Main implements EventListener {
    private View view;
    private Model model;
    private Controller controller;
    
    public void init() {
        this.model = new Model(this);
        this.controller = new Controller(this);
        this.view = new View(this);
        
        view.start();
    }
    
    public Model getModel () {
        return this.model;
    }
    
    @Override
    public void notify (Event e) {
        switch (e.eventType) {
            case VIEW:
                this.view.notify(e);
                break;
            case MODEL:
                this.model.notify(e);
                break;
            case CONTROLLER:
                this.controller.notify(e);
                break;
        }
    }
    
    public static void main(String[] args) {
        (new Main()).init();
    }
}
