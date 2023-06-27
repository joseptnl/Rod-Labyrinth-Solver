
package view;

import main.Event;
import main.EventType;

/**
 *
 * @author josep
 */

public class ViewEvent extends Event {
    public ViewEventType viewEventType;
    public int result;

    // Event to send the result to view
    public ViewEvent(int result) {
        super(EventType.VIEW);      
        this.result = result;
        this.viewEventType = ViewEventType.SOLUTION;
    }
    
    static public enum ViewEventType {
        SOLUTION
    };
}

