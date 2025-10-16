/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class Scoreboard
 * Name: mirzaa and aubryg
 * Created 10/15/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class Scoreboard Purpose: Observes hit events and maintains
 *          counts of coconuts destroyed and coconuts that hit the beach
 *
 * @author mirzaa
 * @version created on 10/12/2025 1:41 PM
 */
public class Scoreboard implements Observer {
    private int coconutsDestroyed = 0;
    private int coconutsOnBeach = 0;
    private final OhCoconutsGameManager gameManager;
    public Scoreboard(OhCoconutsGameManager gameManager) {
        this.gameManager = gameManager;
    }
    @Override
    public void update(HitEvent hit) {
        IslandObject hitter = hit.getHittingObject();
        IslandObject target = hit.getHittableObject();

        // Coconut hits beach
        if (hitter.isFalling() && target.isGroundObject()) {
            coconutsOnBeach++;
           gameManager.coconutMiss(coconutsOnBeach);
        }

        // Laser hits coconut
        else if (!hitter.isFalling() && target.isFalling()) {
            coconutsDestroyed++;
            gameManager.coconutHit(coconutsDestroyed);


        }
    }

    public int getCoconutsDestroyed() {
        return coconutsDestroyed;
    }

    public int getCoconutsOnBeach() {
        return coconutsOnBeach;
    }

}
