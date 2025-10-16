/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class Scoreboard
 * Name: mirzaa
 * Created 10/15/2025
 */
package coconuts;

/**
 * Observes hit events and maintains counts of coconuts destroyed and coconuts that hit the beach.
 */
public class Scoreboard implements Observer {
    private int coconutsDestroyed = 0;
    private int coconutsOnBeach = 0;

    @Override
    public void update(HitEvent hit) {
        IslandObject hitter = hit.getHittingObject();
        IslandObject target = hit.getHittableObject();

        // Coconut hits beach
        if (hitter.isFalling() && target.isGroundObject()) {
            System.out.println("Coconut hit the beach!");
            coconutsOnBeach++;
        }

        // Laser hits coconut
        else if (!hitter.isFalling() && target.isFalling()) {
            System.out.println("Laser hit the coconut!");

            coconutsDestroyed++;
        }
    }

    public int getCoconutsDestroyed() {
        return coconutsDestroyed;
    }

    public int getCoconutsOnBeach() {
        return coconutsOnBeach;
    }

}
