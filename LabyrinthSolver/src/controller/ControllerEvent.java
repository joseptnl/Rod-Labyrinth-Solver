
package controller;

import model.*;
import main.Event;
import main.EventType;

/**
 *
 * @author josep
 */

public class ControllerEvent extends Event {
    public ControllerEventType controllerEventType;

    // Event to start the calculation
    public ControllerEvent() {
        super(EventType.CONTROLLER);
        this.controllerEventType = ControllerEventType.START_TO_SOLVE;
    }
    
    enum ControllerEventType {
        START_TO_SOLVE
    };
}

