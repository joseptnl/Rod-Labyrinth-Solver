
package view;

import main.Event;
import main.EventType;

/**
 *
 * @author josep
 */

public class ViewEvent extends Event {
    public ViewEventType viewEventType;

    public ViewEvent(boolean state) {
        super(EventType.VIEW);
        
        
        this.viewEventType = state ? ViewEventType.START : ViewEventType.FINISH;
    }
    
    static public enum ViewEventType {
        START,
        FINISH,
        CONSULT_MODEL
    };
}

