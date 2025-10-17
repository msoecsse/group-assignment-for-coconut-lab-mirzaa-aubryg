/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class HitEvent
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;



import java.util.ArrayList;
import java.util.Collection;


// This is a domain class; do not introduce JavaFX or other GUI components here
/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class HitEvent Purpose: An abstraction of all objects that can be hit by another object
 *  This captures the Subject side of the Observer pattern; observers of the hit event will take action
 *    to process that event
 *
 * @editor aubryg
 * @version edited on 10/10/2025 12:13 PM
 */
public class HitEvent implements Subject {
    private final Collection<Observer> observers = new ArrayList<>();
    private HittableIslandObject hittable;
    private IslandObject hitting;
    public HitEvent() {}
    public HitEvent(HittableIslandObject hittable, IslandObject hitting) {
        this.hittable = hittable;
        this.hitting = hitting;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyAll(HitEvent hit) {
        for (Observer o : observers) {
            o.update(hit);
        }
    }
    public HittableIslandObject getHittableObject() {
        return hittable;
    }
    public IslandObject getHittingObject() {
        return hitting;
    }
}
