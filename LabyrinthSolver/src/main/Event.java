
package main;

/**
 *
 * @author josep
 */

/**
 * The classes that extends from Event will implement a different constructor
 * for each of their sub event types.
 */
public class Event {
    public EventType eventType;
    
    public Event (EventType eventType) {
        this.eventType = eventType;
    }
}
