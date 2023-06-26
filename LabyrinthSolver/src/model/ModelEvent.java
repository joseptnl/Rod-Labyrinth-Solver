
package model;

import main.Event;
import main.EventType;

/**
 *
 * @author josep
 */

public class ModelEvent extends Event {
    public ModelEventType viewEventType;
    
    public char[][] labyrinth;

    // Event to init model
    public ModelEvent(char[][] labyrinth) {
        super(EventType.MODEL);
        this.viewEventType = ModelEventType.INIT;
        this.labyrinth = labyrinth;
    }
    
    enum ModelEventType {
        INIT
    };
}

